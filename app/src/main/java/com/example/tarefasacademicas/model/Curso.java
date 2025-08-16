package com.example.tarefasacademicas.model;

import android.content.Context;

import com.example.tarefasacademicas.dao.CursoDao;

import java.util.List;

public class Curso {

    CursoDao cursoDao;
    private int id_curso;
    private String desc_curso;
    private String prof_curso;

    private Context context;

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

    public void setContext(Context context) {
        this.context = context;
    }
    public Context getContext() {
        return context;
    }

    // Inserir curso ao banco de dados
    public long inserir(Context context) {
        cursoDao = new CursoDao(context);
        long insert = cursoDao.inserir(this);
        // Verifica se o curso foi inserido com sucesso
        if (insert == -1) {
            return 0;
            // Caso contrário, retorna 1
        } else {
            return 1;
        }
    }

    // Listar cursos do banco de dados
    public List<Curso> listar(Context context) {
        cursoDao = new CursoDao(context);
        return cursoDao.listar();
    }
    // Listar cursos do banco de dados por id
    public Curso buscar(Context context, int id_curso) {
        cursoDao = new CursoDao(context);
        return cursoDao.buscar(id_curso);
    }

    // Atualizar curso no banco de dados
    public int atualizar(Context context) {
        cursoDao = new CursoDao(context);
        return cursoDao.atualizar(this);
    }

    // Deletar curso do banco de dados
    public int deletar(Context context) {
        cursoDao = new CursoDao(context);
        return cursoDao.deletar(this.id_curso);
    }

    // metodo que verifica se tem tarefa ligada ao curso
    public boolean temTarefa(Context context, int id_curso) {
        cursoDao = new CursoDao(context);
        return cursoDao.temTarefa(id_curso);
    }

    @Override
    public String toString(){
        return desc_curso;
    }
}
