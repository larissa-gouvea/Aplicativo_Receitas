package com.example.clinicaveterinaria.database

import com.example.clinicaveterinaria.model.Ingrediente

interface IIngredienteDao {
    fun salvar( ingrediente: Ingrediente ): Boolean
    fun atualizar( ingrediente: Ingrediente ): Boolean
    fun deletar( id: Int ): Boolean
    fun listar(): List<Ingrediente>
}