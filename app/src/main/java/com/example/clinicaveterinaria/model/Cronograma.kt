package com.example.clinicaveterinaria.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cronograma(
    var codigo: Int,
    var nome: String,
    var dia: String
    ) : Parcelable
