package com.example.tarefasacademicas.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Fade;

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
import com.google.android.material.transition.MaterialFade;

public class CursoActivity extends AppCompatActivity {

    private ActivityCursoBinding binding;

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Fade fadeIn = new Fade();
        fadeIn.setDuration(300);
        getWindow().setEnterTransition(fadeIn);

        super.onCreate(savedInstanceState);
        binding = ActivityCursoBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());

        if (getIntent().getStringExtra("tela").equals("Cadastrar")) {
            binding.lblTitulo.setText("Cadastro de Curso");
            binding.btnSalvarCurso.setText("Salvar");
            binding.btnCancelar.setText("Cancelar");
            binding.txtDescCurso.setText("");
            binding.txtProfCurso.setText("");
        } else {
            binding.lblTitulo.setText("Atualizar Curso");
            binding.btnSalvarCurso.setText("Atualizar");
            binding.txtDescCurso.setText(getIntent().getStringExtra("desc_curso"));
            binding.txtProfCurso.setText(getIntent().getStringExtra("prof_curso"));
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

        binding.btnSalvarCurso.setOnClickListener(v -> {
            if(getIntent().getStringExtra("tela").equals("Cadastrar")){
                salvarCurso();
            }else{
                atualizarCurso(getIntent().getIntExtra("id_curso",-1));
            }


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

    public void atualizarCurso(int id_curso) {
        Curso curso = new Curso();
        curso.setId_curso(id_curso);
        curso.setDesc_curso(binding.txtDescCurso.getText().toString().toUpperCase());
        curso.setProf_curso(binding.txtProfCurso.getText().toString().toUpperCase());

        if(curso.atualizar(this) == 1){
            new AlertDialog.Builder(this)
                    .setTitle("Atenção")
                    .setMessage("Curso atualizado com sucesso")
                    .setPositiveButton("OK", (dialog, which) -> {
                        finish();
                    })
                    .show();

        }else{
            new AlertDialog.Builder(this)
                    .setTitle("Atenção")
                    .setMessage("Erro ao atualizar curso")
                    .setPositiveButton("OK", null)
                    .show();
        }
    }

}