package com.example.trackingforgym;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.trackingforgym.data.Ejercicio;

import java.util.ArrayList;
import java.util.List;

public class Rutinas_Adaptador extends RecyclerView.Adapter<Rutinas_Adaptador.ViewHolder> {
    //private List<Lista_Rutinas> mData;
    private ArrayList<Ejercicio> mData;
    private LayoutInflater mInflater;
    private Context context;
    final Rutinas_Adaptador.OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Ejercicio item);
    }

    public Rutinas_Adaptador(ArrayList<Ejercicio> itemList,  Context context, Rutinas_Adaptador.OnItemClickListener listener) {
        this.mData = itemList;
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.listener = listener;
    }

    public int getItemCount(){return mData.size();}

    public Rutinas_Adaptador.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.lista_rutinas, null);
        return new Rutinas_Adaptador.ViewHolder(view);
    }

    public void onBindViewHolder(final Rutinas_Adaptador.ViewHolder holder, final int position){
        holder.bindData(mData.get(position));
    }

    public void setItems(ArrayList<Ejercicio> items){ mData=items; }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iconImage;
        TextView nombre, city, status;

        ViewHolder(View itemView) {
            super(itemView);
            iconImage = itemView.findViewById(R.id.IconImageView);
            nombre = itemView.findViewById(R.id.nameTextView);
            status = itemView.findViewById(R.id.statusTextView);
            city = itemView.findViewById(R.id.cityTextView);
        }
        void bindData (final Ejercicio item){
            iconImage.setColorFilter(Color.parseColor("#"+item.getColor()), PorterDuff.Mode.SRC_IN);
            nombre.setText(item.getNombre());
            //status.setText(item.getStatus());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(item);
                }
            });
        }
    }

}
