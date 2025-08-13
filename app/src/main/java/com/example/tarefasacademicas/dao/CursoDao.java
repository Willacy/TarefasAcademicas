package com.example.tarefasacademicas.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.tarefasacademicas.model.Curso;

import java.util.ArrayList;
import java.util.List;

public class CursoDao {

    // Atributos da classe
    private SQLiteDatabase db;

    // Construtor
    public CursoDao(Context context) {
        BDTarefas banco = new BDTarefas(context);
         db = banco.getWritableDatabase();
    }

    // Inserindo um novo curso (CREATE)
    public long inserir(Curso curso) {
            ContentValues valores = new ContentValues();
            valores.put("desc_curso", curso.getDesc_curso());
            valores.put("prof_curso", curso.getProf_curso());
            return db.insert("curso", null, valores);
    }

    // Buscando todos os cursos (READ)
    public List<Curso> listar() {
        // Lista de cursos
        List<Curso> lista = new ArrayList<>();
        // Buscando todos os cursos
        Cursor cursor = db.rawQuery("SELECT * FROM curso", null);

        // Percorrendo o cursor
        while (cursor.moveToNext()) {
            Curso curso = new Curso();
            curso.setId_curso(cursor.getInt(0));
            curso.setDesc_curso(cursor.getString(1));
            curso.setProf_curso(cursor.getString(2));
            lista.add(curso);
        }
        cursor.close();
        return lista;
    }

    // Buscando um curso pelo id (READ)
    public Curso buscar(int id) {
        Cursor cursor = db.rawQuery("SELECT * FROM curso WHERE id_curso = ?", new String[]{String.valueOf(id)});
        System.out.println(cursor);
        if (cursor.moveToFirst()) {
            Curso curso = new Curso();
            curso.setId_curso(cursor.getInt(0));
            curso.setDesc_curso(cursor.getString(1));
            curso.setProf_curso(cursor.getString(2));
            cursor.close();
            return curso;
        }

        cursor.close();
        return null;
    }

    // Atualizando um curso (UPDATE)
    public int atualizar(Curso curso) {
        ContentValues valores = new ContentValues();
        valores.put("desc_curso", curso.getDesc_curso());
        valores.put("prof_curso", curso.getProf_curso());
        return db.update("curso", valores, "id_curso = ?", new String[]{String.valueOf(curso.getId_curso())});
    }

    // Deletando um curso (DELETE)
    public int deletar(int id) {
        return db.delete("curso", "id_curso = ?", new String[]{String.valueOf(id)});
    }

    // Fechando o banco de dados
    public void fechar() {
        db.close();
    }

}
