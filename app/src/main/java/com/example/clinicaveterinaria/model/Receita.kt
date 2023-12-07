package com.example.clinicaveterinaria.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Receita(
    var codigo: Int,
    var nome: String
) : Parcelable

