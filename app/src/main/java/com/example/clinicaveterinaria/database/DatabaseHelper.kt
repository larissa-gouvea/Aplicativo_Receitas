package com.example.clinicaveterinaria.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper (context : Context): SQLiteOpenHelper(
    //-> contexto       -> nome database
    //-> CursorFactory  -> versão database
    context, "appReceitas", null, 1
) {

    companion object{
        const val NOME_BANCO = "appReceitas"



        const val TABELA_INGREDIENTE = "IngredienteTable"
        const val TINGREDIENTE_CODIGO = "codigo"
        const val TINGREDIENTE_NOME = "nome"
        const val TINGREDIENTE_QUANTIDADE = "quantidade"

        const val TABELA_RECEITA = "ReceitaTable"
        const val TRECEITA_CODIGO = "codigo"
        const val TRECEITA_NOME = "nome"

        const val TABELA_CRONOGRAMA = "CronogramaTable"
        const val TCRONOGRAMA_CODIGO = "codigo"
        const val TCRONOGRAMA_NOME = "nome"
        const val TCRONOGRAMA_DIA = "dia"



    }

    override fun onCreate(db: SQLiteDatabase?) {

        Log.i("database", "Entrou em databasehelper")


        val sqlcronograma = "CREATE TABLE ${DatabaseHelper.TABELA_CRONOGRAMA} ("+
                "${DatabaseHelper.TCRONOGRAMA_CODIGO} integer PRIMARY KEY AUTOINCREMENT,"+
                "${DatabaseHelper.TCRONOGRAMA_NOME} varchar(100) NOT NULL,"+
                "${DatabaseHelper.TCRONOGRAMA_DIA} varchar(50) NOT NULL);"

        try {
            db?.execSQL(sqlcronograma)
            Log.i("db", "Sucesso ao criar a tabela ${DatabaseHelper.TABELA_CRONOGRAMA}.")
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("db", "Erro ao criar a tabela ${DatabaseHelper.TABELA_CRONOGRAMA}.")
        }


        val sqlvet = "CREATE TABLE ${DatabaseHelper.TABELA_INGREDIENTE} ("+
                "${DatabaseHelper.TINGREDIENTE_CODIGO} integer PRIMARY KEY AUTOINCREMENT,"+
                "${DatabaseHelper.TINGREDIENTE_NOME} varchar(100) NOT NULL,"+
                "${DatabaseHelper.TINGREDIENTE_QUANTIDADE} varchar(50) NOT NULL);"

        try {
            db?.execSQL(sqlvet)
            Log.i("db", "Sucesso ao criar a tabela ${DatabaseHelper.TABELA_INGREDIENTE}.")
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("db", "Erro ao criar a tabela ${DatabaseHelper.TABELA_INGREDIENTE}.")
        }


        val sqlreceita = "CREATE TABLE ${DatabaseHelper.TABELA_RECEITA} ("+
                "${DatabaseHelper.TRECEITA_CODIGO} integer PRIMARY KEY AUTOINCREMENT,"+
                "${DatabaseHelper.TRECEITA_NOME} varchar(100) NOT NULL);"

        try {
            db?.execSQL(sqlreceita)
            Log.i("db", "Sucesso ao criar a tabela ${DatabaseHelper.TABELA_RECEITA}.")
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("db", "Erro ao criar a tabela ${DatabaseHelper.TABELA_RECEITA}.")
        }


    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}