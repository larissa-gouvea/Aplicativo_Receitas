package com.example.clinicaveterinaria.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Ingrediente(
    var codigo: Int,
    var nome: String,
    var quantidade: String

) : Parcelable
