package com.example.tarefasacademicas.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tarefasacademicas.model.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class TarefaDao {

    // Atributos da classe
    private SQLiteDatabase db;

    // Construtor
    public TarefaDao(Context context) {
        BDTarefas banco = new BDTarefas(context);
        db = banco.getWritableDatabase();
    }

    // Inserindo uma nova tarefa (CREATE)
    public long inserir(Tarefa tarefa) {
        ContentValues valores = new ContentValues();
        valores.put("desc_tarefa", tarefa.getDesc_tarefa());
        valores.put("data_tarefa", tarefa.getData_tarefa());
        valores.put("curso_tarefa", tarefa.getCurso_tarefa());
        return db.insert("tarefa", null, valores);
    }

    // Buscando todas as tarefas (READ)
    public List<Tarefa> listar() {
        List<Tarefa> lista = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM tarefa", null);
        while (cursor.moveToNext()) {
            Tarefa tarefa = new Tarefa();
            tarefa.setId_tarefa(cursor.getInt(0));
            tarefa.setDesc_tarefa(cursor.getString(1));
            tarefa.setData_tarefa(cursor.getString(2));
            tarefa.setCurso_tarefa(cursor.getInt(3));
            lista.add(tarefa);
        }
        cursor.close();
        return lista;
    }

    // Buscando uma tarefa pelo id (READ)
    public Tarefa buscar(int id) {
        Cursor cursor = db.rawQuery("SELECT * FROM tarefa WHERE id_tarefa = ?", new String[]{String.valueOf(id)});
        if (cursor.moveToFirst()) {
            Tarefa tarefa = new Tarefa();
            tarefa.setId_tarefa(cursor.getInt(0));
            tarefa.setDesc_tarefa(cursor.getString(1));
            tarefa.setData_tarefa(cursor.getString(2));
            tarefa.setCurso_tarefa(cursor.getInt(3));
            cursor.close();
            return tarefa;
        }
        cursor.close();
        return null;
    }

    // Atualizando uma tarefa (UPDATE)
    public int atualizar(Tarefa tarefa) {
        ContentValues valores = new ContentValues();
        valores.put("desc_tarefa", tarefa.getDesc_tarefa());
        valores.put("data_tarefa", tarefa.getData_tarefa());
        valores.put("curso_tarefa", tarefa.getCurso_tarefa());
        return db.update("tarefa", valores, "id_tarefa = ?", new String[]{String.valueOf(tarefa.getId_tarefa())});
    }

    // Deletando uma tarefa (DELETE)
    public int deletar(int id) {
        return db.delete("tarefa", "id_tarefa = ?", new String[]{String.valueOf(id)});
    }

    // Fechando o banco de dados
    public void fechar() {
        db.close();
    }
}
