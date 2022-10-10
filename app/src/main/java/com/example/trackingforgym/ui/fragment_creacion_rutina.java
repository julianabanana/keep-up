package com.example.trackingforgym.ui;

import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trackingforgym.R;
import com.example.trackingforgym.data.DataBase;
import com.example.trackingforgym.data.Ejercicio;
import com.example.trackingforgym.data.Rutine;
import com.example.trackingforgym.data.RutineHistoric;
import com.example.trackingforgym.data.Serie;
import com.example.trackingforgym.data.Session;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import sun.bob.mcalendarview.MCalendarView;
import yuku.ambilwarna.AmbilWarnaDialog;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_creacion_rutina#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_creacion_rutina extends Fragment {
    Rutine aSubir;
    Boolean[] agregados;
    EditText nombreRutina;
    private View mColorPreview;
    Button crearRutina;
    Adaptador_Ejercicios_Creacion_Rutina adapter;
    FloatingActionButton agregarEjercicio;
    RecyclerView recycler;
    View root;
    private int mDefaultColor;
    Button pickColor;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters

    public fragment_creacion_rutina() {
        }


    // TODO: Rename and change types and number of parameters
    public static fragment_creacion_rutina newInstance(String param1, String param2) {
        fragment_creacion_rutina fragment = new fragment_creacion_rutina();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_creacion_rutina, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        root = view;
        mColorPreview = (View) view.findViewById(R.id.preview_selected_color1);
        pickColor = (Button) view.findViewById(R.id.button_DEfColor);
        pickColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openColorPickerDialogue();
            }
        });
        agregados = new Boolean[Session.getUser().ejercicios.size()];
        for(int i =0; i<agregados.length;i++){
            agregados[i]=false;
        }
        aSubir = new Rutine(0,0,"","",0);
        nombreRutina = (EditText) view.findViewById(R.id.editText_nombre_rutina);
        crearRutina = (Button) view.findViewById(R.id.button_crear);
        recycler=(RecyclerView) view.findViewById(R.id.recyclerView_ejercicios_de_rutina);
        agregarEjercicio = (FloatingActionButton)  view.findViewById(R.id.button_agregar_ejercicios);
        setRecycler();
        crearRutina.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Rutine g = new Rutine(0, 0, Integer.toHexString(mDefaultColor).substring(2), nombreRutina.getText().toString(), 0);
                g.ejercicios= aSubir.ejercicios;
                g.upload();
                g.setIdDataBase();
                Session.getUser().rutinas.add(g);
                /*System.out.print(g.getId()+" "+g.getColor()+" "+g.getNombre()+" "+" | ");
                for(Ejercicio e : g.ejercicios){
                    System.out.print(e.getId()+" "+e.getColor()+" "+e.getNombre()+" "+" | ");

                    System.out.println();
                }*/
                getActivity().onBackPressed();
            };});

            agregarEjercicio.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    Navigation.findNavController(view).navigate(R.id.fragment_creacion_ejercicio);
                }
            }
        );

    }

    public void setRecycler(){
        adapter= new Adaptador_Ejercicios_Creacion_Rutina(Session.getUser().ejercicios);
        recycler.setLayoutManager(new LinearLayoutManager(root.getContext(),LinearLayoutManager.HORIZONTAL, false));
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i=recycler.getChildAdapterPosition(view);
                if(agregados[i]){
                    ImageView a = (ImageView) view.findViewById(R.id.imageView5);
                    a.setImageResource(R.drawable.dumbell);
                    agregados[i]=false;
                    aSubir.ejercicios.remove(adapter.getDatos(i));
                }else{
                    ImageView a = (ImageView) view.findViewById(R.id.imageView5);
                    a.setImageResource(R.drawable.ic_baseline_bar_chart_24);
                    aSubir.ejercicios.add(adapter.getDatos(i));
                    agregados[i]=true;
                }

                agregarEjerccicio(adapter.getDatos(i));
            }
        });
        recycler.setAdapter(adapter);
    }
    public void agregarEjerccicio(Ejercicio i){
        System.out.println("agregar "+i.getNombre());
    }

    public void openColorPickerDialogue() {
        mDefaultColor = 150;
        // the AmbilWarnaDialog callback needs 3 parameters
        // one is the context, second is default color,
        final AmbilWarnaDialog colorPickerDialogue = new AmbilWarnaDialog(getContext(), mDefaultColor,
                new AmbilWarnaDialog.OnAmbilWarnaListener() {
                    @Override
                    public void onCancel(AmbilWarnaDialog dialog) {
                        // leave this function body as
                        // blank, as the dialog
                        // automatically closes when
                        // clicked on cancel button
                    }

                    @Override
                    public void onOk(AmbilWarnaDialog dialog, int color) {

                        mDefaultColor = color;
                        System.out.println(mDefaultColor);
                        mColorPreview.setBackgroundColor(mDefaultColor);
                    }
                });
        colorPickerDialogue.show();
    }
        // or  (ImageView) view.findViewById(R.id.foo);
}