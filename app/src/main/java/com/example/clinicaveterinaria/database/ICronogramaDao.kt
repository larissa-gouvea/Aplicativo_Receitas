package com.example.clinicaveterinaria.database

import com.example.clinicaveterinaria.model.Cronograma

interface ICronogramaDao {
    fun salvar( cronograma: Cronograma ): Boolean
    fun atualizar( cronograma: Cronograma ): Boolean
    fun deletar( id: Int ): Boolean
    fun listar(): List<Cronograma>
}
