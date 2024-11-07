package com.example.fannetixshop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ArticuloAdapter extends RecyclerView.Adapter<ArticuloViewHolder> {

    private List<Articulo> articuloList;
    //private List<Multimedia>multimediaList;

    public ArticuloAdapter(List<Articulo> articuloList) {
        this.articuloList = articuloList;
        //this.multimediaList = multimediaList;
    }

    @NonNull
    @Override
    public ArticuloViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new ArticuloViewHolder(layoutInflater.inflate(R.layout.item_articulos, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ArticuloViewHolder holder, int position) {
        Articulo item = articuloList.get(position);
        holder.render(item);

        //Multimedia multimedia = multimediaList.get(position);
        //holder.renderMulti(multimedia);
    }

    @Override
    public int getItemCount() {
        return articuloList.size();
    }
}
