package com.example.clinicaveterinaria.database

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.example.clinicaveterinaria.model.Cronograma


class CronogramaDAO(context: Context) : ICronogramaDao {

    private val escrita = DatabaseHelper(context).writableDatabase
    private val leitura = DatabaseHelper(context).readableDatabase

    override fun salvar(cronograma: Cronograma): Boolean {
        val valores = ContentValues()
        valores.put("${DatabaseHelper.TCRONOGRAMA_NOME}", cronograma.nome);
        valores.put("${DatabaseHelper.TCRONOGRAMA_DIA}", cronograma.dia);

        try {
            escrita.insert(
                DatabaseHelper.TABELA_CRONOGRAMA,
                null,
                valores
            )
            Log.i("db", "Cronograma inserido na tabela. ${DatabaseHelper.TABELA_CRONOGRAMA}.")
        } catch (e: Exception) {
            Log.i("db", "Erro ao inserir cronograma na tabela ${DatabaseHelper.TABELA_CRONOGRAMA}.")
            return false
        }
        return true
    }


    override fun atualizar(cronograma: Cronograma): Boolean {
        val args = arrayOf(cronograma.codigo.toString())
        val conteudo = ContentValues()
        conteudo.put("${DatabaseHelper.TCRONOGRAMA_NOME}",cronograma.nome)
        conteudo.put("${DatabaseHelper.TCRONOGRAMA_DIA}",cronograma.dia)

        try {
            escrita.update(
                DatabaseHelper.TABELA_CRONOGRAMA,
                conteudo,
                "${DatabaseHelper.TCRONOGRAMA_CODIGO} = ?",
                args
            )
        } catch (e: Exception) {
            Log.i("db","Não foi possível atualizar cronograma.")
            return false
        }
        Log.i("db","Cronograma atualizado com sucesso.")
        return true
    }


    override fun deletar(codigo: Int): Boolean {
        val args = arrayOf(codigo.toString())
        try {
            escrita.delete(
                DatabaseHelper.TABELA_CRONOGRAMA,
                "${DatabaseHelper.TCRONOGRAMA_CODIGO} = ?",
                args
            )
        } catch (e: Exception) {
            Log.i("db","Erro ao deletar registro na tabela ${DatabaseHelper.TABELA_CRONOGRAMA}.")
            return false
        }
        Log.i("db","Registro deletado com sucesso na tabela ${DatabaseHelper.TABELA_CRONOGRAMA}.")
        return true
    }


    override fun listar(): List<Cronograma> {
        val listaVets = ArrayList<Cronograma>()

        val sql = "SELECT * FROM ${DatabaseHelper.TABELA_CRONOGRAMA} ORDER BY ${DatabaseHelper.TCRONOGRAMA_NOME} ;"
        val cursor = leitura.rawQuery(sql,null)

        //capturando os indices das colunas
        val iCodigo = cursor.getColumnIndex(DatabaseHelper.TCRONOGRAMA_CODIGO);
        val iNome = cursor.getColumnIndex(DatabaseHelper.TCRONOGRAMA_NOME);
        val iDia = cursor.getColumnIndex(DatabaseHelper.TCRONOGRAMA_DIA);

        while (cursor.moveToNext()){
            val codVet = cursor.getInt(iCodigo)
            val nome = cursor.getString(iNome)
            val dia = cursor.getString(iDia)

            listaVets.add(
                Cronograma(codVet, nome, dia )
            )
            Log.i("db","Listagem retornada. \n")
        }
        return listaVets
    }

}