package com.example.tarefasacademicas.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;

import com.example.tarefasacademicas.R;
import com.example.tarefasacademicas.databinding.FragmentCursosBinding;
import com.example.tarefasacademicas.model.Curso;
import com.example.tarefasacademicas.view.CursoActivity;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CursosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CursosFragment extends Fragment {

    private FragmentCursosBinding binding;
    private Curso curso = new Curso();
    private List<Curso> Cursos = curso.listar();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CursosFragment() {
        // Required empty public constructor
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
    public static CursosFragment newInstance(String param1, String param2) {
        CursosFragment fragment = new CursosFragment();
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
        binding = FragmentCursosBinding.inflate(inflater, container, false);
        ArrayAdapter<Curso> adapter = new ArrayAdapter<Curso>(requireContext(), android.R.layout.simple_list_item_1, Cursos);
        binding.lstCursos.setAdapter(adapter);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnNovoCurso.setOnClickListener(v-> {
            novoCurso();
        });

    }

    public void novoCurso(){
        Intent intent = new Intent(getActivity(), CursoActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}