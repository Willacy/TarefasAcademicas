package com.example.tarefasacademicas.dao;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class BDTarefas extends SQLiteOpenHelper {
    // Atributos da classe
    private static final String NOME_BANCO = "bd_tarefas.db";
    private static final int VERSAO_BANCO = 1;
    // Construtor da classe
    public BDTarefas(Context context){
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }
    // Chamando o banco quando executado pela primeira vez
    @Override
    public void onCreate(SQLiteDatabase db){
        // Cria a tabela chamada curso
        String sql = "CREATE TABLE curso("+
                "id_curso INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "desc_curso TEXT NOT NULL,"+
                "prof_curso TEXT NOT NULL)";
        db.execSQL(sql);
        // Cria a tabela chamada tarefa
        sql = "CREATE TABLE tarefa("+
                "id_tarefa INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "desc_tarefa TEXT NOT NULL,"+
                "data_tarefa TEXT NOT NULL,"+
                "curso_tarefa INTEGER NOT NULL,"+
                "FOREIGN KEY(curso_tarefa) REFERENCES curso(id_curso))";
        db.execSQL(sql);
    }
    // Chamando o banco quando houver uma atualização
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS curso"); // Apaga a tabela curso
        db.execSQL("DROP TABLE IF EXISTS tarefa"); // Apaga a tabela tarefa
        onCreate(db);//
    }
}
