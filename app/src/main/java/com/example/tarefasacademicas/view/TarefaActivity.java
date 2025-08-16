package com.example.tarefasacademicas.view;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.widget.ArrayAdapter;
import androidx.appcompat.app.AppCompatActivity;
import com.example.tarefasacademicas.R;
import com.example.tarefasacademicas.databinding.ActivityTarefaBinding;
import com.example.tarefasacademicas.model.Curso;
import com.example.tarefasacademicas.model.Tarefa;
import java.util.Calendar;
import java.util.List;

public class TarefaActivity extends AppCompatActivity {

    private ActivityTarefaBinding binding;
    private Curso cursoModel = new Curso();
    private ArrayAdapter<Curso> spinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTarefaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Alimentar o spinner apenas uma vez
        spinnerAdapter = criarAdapterSpinner();
        binding.spnTarefa.setAdapter(spinnerAdapter);

        configurarCapturaData();
        configurarTela();
        configurarBotoes();
    }

    private ArrayAdapter<Curso> criarAdapterSpinner() {
        List<Curso> listaCursos = cursoModel.listar(this);
        listaCursos.add(0, new Curso(0, "Selecione um curso", ""));
        ArrayAdapter<Curso> adapter = new ArrayAdapter<>(this, R.layout.spinner_tarefa, listaCursos);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_tarefa);
        return adapter;
    }

    private void configurarCapturaData() {
        binding.txtDataTarefa.setInputType(InputType.TYPE_NULL);
        binding.txtDataTarefa.setFocusable(false);
        binding.txtDataTarefa.setOnClickListener(v -> {
            final Calendar c = Calendar.getInstance();
            new DatePickerDialog(this, (view, year, month, day) -> {
                String data = String.format("%02d/%02d/%04d", day, month + 1, year);
                binding.txtDataTarefa.setText(data);
            }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
        });
    }

    private void configurarTela() {
        String acao = getIntent().getStringExtra("tela");
        if ("Cadastrar".equalsIgnoreCase(acao)) {
            binding.lblTitulo.setText("Cadastro de Tarefa");
            binding.btnSalvarTarefa.setText("Salvar");
        } else {
            binding.lblTitulo.setText("Atualizar Tarefa");
            binding.btnSalvarTarefa.setText("Atualizar");
            binding.txtDescTarefa.setText(getIntent().getStringExtra("desc_tarefa"));
            binding.txtDataTarefa.setText(getIntent().getStringExtra("data_tarefa"));
            Curso cursoSelecionado = new Curso().buscar(this, getIntent().getIntExtra("curso_tarefa", 0));
            selecionarCursoNoSpinner(cursoSelecionado);
        }
    }

    private void selecionarCursoNoSpinner(Curso cursoSelecionado) {
        for (int i = 0; i < spinnerAdapter.getCount(); i++) {
            if (spinnerAdapter.getItem(i).getId_curso() == cursoSelecionado.getId_curso()) {
                binding.spnTarefa.setSelection(i);
                break;
            }
        }
    }

    private void configurarBotoes() {
        binding.btnVoltar.setOnClickListener(v -> finish());

        binding.btnCancelar.setOnClickListener(v ->
                new AlertDialog.Builder(this)
                        .setTitle("Atenção")
                        .setMessage("Deseja cancelar a operação?")
                        .setNegativeButton("Não", null)
                        .setPositiveButton("Sim", (dialog, which) -> finish())
                        .show());

        binding.btnSalvarTarefa.setOnClickListener(v -> {
            String acao = getIntent().getStringExtra("tela");
            if ("Cadastrar".equalsIgnoreCase(acao)) {
                salvarTarefa();
            } else {
                atualizarTarefa(getIntent().getIntExtra("id_tarefa", -1));
            }
        });
    }

    private boolean validarCampos(String descTarefa, String dataTarefa, Curso cursoSelecionado) {
        if (descTarefa.isEmpty() || dataTarefa.isEmpty()) {
            new AlertDialog.Builder(this)
                    .setTitle("Atenção")
                    .setMessage("Preencha todos os campos")
                    .setPositiveButton("OK", null)
                    .show();
            return false;
        } else if (cursoSelecionado.getId_curso() == 0) {
            new AlertDialog.Builder(this)
                    .setTitle("Atenção")
                    .setMessage("Selecione um curso!")
                    .setPositiveButton("OK", null)
                    .show();
            return false;
        }
        return true;
    }

    private void salvarTarefa() {
        String desc = binding.txtDescTarefa.getText().toString().toUpperCase();
        String data = binding.txtDataTarefa.getText().toString().toUpperCase();
        Curso cursoSelecionado = (Curso) binding.spnTarefa.getSelectedItem();

        if (!validarCampos(desc, data, cursoSelecionado)) return;

        Tarefa tarefa = new Tarefa();
        tarefa.setDesc_tarefa(desc);
        tarefa.setData_tarefa(data);
        tarefa.setCurso_tarefa(cursoSelecionado.getId_curso());
        tarefa.setContext(this);

        long resultado = tarefa.inserir(this);
        if (resultado != -1) {
            new AlertDialog.Builder(this)
                    .setTitle("Sucesso")
                    .setMessage("Tarefa inserida com sucesso")
                    .setPositiveButton("OK", null)
                    .show();
            limparCampos();
        } else {
            new AlertDialog.Builder(this)
                    .setTitle("Erro")
                    .setMessage("Erro ao inserir tarefa")
                    .setPositiveButton("OK", null)
                    .show();
        }
    }

    private void atualizarTarefa(int idTarefa) {
        String desc = binding.txtDescTarefa.getText().toString().toUpperCase();
        String data = binding.txtDataTarefa.getText().toString().toUpperCase();
        Curso cursoSelecionado = (Curso) binding.spnTarefa.getSelectedItem();

        if (!validarCampos(desc, data, cursoSelecionado)) return;

        Tarefa tarefa = new Tarefa();
        tarefa.setId_tarefa(idTarefa);
        tarefa.setDesc_tarefa(desc);
        tarefa.setData_tarefa(data);
        tarefa.setCurso_tarefa(cursoSelecionado.getId_curso());

        if (tarefa.atualizar(this) == 1) {
            new AlertDialog.Builder(this)
                    .setTitle("Sucesso")
                    .setMessage("Tarefa atualizada com sucesso")
                    .setPositiveButton("OK", (dialog, which) -> finish())
                    .show();
        } else {
            new AlertDialog.Builder(this)
                    .setTitle("Erro")
                    .setMessage("Erro ao atualizar tarefa")
                    .setPositiveButton("OK", null)
                    .show();
        }
    }

    private void limparCampos() {
        binding.txtDescTarefa.setText("");
        binding.txtDataTarefa.setText("");
        binding.spnTarefa.setSelection(0);
        binding.txtDescTarefa.requestFocus();
    }
}
