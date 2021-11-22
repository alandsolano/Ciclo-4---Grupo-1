package com.example.delcampofresco.ui.fruits;

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
import com.example.delcampofresco.databinding.FragmentFruitsBinding;

public class FruitsFragment extends Fragment {

    private FruitsViewModel fruitsViewModel;
    private FragmentFruitsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        fruitsViewModel =
                new ViewModelProvider(this).get(FruitsViewModel.class);

        binding = FragmentFruitsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //final TextView textView = binding.textFruits;
        fruitsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });
        DB marketConnection = new DB(this.getContext(), root, "fruits");
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}