package com.example.tarefasacademicas;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tarefasacademicas.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());

        binding.btnLogin.setOnClickListener(view -> login());

    }
    private void login(){
        // pega os dados do usuario
        String user = binding.txtUser.getEditText().getText().toString();
        String password = binding.txtPassword.getEditText().getText().toString();
        // verifica se os dados não estao vazios
        if(user.isEmpty()) {
            binding.txtUser.setError("Campo vazio");
        }else if(password.isEmpty()){
            binding.txtPassword.setError("Campo vazio");
        }else if(user.equals("admin") && password.equals("admin")){
            Toast.makeText(this, "Login realizado com sucesso", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Usuário ou senha incorretos", Toast.LENGTH_SHORT).show();
        }

    }

}