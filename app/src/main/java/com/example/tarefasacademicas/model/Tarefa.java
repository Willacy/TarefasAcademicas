package com.example.tarefasacademicas.model;

import android.content.Context;

import com.example.tarefasacademicas.dao.CursoDao;
import com.example.tarefasacademicas.dao.TarefaDao;
import com.example.tarefasacademicas.view.TarefaActivity;

import java.util.List;

public class Tarefa {

    // Atributos da classe
    private int id_tarefa;
    private String desc_tarefa;
    private String data_tarefa;
    private int curso_tarefa;

    // Construtor
    public Tarefa(int id_tarefa, String desc_tarefa, String data_tarefa, int curso_tarefa) {
        this.id_tarefa = id_tarefa;
        this.desc_tarefa = desc_tarefa;
        this.data_tarefa = data_tarefa;
        this.curso_tarefa = curso_tarefa;
    }

    // Construtor vazio
    public Tarefa() {
    }

    // Getters e Setters
    public int getId_tarefa() {
        return id_tarefa;
    }
    public void setId_tarefa(int id_tarefa) {
        this.id_tarefa = id_tarefa;
    }

    public String getDesc_tarefa() {
        return desc_tarefa;
    }
    public void setDesc_tarefa(String desc_tarefa) {
        this.desc_tarefa = desc_tarefa;
    }

    public String getData_tarefa() {
        return data_tarefa;
    }
    public void setData_tarefa(String data_tarefa) {
        this.data_tarefa = data_tarefa;
    }

    public int getCurso_tarefa() {
        return curso_tarefa;
    }
    public void setCurso_tarefa(int curso_tarefa) {
        this.curso_tarefa = curso_tarefa;
    }

    // Inserir tarefa ao banco de dados
    public long inserir(Context context) {
        TarefaDao tarefaDao = new TarefaDao(context);
        long insert = tarefaDao.inserir(this);
        // Verifica se tarefa foi inserida com sucesso
        if (insert == -1) {
            return 0;
            // Caso contrário, retorna 1
        } else {
            return 1;
        }
    }

    // Listar tarefas do banco de dados
    public List<Tarefa> listar(Context context) {
        TarefaDao tarefaDao = new TarefaDao(context);
        return tarefaDao.listar();
    }

    // Atualizar tarefa no banco de dados
    public int atualizar(Context context) {
        TarefaDao tarefaDao = new TarefaDao(context);
        return tarefaDao.atualizar(this);
    }

    // Deletar tarefa do banco de dados
    public int deletar(Context context) {
        TarefaDao tarefaDao = new TarefaDao(context);
        return tarefaDao.deletar(this.id_tarefa);
    }

    public void setContext(TarefaActivity tarefaActivity) {

    }
}
