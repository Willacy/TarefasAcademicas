package com.example.tarefasacademicas.model;

public class Curso {
    private int id_curso;
    private String desc_curso;
    private String prof_curso;

    // Construtor
    public Curso(int id_curso, String desc_curso, String prof_curso) {
        this.id_curso = id_curso;
        this.desc_curso = desc_curso;
        this.prof_curso = prof_curso;
    }

    // Construtor vazio
    public Curso() {
    }

    //getters e setters
    public int getId_curso() {
        return id_curso;
    }

    public void setId_curso(int id_curso) {
        this.id_curso = id_curso;
    }

    public String getDesc_curso() {
        return desc_curso;
    }

    public void setDesc_curso(String desc_curso) {
        this.desc_curso = desc_curso;
    }

    public String getProf_curso() {
        return prof_curso;
    }

    public void setProf_curso(String prof_curso) {
        this.prof_curso = prof_curso;
    }
}
