package com.example.trackingforgym.ui;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.trackingforgym.R;
import com.example.trackingforgym.data.Rutine;

public class Adaptador_rutina_layout extends RecyclerView.Adapter<Adaptador_rutina_layout.ViewHolder> implements View.OnClickListener{

    private Rutine[] localDataSet;
    View.OnClickListener listener;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    public Rutine getDatos (int i){
        return localDataSet[i];
    };

    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
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

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public Adaptador_rutina_layout(Rutine[] dataSet) {
        localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.rutina_layout, null, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        System.out.println("poniendo "+ localDataSet[position]);
        viewHolder.getTextView().setText(localDataSet[position]==null?"":localDataSet[position].getNombre());
        viewHolder.getImageView().setBackgroundColor(Color.parseColor(localDataSet[position]==null?"#62EA15":"#"+localDataSet[position].getColor()));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.length;
    }
}
