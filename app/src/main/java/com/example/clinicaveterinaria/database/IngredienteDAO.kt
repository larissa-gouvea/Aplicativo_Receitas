package com.example.clinicaveterinaria.database

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.example.clinicaveterinaria.model.Ingrediente


class IngredienteDAO(context: Context) : IIngredienteDao {

    private val escrita = DatabaseHelper(context).writableDatabase
    private val leitura = DatabaseHelper(context).readableDatabase

    override fun salvar(ingrediente: Ingrediente): Boolean {
        val valores = ContentValues()
        valores.put("${DatabaseHelper.TINGREDIENTE_NOME}", ingrediente.nome);
        valores.put("${DatabaseHelper.TINGREDIENTE_QUANTIDADE}", ingrediente.quantidade);

        try {
            escrita.insert(
                DatabaseHelper.TABELA_INGREDIENTE,
                null,
                valores
            )
            Log.i("db", "Ingrediente inserido na tabela. ${DatabaseHelper.TABELA_INGREDIENTE}.")
        } catch (e: Exception) {
            Log.i("db", "Erro ao inserir ingrediente na tabela ${DatabaseHelper.TABELA_INGREDIENTE}.")
            return false
        }
        return true
    }


    override fun atualizar(ingrediente: Ingrediente): Boolean {
        val args = arrayOf(ingrediente.codigo.toString())
        val conteudo = ContentValues()
        conteudo.put("${DatabaseHelper.TINGREDIENTE_NOME}", ingrediente.nome)
        conteudo.put("${DatabaseHelper.TINGREDIENTE_QUANTIDADE}", ingrediente.quantidade)

        try {
            escrita.update(
                DatabaseHelper.TABELA_INGREDIENTE,
                conteudo,
                "${DatabaseHelper.TINGREDIENTE_CODIGO} = ?",
                args
            )
        } catch (e: Exception) {
            Log.i("db", "Não foi possível atualizar ingrediente.")
            return false
        }
        Log.i("db", "Ingrediente atualizado com sucesso.")
        return true
    }


    override fun deletar(codigo: Int): Boolean {
        val args = arrayOf(codigo.toString())
        try {
            escrita.delete(
                DatabaseHelper.TABELA_INGREDIENTE,
                "${DatabaseHelper.TINGREDIENTE_CODIGO} = ?",
                args
            )
        } catch (e: Exception) {
            Log.i("db", "Erro ao deletar registro na tabela ${DatabaseHelper.TABELA_INGREDIENTE}.")
            return false
        }
        Log.i("db", "Registro deletado com sucesso na tabela ${DatabaseHelper.TABELA_INGREDIENTE}.")
        return true
    }


    override fun listar(): List<Ingrediente> {
        val listaVets = ArrayList<Ingrediente>()

        val sql =
            "SELECT * FROM ${DatabaseHelper.TABELA_INGREDIENTE} ORDER BY ${DatabaseHelper.TINGREDIENTE_NOME} ;"
        val cursor = leitura.rawQuery(sql, null)

        //capturando os indices das colunas
        val iCodigo = cursor.getColumnIndex(DatabaseHelper.TINGREDIENTE_CODIGO);
        val iNome = cursor.getColumnIndex(DatabaseHelper.TINGREDIENTE_NOME);
        val iEspec = cursor.getColumnIndex(DatabaseHelper.TINGREDIENTE_QUANTIDADE);


        while (cursor.moveToNext()) {
            val codVet = cursor.getInt(iCodigo)
            val nome = cursor.getString(iNome)
            val especialidade = cursor.getString(iEspec)

            listaVets.add(
                Ingrediente(codVet, nome, especialidade)
            )
            Log.i("db", "Listagem retornada. \n")
        }
        return listaVets
    }

}
