package com.example.tarefasacademicas.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.tarefasacademicas.R;
import com.example.tarefasacademicas.model.Curso;

import java.util.List;

public class CursoAdapter extends ArrayAdapter<Curso> {
    public CursoAdapter(Context context, List<Curso> cursos){
        super(context, 0, cursos);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Curso curso = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_list_curso, parent, false);
        }
        TextView txtItem1 = convertView.findViewById(R.id.txtItem1);
        TextView txtItem2 = convertView.findViewById(R.id.txtItem2);

        txtItem1.setText(curso.getDesc_curso());
        txtItem2.setText("Prof.: " + curso.getProf_curso());

        return convertView;
    }
}
