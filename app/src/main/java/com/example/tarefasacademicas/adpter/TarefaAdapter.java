package com.example.tarefasacademicas.adpter;

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
import com.example.tarefasacademicas.dao.CursoDao;
import com.example.tarefasacademicas.model.Curso;
import com.example.tarefasacademicas.model.Tarefa;
import com.example.tarefasacademicas.view.TarefaActivity;

import java.util.List;

public class TarefaAdapter extends ArrayAdapter<Tarefa> {

    public TarefaAdapter(Context context, List<Tarefa> tarefas) {
        super(context, 0, tarefas);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Tarefa tarefa = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_list_tarefa, parent, false);
        }
        TextView txtItem1 = convertView.findViewById(R.id.txtItem1);
        TextView txtItem2 = convertView.findViewById(R.id.txtItem2);
        TextView txtItem3 = convertView.findViewById(R.id.txtItem3);
        ImageButton btnEditar = convertView.findViewById(R.id.btnEditar);
        ImageButton btnDeletar = convertView.findViewById(R.id.btnDeletar);


        // Preencher os campos do item da lista com os dados da tarefa
        Curso curso = new Curso().listar(getContext()).get(tarefa.getCurso_tarefa());

        txtItem1.setText(tarefa.getDesc_tarefa());
        txtItem2.setText("Curso: " + curso.getDesc_curso());
        txtItem3.setText("Data: " + tarefa.getData_tarefa());

        btnEditar.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), TarefaActivity.class);
            intent.putExtra("tela", "editar");
            intent.putExtra("id_tarefa", tarefa.getId_tarefa());
            intent.putExtra("desc_tarefa", tarefa.getDesc_tarefa());
            intent.putExtra("curso_tarefa", tarefa.getCurso_tarefa());
            intent.putExtra("data_tarefa", tarefa.getData_tarefa());
            getContext().startActivity(intent);
        });

        btnDeletar.setOnClickListener(v -> {
            new AlertDialog.Builder(getContext())
                    .setTitle("Cuidado!")
                    .setMessage("Deseja deletar a tarefa?")
                    .setNegativeButton("Não", null)
                    .setPositiveButton("Sim", (dialog, which) -> {
                        if (tarefa.deletar(getContext()) == 1) {
                            Toast.makeText(getContext(), "Tarefa deletada com sucesso", Toast.LENGTH_SHORT).show();
                            remove(tarefa);
                        } else {
                            Toast.makeText(getContext(), "Erro ao deletar tarefa", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .show();
        });
        return convertView;
    }
}
