package com.example.trackingforgym.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.trackingforgym.R;
import com.example.trackingforgym.databinding.FragmentMenuBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_calculo_rm#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_calculo_rm extends Fragment {
    private FragmentMenuBinding binding;
    EditText pesoLevantado;
    EditText repeticiones;
    Button calcular;
    TextView resultado;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragment_calculo_rm() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_calculo_rm.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_calculo_rm newInstance(String param1, String param2) {
        fragment_calculo_rm fragment = new fragment_calculo_rm();
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
        //binding = inflater.inflate(R.layout.fragment_calculo_rm, container, false);
        //View root = binding.getRoot();

        /*calcular=(Button) root.findViewById(R.id.calcularRMDatos);
        pesoLevantado=(EditText) root.findViewById(R.id.textPesoLevantado);
        repeticiones=(EditText) root.findViewById(R.id.editTextRepeticiones);
        resultado=(TextView) root.findViewById(R.id.resultadoRm);

        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a=Integer.valueOf(repeticiones.getText().toString());
                Double d=Double.valueOf(pesoLevantado.getText().toString());
                resultado.setText(Double.toString(d/(1.0278-(0.0278*a))));
            }
        });*/


        //return inflater.inflate(R.layout.fragment_calculo_rm, container, false);
        return inflater.inflate(R.layout.fragment_calculo_rm, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        calcular=(Button) view.findViewById(R.id.calcularRMDatos);
        pesoLevantado=(EditText) view.findViewById(R.id.textPesoLevantado);
        repeticiones=(EditText) view.findViewById(R.id.editTextRepeticiones);
        resultado=(TextView) view.findViewById(R.id.resultadoRm);

        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a=Integer.valueOf(repeticiones.getText().toString());
                Double d=Double.valueOf(pesoLevantado.getText().toString());
                resultado.setText(Double.toString(Math.round((d/(1.0278-(0.0278*a)))*10)/10)+" Kg");
            }
        });
    }
}