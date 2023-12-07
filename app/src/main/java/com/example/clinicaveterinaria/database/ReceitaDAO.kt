package com.example.clinicaveterinaria.database

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.example.clinicaveterinaria.model.Receita


class ReceitaDAO(context: Context) : IReceitaDao {

    private val escrita = DatabaseHelper(context).writableDatabase
    private val leitura = DatabaseHelper(context).readableDatabase

    override fun salvar(receita: Receita): Boolean {
        val valores = ContentValues()
        valores.put("${DatabaseHelper.TRECEITA_NOME}", receita.nome);


        try {
            escrita.insert(
                DatabaseHelper.TABELA_RECEITA,
                null,
                valores
            )
            Log.i("db", "Receita inserido na tabela. ${DatabaseHelper.TABELA_RECEITA}.")
        } catch (e: Exception) {
            Log.i("db", "Erro ao inserir receita na tabela ${DatabaseHelper.TABELA_RECEITA}.")
            return false
        }
        return true
    }


    override fun atualizar(receita: Receita): Boolean {
        val args = arrayOf(receita.codigo.toString())
        val conteudo = ContentValues()
        conteudo.put("${DatabaseHelper.TRECEITA_NOME}", receita.nome)


        try {
            escrita.update(
                DatabaseHelper.TABELA_RECEITA,
                conteudo,
                "${DatabaseHelper.TRECEITA_CODIGO} = ?",
                args
            )
        } catch (e: Exception) {
            Log.i("db", "Não foi possível atualizar receita.")
            return false
        }
        Log.i("db", "Receita atualizado com sucesso.")
        return true
    }


    override fun deletar(codigo: Int): Boolean {
        val args = arrayOf(codigo.toString())
        try {
            escrita.delete(
                DatabaseHelper.TABELA_RECEITA,
                "${DatabaseHelper.TRECEITA_CODIGO} = ?",
                args
            )
        } catch (e: Exception) {
            Log.i("db", "Erro ao deletar registro na tabela ${DatabaseHelper.TABELA_RECEITA}.")
            return false
        }
        Log.i("db", "Registro deletado com sucesso na tabela ${DatabaseHelper.TABELA_RECEITA}.")
        return true
    }


    override fun listar(): List<Receita> {
        val listaVets = ArrayList<Receita>()

        val sql =
            "SELECT * FROM ${DatabaseHelper.TABELA_RECEITA} ORDER BY ${DatabaseHelper.TINGREDIENTE_NOME} ;"
        val cursor = leitura.rawQuery(sql, null)

        //capturando os indices das colunas
        val iCodigo = cursor.getColumnIndex(DatabaseHelper.TRECEITA_CODIGO);
        val iNome = cursor.getColumnIndex(DatabaseHelper.TRECEITA_NOME);



        while (cursor.moveToNext()) {
            val codVet = cursor.getInt(iCodigo)
            val nome = cursor.getString(iNome)


            listaVets.add(
                Receita(codVet, nome)
            )
            Log.i("db", "Listagem retornada. \n")
        }
        return listaVets
    }

}
