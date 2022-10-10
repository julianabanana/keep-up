package com.example.trackingforgym.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trackingforgym.R;
import com.example.trackingforgym.data.ejercicios_lista;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListaEjerciciosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListaEjerciciosFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View root;
    public ListaEjerciciosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListaEjerciciosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListaEjerciciosFragment newInstance(String param1, String param2) {
        ListaEjerciciosFragment fragment = new ListaEjerciciosFragment();
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
        return inflater.inflate(R.layout.fragment_lista_ejercicios, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        root =view;
        //setElements();
        recycle();

        System.out.println("cambadio helo");
    }
    public void recycle(){
        RecyclerView ejercicios= root.findViewById(R.id.recyclerView);
        ArrayList<Integer> lista= new ArrayList<Integer>();
        lista.add(1);
        lista.add(2);
        lista.add(3);
        lista.add(4);
        ejercicios_lista adaptador = new ejercicios_lista(lista);
        ejercicios.setLayoutManager(new LinearLayoutManager(root.getContext(),LinearLayoutManager.VERTICAL, false));
        ejercicios.setAdapter(adaptador);
    }
}