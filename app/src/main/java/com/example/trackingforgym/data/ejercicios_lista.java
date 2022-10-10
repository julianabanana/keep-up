package com.example.trackingforgym.data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trackingforgym.R;
import com.example.trackingforgym.estructuras.ColasRef;

import java.util.ArrayList;

public class ejercicios_lista extends RecyclerView.Adapter<ejercicios_lista.ViewHolderDatos> {
    ArrayList<Integer> data;
    ColasRef nombre_ejercicio = new ColasRef();
    public ejercicios_lista(ArrayList<Integer> nombres){
        //nombre_ejercicio.Enqueue(nombre);
        data = nombres;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ejercicios_lista.ViewHolderDatos holder, int position) {
        holder.GetText().setText("hola");
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView cuadrito;
        public ViewHolderDatos(@NonNull View itemView) {

            super(itemView);
            cuadrito = (TextView) itemView.findViewById(R.id.textView14);
        }
        public TextView GetText(){
            return cuadrito;
        }
    }
}
