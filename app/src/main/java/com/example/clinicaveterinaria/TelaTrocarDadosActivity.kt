package com.example.clinicaveterinaria

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

lateinit var BtnRetornar: Button


class TelaTrocarDadosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_trocar_dados)

        BtnRetornar = findViewById(R.id.trocar_btn_retornar)


        BtnRetornar.setOnClickListener {
            val intent = Intent(this, InformacoesContaActivity::class.java)
            startActivity(intent)
        }

    }
}