package com.example.clinicaveterinaria

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

lateinit var receitaBtnRetornar: Button

class TelaReceitaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_receita)
        receitaBtnRetornar = findViewById(R.id.btn_retornar)


        receitaBtnRetornar.setOnClickListener {
            val intent = Intent(this, TelaListaReceitasActivity::class.java)
            startActivity(intent)
        }


    }

}