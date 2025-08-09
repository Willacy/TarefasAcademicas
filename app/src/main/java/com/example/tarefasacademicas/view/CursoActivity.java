package com.example.tarefasacademicas.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.tarefasacademicas.R;
import com.example.tarefasacademicas.databinding.ActivityCursoBinding;
import com.example.tarefasacademicas.fragments.CursosFragment;
import com.example.tarefasacademicas.model.Curso;

public class CursoActivity extends AppCompatActivity {

    private ActivityCursoBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCursoBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());

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

        binding.btnSalvarCurso.setOnClickListener(v -> {
            salvarCurso();
        });
    }

    // Método para salvar o curso
    public void salvarCurso() {
        // Obtém os dados do curso a partir dos campos de texto
        String descCurso = binding.txtDescCurso.getText().toString().toUpperCase();
        String profCurso = binding.txtProfCurso.getText().toString().toUpperCase();

        // Verifica se os campos estão vazios
        if (descCurso.isEmpty() || profCurso.isEmpty()) {
            new AlertDialog.Builder(this)
                    .setTitle("Atenção")
                    .setMessage("Preencha todos os campos")
                    .setPositiveButton("OK", null)
                    .show();
        } else {
            // Cria um objeto Curso
            Curso curso = new Curso();

            curso.setDesc_curso(descCurso);
            curso.setProf_curso(profCurso);
            curso.setContext(this);

            long inserted = curso.inserir(this);
            if (inserted == 1) {
                new AlertDialog.Builder(this)
                        .setTitle("Atenção")
                        .setMessage("Curso inserido com sucesso")
                        .setPositiveButton("OK", null)
                        .show();

                binding.txtDescCurso.setText("");
                binding.txtProfCurso.setText("");

            } else {
                new AlertDialog.Builder(this)
                        .setTitle("Atenção")
                        .setMessage("Erro ao inserir curso")
                        .setPositiveButton("OK", null)
                        .show();
            }
        }
    }
}