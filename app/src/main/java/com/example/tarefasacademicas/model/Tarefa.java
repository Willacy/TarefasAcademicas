package com.example.tarefasacademicas.model;

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
}
