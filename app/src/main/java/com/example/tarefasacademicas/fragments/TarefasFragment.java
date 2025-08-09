package com.example.tarefasacademicas.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tarefasacademicas.R;
import com.example.tarefasacademicas.databinding.FragmentTarefasBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TarefasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TarefasFragment extends Fragment {

    private FragmentTarefasBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTarefasBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}