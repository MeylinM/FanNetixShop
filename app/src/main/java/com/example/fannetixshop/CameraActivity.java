package com.example.fannetixshop;

import android.Manifest;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.*;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import com.google.common.util.concurrent.ListenableFuture;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class CameraActivity extends AppCompatActivity {
    private PreviewView previewView;
    private Button captureButton;
    private ImageCapture imageCapture;
    private ExecutorService cameraExecutor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);


        previewView = findViewById(R.id.previewView);
        captureButton = findViewById(R.id.captureButton);


        // Verifica permisos de cámara
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            startCamera();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 10);
        }


        captureButton.setOnClickListener(v -> takePhoto());


        cameraExecutor = Executors.newSingleThreadExecutor();
    }


    private void startCamera() {
        // Obtenemos una instancia de ProcessCameraProvider de forma asincrónica
        ListenableFuture<ProcessCameraProvider> cameraProviderFuture = ProcessCameraProvider.getInstance(this);


        // Agregamos un Listener para obtener el resultado de cameraProviderFuture cuando esté disponible
        cameraProviderFuture.addListener(() -> {
            try {
                // Obtenemos el ProcessCameraProvider usando get(), que es sincrónico
                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();


                // Configuramos la vista previa de la cámara
                Preview preview = new Preview.Builder().build();
                preview.setSurfaceProvider(previewView.getSurfaceProvider());


                // Configuración para capturar imágenes
                imageCapture = new ImageCapture.Builder().build();


                // Selección de cámara (trasera en este caso)
                CameraSelector cameraSelector = new CameraSelector.Builder()
                        .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                        .build();


                // Desvinculamos cualquier configuración anterior y vinculamos las nuevas configuraciones de cámara
                cameraProvider.unbindAll();
                cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture);


            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }, ContextCompat.getMainExecutor(this));
    }


    private void takePhoto() {
        if (imageCapture == null) return;


        // Configuración de archivo para guardar la imagen
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, "new_photo");
        contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg");


        ImageCapture.OutputFileOptions outputOptions = new ImageCapture.OutputFileOptions
                .Builder(getContentResolver(), MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                .build();


        // Captura de la foto
        imageCapture.takePicture(outputOptions, ContextCompat.getMainExecutor(this),
                new ImageCapture.OnImageSavedCallback() {
                    @Override
                    public void onImageSaved(@NonNull ImageCapture.OutputFileResults output) {
                        Toast.makeText(CameraActivity.this, "Photo saved successfully!", Toast.LENGTH_SHORT).show();
                    }


                    @Override
                    public void onError(@NonNull ImageCaptureException exception) {
                        Toast.makeText(CameraActivity.this, "Error saving photo: " + exception.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // Llamada al método de la clase base para manejar cualquier acción predeterminada
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        if (requestCode == 10 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Permiso concedido, iniciar la cámara
            startCamera();
        } else {
            // Permiso no concedido, mostrar mensaje
            Toast.makeText(this, "Camera permission is required", Toast.LENGTH_SHORT).show();
        }
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        cameraExecutor.shutdown();
    }
}

