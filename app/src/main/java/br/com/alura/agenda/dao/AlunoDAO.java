package br.com.alura.agenda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.agenda.modelo.Aluno;

/**
 * Created by pablo on 19/06/16.
 */
public class AlunoDAO extends SQLiteOpenHelper {

    private static final String NOME_DO_BANCO = "AGENDA";
    private static final String NOME_DA_TABELA = "Aluno";
    private static final String CRIA_TABELA_ALUNO = "CREATE TABLE "+ NOME_DA_TABELA +" (id INTEGER PRIMARY KEY, nome TEXT NOT NULL, endereco TEXT, telefone TEXT, site TEXT, nota REAL);";
    private static final String DELETA_TABELA_ALUNO = "DROP TABLE IF EXISTS "+ NOME_DA_TABELA + " ;";
    private static final String CONSULTA_TODOS_ALUNOS = "SELECT * FROM " + NOME_DA_TABELA + " ;";

    public AlunoDAO(Context context) {
        super(context, NOME_DO_BANCO, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CRIA_TABELA_ALUNO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versaoInicial, int versaoFinal) {
        db.execSQL(DELETA_TABELA_ALUNO);
        onCreate(db);
    }

    @NonNull
    private ContentValues getContentValues(Aluno aluno) {
        ContentValues values = new ContentValues();
        values.put("nome", aluno.getNome());
        values.put("endereco", aluno.getEndereco());
        values.put("telefone", aluno.getTelefone());
        values.put("site", aluno.getSite());
        values.put("nota", aluno.getNota());
        return values;
    }

    private void insere(Aluno aluno) {
        //busca instância do banco de dados
        SQLiteDatabase db = getWritableDatabase();
        //preenche os dados do aluno
        ContentValues values = getContentValues(aluno);
        //salvar o aluno
        db.insert(NOME_DA_TABELA, null, values);
        //fechar conexao
        close();
    }

    private void altera(Aluno aluno) {
        //busca instância do banco de dados
        SQLiteDatabase db = getWritableDatabase();
        //preenche os dados do aluno
        ContentValues values = getContentValues(aluno);
        //parâmetros da atualização
        String[] params = {aluno.getId().toString()};
        //atualiza o aluno
        db.update(NOME_DA_TABELA, values, "id = ?", params);
        //fechar conexao
        close();
    }

    public List<Aluno> buscaAlunos() {

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(CONSULTA_TODOS_ALUNOS, null);
        List<Aluno> alunos = new ArrayList<>();
        while (cursor.moveToNext()) {
            Aluno aluno = new Aluno();
            aluno.setId(cursor.getLong(cursor.getColumnIndex("id")));
            aluno.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            aluno.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));
            aluno.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
            aluno.setSite(cursor.getString(cursor.getColumnIndex("site")));
            aluno.setNota(cursor.getDouble(cursor.getColumnIndex("nota")));
            alunos.add(aluno);
        }
        cursor.close();
        return alunos;
    }

    public void delete(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();
        String[] param = {aluno.getId().toString()};
        db.delete(NOME_DA_TABELA, "id = ?", param);
    }

    public void grava(Aluno aluno) {

        if(aluno.getId() == null)
            insere(aluno);
        else
            altera(aluno);

    }
}
