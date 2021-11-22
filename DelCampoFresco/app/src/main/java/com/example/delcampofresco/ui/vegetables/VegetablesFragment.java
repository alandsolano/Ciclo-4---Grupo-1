package com.example.delcampofresco.ui.vegetables;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.delcampofresco.DB;
import com.example.delcampofresco.databinding.FragmentVegetablesBinding;

public class VegetablesFragment extends Fragment {

    private VegetablesViewModel vegetablesViewModel;
    private FragmentVegetablesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        vegetablesViewModel =
                new ViewModelProvider(this).get(VegetablesViewModel.class);

        binding = FragmentVegetablesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //final TextView textView = binding.textVegetables;
        vegetablesViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });
        DB marketConnection = new DB(this.getContext(), root, "vegetables");
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}