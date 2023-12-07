package com.example.clinicaveterinaria.database

import com.example.clinicaveterinaria.model.Receita

interface IReceitaDao {
    fun salvar( receita: Receita ): Boolean
    fun atualizar( receita: Receita ): Boolean
    fun deletar( id: Int ): Boolean
    fun listar(): List<Receita>
}