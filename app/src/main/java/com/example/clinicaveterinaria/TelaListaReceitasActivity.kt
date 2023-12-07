package com.example.clinicaveterinaria

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

lateinit var Btn_Retornar:Button
lateinit var Btn_Pontos:Button
lateinit var TextBolo:TextView
lateinit var TextPudim:TextView
lateinit var TextBrigadeiro:TextView

class TelaListaReceitasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_lista_receitas)
        Btn_Retornar = findViewById(R.id.btn_retornar)

        Btn_Pontos = findViewById(R.id.btn_pontos)


        Btn_Retornar.setOnClickListener {
            val intent = Intent(this, TipoComidaActivity::class.java)
            startActivity(intent)
        }
        Btn_Pontos.setOnClickListener {
            val intent = Intent(this, InformacoesContaActivity::class.java)
            startActivity(intent)
        }



    }

}