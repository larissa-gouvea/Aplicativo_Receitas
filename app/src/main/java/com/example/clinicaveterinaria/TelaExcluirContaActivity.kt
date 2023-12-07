package com.example.clinicaveterinaria

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

lateinit var ExcluirBtnRetornar: Button
lateinit var ExcluirBtnExcluirConta: Button

class TelaExcluirContaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_excluir_conta)

        ExcluirBtnRetornar = findViewById(R.id.excluir_btn_retornar)
        ExcluirBtnExcluirConta = findViewById(R.id.excluir_btn_excluir_conta)


        ExcluirBtnRetornar.setOnClickListener {
            val intent = Intent(this, InformacoesContaActivity::class.java)
            startActivity(intent)
        }
        ExcluirBtnExcluirConta.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

    }
}