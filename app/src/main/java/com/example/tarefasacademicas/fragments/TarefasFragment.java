package com.example.tarefasacademicas.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tarefasacademicas.R;
import com.example.tarefasacademicas.adpter.CursoAdapter;
import com.example.tarefasacademicas.adpter.TarefaAdapter;
import com.example.tarefasacademicas.databinding.FragmentTarefasBinding;
import com.example.tarefasacademicas.model.Curso;
import com.example.tarefasacademicas.model.Tarefa;
import com.example.tarefasacademicas.view.CursoActivity;
import com.example.tarefasacademicas.view.TarefaActivity;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TarefasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TarefasFragment extends Fragment {

    private FragmentTarefasBinding binding;

    private Tarefa tarefa = new Tarefa();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TarefasFragment() {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CursosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TarefasFragment newInstance(String param1, String param2) {
        TarefasFragment fragment = new TarefasFragment();
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
        binding = FragmentTarefasBinding.inflate(inflater, container, false);
        listarTarefas();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnNovaTarefa.setOnClickListener(v -> {
            novaTarefa();
        });
    }

    public void novaTarefa() {
        Intent intent = new Intent(getActivity(), TarefaActivity.class);
        intent.putExtra("tela", "Cadastrar");
        startActivity(intent);
    }

    public void listarTarefas(){
        List<Tarefa> lista = tarefa.listar(requireContext());
        TarefaAdapter adapter = new TarefaAdapter(requireContext(), lista);
        binding.lstTarefas.setAdapter(adapter);
    }

    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onResume(){
        super.onResume();
        listarTarefas();
    }
}