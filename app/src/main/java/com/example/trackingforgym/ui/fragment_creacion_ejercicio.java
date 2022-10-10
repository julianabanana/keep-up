package com.example.trackingforgym.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.trackingforgym.R;
import com.example.trackingforgym.data.Ejercicio;
import com.example.trackingforgym.data.Rutine;
import com.example.trackingforgym.data.Session;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import yuku.ambilwarna.AmbilWarnaDialog;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_creacion_ejercicio#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_creacion_ejercicio extends Fragment {
    EditText nombreEjercicio;
    EditText editParteCuerpo;
    Button crearEjercicio;
    Button elegirColor;
    private View mColorPreview;
    private int mDefaultColor;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragment_creacion_ejercicio() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static fragment_creacion_ejercicio newInstance(String param1, String param2) {
        fragment_creacion_ejercicio fragment = new fragment_creacion_ejercicio();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_creacion_ejercicio, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nombreEjercicio = (EditText) view.findViewById(R.id.registro_nombre3);
        crearEjercicio = (Button) view.findViewById(R.id.btnSubirEjercicio);
        mColorPreview = (View) view.findViewById(R.id.preview_selected_color);
        editParteCuerpo=(EditText) view.findViewById(R.id.editTextParteCuerpo);
        Fragment este = this;
        crearEjercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("creo rutina");
                Snackbar.make(view, "ejercicio  creado : " + nombreEjercicio.getText().toString(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Ejercicio a = new Ejercicio( nombreEjercicio.getText().toString(),  Integer.toHexString(mDefaultColor).substring(2), editParteCuerpo.getText().toString());
                a.upload();
                a.setIdDataBase();
                Session.getUser().ejercicios.add(a);
                getActivity().onBackPressed();
            }
        });
        elegirColor  = (Button) view.findViewById(R.id.btnElegirColor);
        elegirColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("eligiendo color");
                openColorPickerDialogue();
            }
        });
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
}