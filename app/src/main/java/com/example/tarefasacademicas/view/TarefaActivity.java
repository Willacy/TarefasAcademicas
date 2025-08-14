package com.example.tarefasacademicas.view;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tarefasacademicas.R;
import com.example.tarefasacademicas.databinding.ActivityCursoBinding;
import com.example.tarefasacademicas.databinding.ActivityTarefaBinding;
import com.example.tarefasacademicas.model.Curso;
import com.example.tarefasacademicas.model.Tarefa;

import java.util.Calendar;
import java.util.List;

public class TarefaActivity extends AppCompatActivity {

    private ActivityTarefaBinding binding;
    private Curso curso = new Curso();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTarefaBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());

        // Alimentar o spinner
        List<Curso> listaCursos = curso.listar(this);
        ArrayAdapter<Curso> adapter = new ArrayAdapter<>(
                this,
                R.layout.spinner_tarefa,
                listaCursos
        );
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_tarefa);
        binding.spnTarefa.setAdapter(adapter);

        // Essa linha configura a captura da data
        binding.txtDataTarefa.setInputType(InputType.TYPE_NULL);
        binding.txtDataTarefa.setFocusable(false);
        binding.txtDataTarefa.setOnClickListener(v -> {
            final Calendar calendario = Calendar.getInstance();
            int ano = calendario.get(Calendar.YEAR);
            int mes = calendario.get(Calendar.MONTH);
            int dia = calendario.get(Calendar.DAY_OF_MONTH);

            new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
                String data = String.format("%02d/%02d/%04d", dayOfMonth, month + 1, year);
                binding.txtDataTarefa.setText(data);
            }, ano, mes, dia).show();
        });
        // fim da captura da data

        if (getIntent().getStringExtra("tela").equals("Cadastrar")) {
            binding.lblTitulo.setText("Cadastro de tarefa");
            binding.btnSalvarTarefa.setText("Salvar");
            binding.txtDescTarefa.setText("");
            binding.txtDataTarefa.setText("");
            binding.spnTarefa.setSelection(0);
        } else {
            binding.lblTitulo.setText("Atualizar Tarefa");
            binding.btnSalvarTarefa.setText("Atualizar");
            binding.txtDescTarefa.setText(getIntent().getStringExtra("desc_tarefa"));
            binding.txtDataTarefa.setText(getIntent().getStringExtra("data_tarefa"));
            binding.spnTarefa.setSelection(getIntent().getIntExtra("curso_tarefa",-1));
        }

        binding.btnVoltar.setOnClickListener(v -> {
            finish();
        });

        binding.btnCancelar.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("Atenção")
                    .setMessage("Deseja cancelar a operação?")
                    .setNegativeButton("Não", null)
                    .setPositiveButton("Sim", (dialog, which) -> {
                        finish();
                    })
                    .show();
        });

        binding.btnSalvarTarefa.setOnClickListener(v -> {
            if(getIntent().getStringExtra("tela").equals("Cadastrar")){
                salvarTarefa();
            }else{
                atualizarTarefa(getIntent().getIntExtra("id_tarefa",-1));
            }


        });
    }

    // Método para salvar a tarefa
    public void salvarTarefa() {
        // Obtém os dados da tarefa a partir dos campos de texto
        String descTarefa = binding.txtDescTarefa.getText().toString().toUpperCase();
        String dataTarefa = binding.txtDataTarefa.getText().toString().toUpperCase();
        int cursoTarefa = binding.spnTarefa.getSelectedItemPosition();

        // Verifica se os campos estão vazios
        if (descTarefa.isEmpty() || dataTarefa.isEmpty()) {
            new AlertDialog.Builder(this)
                    .setTitle("Atenção")
                    .setMessage("Preencha todos os campos")
                    .setPositiveButton("OK", null)
                    .show();
        } else {
            // Cria um objeto Tarefa
            Tarefa tarefa = new Tarefa();

            tarefa.setDesc_tarefa(descTarefa);
            tarefa.setData_tarefa(dataTarefa);
            tarefa.setCurso_tarefa(cursoTarefa);
            tarefa.setContext(this);

            long inserted = tarefa.inserir(this);
            if (inserted == 1) {
                new AlertDialog.Builder(this)
                        .setTitle("Atenção")
                        .setMessage("Tarefa inserida com sucesso")
                        .setPositiveButton("OK", null)
                        .show();

                binding.txtDescTarefa.setText("");
                binding.txtDataTarefa.setText("");
                binding.spnTarefa.setSelection(0);

            } else {
                new AlertDialog.Builder(this)
                        .setTitle("Atenção")
                        .setMessage("Erro ao inserir tarefa")
                        .setPositiveButton("OK", null)
                        .show();
            }
        }
    }
    // Método para atualizar a tarefa
    public void atualizarTarefa(int id_tarefa) {
        Tarefa tarefa = new Tarefa();
        tarefa.setId_tarefa(id_tarefa);
        tarefa.setDesc_tarefa(binding.txtDescTarefa.getText().toString().toUpperCase());
        tarefa.setData_tarefa(binding.txtDataTarefa.getText().toString().toUpperCase());
        tarefa.setCurso_tarefa(binding.spnTarefa.getSelectedItemPosition());


        if(tarefa.atualizar(this) == 1){
            new AlertDialog.Builder(this)
                    .setTitle("Atenção")
                    .setMessage("Tarefa atualizada com sucesso")
                    .setPositiveButton("OK", (dialog, which) -> {
                        finish();
                    })
                    .show();

        }else{
            new AlertDialog.Builder(this)
                    .setTitle("Atenção")
                    .setMessage("Erro ao atualizar tarefa")
                    .setPositiveButton("OK", null)
                    .show();
        }
    }
}