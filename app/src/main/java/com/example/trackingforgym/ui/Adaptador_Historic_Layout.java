package com.example.trackingforgym.ui;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.trackingforgym.R;
import com.example.trackingforgym.data.RutineHistoric;

import sun.bob.mcalendarview.MCalendarView;

public class Adaptador_Historic_Layout extends RecyclerView.Adapter<Adaptador_Historic_Layout.ViewHolder> implements View.OnClickListener{

    public RutineHistoric[] localDataSet;
    public RutineHistoric[] localDataOriginal;
    View.OnClickListener listener;

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    public String getDatos (int i){
        return localDataSet[i].getNombre();
    };


    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titulo;
        private TextView fecha;
        ImageView fig;
        MCalendarView calendarView;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            titulo = (TextView) view.findViewById(R.id.tituloHistoricLayout);
            fecha = (TextView) view.findViewById(R.id.fechaHistoricLayout);
            fig =(ImageView) view.findViewById(R.id.imageView3);
        }

        public TextView getTextView() {
            return titulo;
        }
        public TextView getFechaTextView() {
            return fecha;
        }
        public ImageView getImageView() {
            return fig;
        }
    }

    public Adaptador_Historic_Layout(RutineHistoric[] dataSet) {
        localDataSet = dataSet;
        localDataOriginal=dataSet;
    }


    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.historic_layout, viewGroup, false);
        view.setOnClickListener(this);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        System.out.println(localDataSet[position]);
        //viewHolder.getFechaTextView().setText(localDataSet[position].getFecha().toString());
        viewHolder.getFechaTextView().setText(localDataSet[position].getFecha().toString());
        viewHolder.getTextView().setText(localDataSet[position].getNombre());
        viewHolder.getImageView().setBackgroundColor(Color.parseColor("#"+localDataSet[position].getColor()));

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.length;
    }
}
