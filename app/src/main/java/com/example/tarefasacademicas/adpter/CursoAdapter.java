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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

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
            intent.putExtra("tela", "editar");
            intent.putExtra("id_curso", curso.getId_curso());
            intent.putExtra("desc_curso", curso.getDesc_curso());
            intent.putExtra("prof_curso", curso.getProf_curso());
            getContext().startActivity(intent);
        });

        btnDeletar.setOnClickListener(v -> {
            new AlertDialog.Builder(getContext())
                    .setTitle("Cuidado!")
                    .setMessage("Deseja deletar o curso?")
                    .setNegativeButton("Não", null)
                    .setPositiveButton("Sim", (dialog, which) -> {
                        if (curso.deletar(getContext()) == 1) {
                            Toast.makeText(getContext(), "Curso deletado com sucesso", Toast.LENGTH_SHORT).show();
                            remove(curso);
                        }else{
                            Toast.makeText(getContext(), "Erro ao deletar curso", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .show();
        });

        return convertView;
    }
}
