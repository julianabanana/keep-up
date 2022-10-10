package com.example.trackingforgym.ui.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.trackingforgym.R;
import com.example.trackingforgym.databinding.FragmentMenuBinding;

public class MenuFragment extends Fragment {

    private MenuViewModel menuViewModel;
    private FragmentMenuBinding binding;

    Button irCalculaMaximos;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        menuViewModel =
                new ViewModelProvider(this).get(MenuViewModel.class);

        binding = FragmentMenuBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        irCalculaMaximos = (Button) root.findViewById(R.id.btnCalculaMaximos);

        irCalculaMaximos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("ir rm");
                //Navigation.findNavController(root).navigate(R.id.fragment_calculo_rm);
                Navigation.findNavController(view).navigate(R.id.fragment_calculo_rm);
            }
        });
        /*final TextView textView = binding.textMenu;
        menuViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}