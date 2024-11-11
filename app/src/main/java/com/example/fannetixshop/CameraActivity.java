package com.example.fannetixshop;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.common.util.concurrent.ListenableFuture;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CameraActivity extends AppCompatActivity {
    private PreviewView previewView;
    private ImageButton captureButton, closeButton, checkButton, returnButton;
    private ImageView imageViewCaptured;
    private ImageCapture imageCapture;
    private ExecutorService cameraExecutor;
    private String imagePath; //Para almacenar el path de la imagen

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        // Inicializar vistas
        previewView = findViewById(R.id.previewView);
        captureButton = findViewById(R.id.captureButton);
        closeButton = findViewById(R.id.closeButton);
        checkButton = findViewById(R.id.checkButton);
        returnButton = findViewById(R.id.returnButton);
        imageViewCaptured = findViewById(R.id.imageViewCaptured); // Asignamos el ImageView

        // Establecer botones ocultos inicialmente
        closeButton.setVisibility(View.GONE);
        checkButton.setVisibility(View.GONE);
        imageViewCaptured.setVisibility(View.GONE); // Ocultamos la imagen inicialmente

        // Verificar permisos de cámara
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            startCamera();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 10);
        }

        // Configurar el botón de captura
        captureButton.setOnClickListener(v -> takePhoto());

        // Configurar el botón de cerrar
        closeButton.setOnClickListener(v -> {
            // Ocultar la imagen y volver a los botones iniciales
            imageViewCaptured.setVisibility(View.GONE); // Ocultar la imagen
            captureButton.setVisibility(View.VISIBLE); // Mostrar el botón de captura
            returnButton.setVisibility(View.VISIBLE);
            closeButton.setVisibility(View.GONE); // Ocultar el botón de cerrar
            checkButton.setVisibility(View.GONE); // Ocultar el botón de check
        });

        // Configurar el botón de aceptar (check)
        checkButton.setOnClickListener(v -> {
            // Acción para guardar la foto
            Toast.makeText(this, getString(R.string.toastImageSaved), Toast.LENGTH_SHORT).show();

            // Crear el Intent para devolver el path de la imagen
            Intent resultIntent = new Intent();
            resultIntent.putExtra("imagePath", imagePath); // 'imagePath' es el path de la imagen tomada
            setResult(RESULT_OK, resultIntent);
            finish();
        });

        // Configurar el botón de regreso
        returnButton.setOnClickListener(v -> {
            // Volver a la actividad anterior sin guardar la foto
            setResult(RESULT_CANCELED);
            finish();
        });

        // Inicializar el executor de la cámara
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

        // Guardar la imagen en el almacenamiento
        ImageCapture.OutputFileOptions outputOptions = new ImageCapture.OutputFileOptions
                .Builder(getContentResolver(), MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                .build();

        // Captura de la foto
        imageCapture.takePicture(outputOptions, ContextCompat.getMainExecutor(this),
                new ImageCapture.OnImageSavedCallback() {
                    @Override
                    public void onImageSaved(@NonNull ImageCapture.OutputFileResults output) {

                        // Convertir la imagen guardada en un Bitmap y mostrarla en ImageView
                        try {
                            Bitmap bitmap = null;
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
                                bitmap = ImageDecoder.decodeBitmap(ImageDecoder.createSource(getContentResolver(), output.getSavedUri()));
                            }
                            imageViewCaptured.setImageBitmap(bitmap);
                            imageViewCaptured.setVisibility(View.VISIBLE); // Mostrar la imagen capturada

                            // Guardar el path de la imagen
                            imagePath = output.getSavedUri().toString();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        // Después de tomar la foto, mostrar los botones de cerrar y aceptar
                        captureButton.setVisibility(View.GONE);
                        returnButton.setVisibility(View.GONE);
                        closeButton.setVisibility(View.VISIBLE);
                        checkButton.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onError(@NonNull ImageCaptureException exception) {
                        Toast.makeText(CameraActivity.this, getString(R.string.toastImageError), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 10 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Permiso concedido, iniciar la cámara
            startCamera();
        } else {
            // Permiso no concedido, mostrar mensaje
            Toast.makeText(this, getString(R.string.toastCameraPermission), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cameraExecutor.shutdown();
    }
}
