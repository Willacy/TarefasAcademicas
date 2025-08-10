package com.example.tarefasacademicas.adpter;

import static android.app.PendingIntent.getActivity;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.tarefasacademicas.R;
import com.example.tarefasacademicas.model.Curso;
import com.example.tarefasacademicas.view.CursoActivity;

import java.util.List;

public class CursoAdapter extends ArrayAdapter<Curso> {

    public CursoAdapter(Context context, List<Curso> cursos) {
        super(context, 0, cursos);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Curso curso = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_list_curso, parent, false);
        }
        TextView txtItem1 = convertView.findViewById(R.id.txtItem1);
        TextView txtItem2 = convertView.findViewById(R.id.txtItem2);
        ImageButton btnEditar = convertView.findViewById(R.id.btnEditar);
        ImageButton btnDeletar = convertView.findViewById(R.id.btnDeletar);

        txtItem1.setText(curso.getDesc_curso());
        txtItem2.setText("Prof.: " + curso.getProf_curso());

        btnEditar.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), CursoActivity.class);
            intent.putExtra("crud", 1);
            intent.putExtra("id", curso.getId_curso());
            intent.putExtra("desc", curso.getDesc_curso());
            intent.putExtra("prof", curso.getProf_curso());
            getContext().startActivity(intent);

        });

        return convertView;
    }
}
