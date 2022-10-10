package com.example.trackingforgym.ui;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.trackingforgym.R;
import com.example.trackingforgym.data.Ejercicio;

import java.util.ArrayList;

import sun.bob.mcalendarview.MCalendarView;

public class Adaptador_Ejercicios_Creacion_Rutina extends RecyclerView.Adapter<Adaptador_Ejercicios_Creacion_Rutina.ViewHolder> implements View.OnClickListener{

    private ArrayList<Ejercicio> localDataSet;
    View.OnClickListener listener;

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    public Ejercicio getDatos (int i){
        return localDataSet.get(i);
    }


    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView titulo;
        ImageView fig;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            titulo = (TextView) view.findViewById(R.id.tituloLayoutRutina);
            fig =(ImageView) view.findViewById(R.id.imageView5);
        }

        public TextView getTextView() {
            return titulo;
        }
        public ImageView getImageView() {
            return fig;
        }
    }


    public Adaptador_Ejercicios_Creacion_Rutina(ArrayList<Ejercicio> dataSet) {
        localDataSet = dataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.rutina_layout, viewGroup, false);
        view.setOnClickListener(this);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        System.out.println("poniendo "+ localDataSet.get(position));
        viewHolder.getTextView().setText(localDataSet.get(position)==null?"":localDataSet.get(position).getNombre());
        viewHolder.getImageView().setBackgroundColor(Color.parseColor(localDataSet.get(position)==null?"#62EA15":"#"+localDataSet.get(position).getColor()));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
