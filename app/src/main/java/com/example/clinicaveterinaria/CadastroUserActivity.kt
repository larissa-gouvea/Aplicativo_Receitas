package com.example.clinicaveterinaria

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

lateinit var caduBtnCancelar:Button
lateinit var caduBtnCadastrar:Button


class CadastroUserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_user)

        caduBtnCancelar = findViewById(R.id.cadu_btn_cancelar)
        caduBtnCadastrar = findViewById(R.id.cadu_btn_cadastrar)

        caduBtnCancelar.setOnClickListener {
            finish()
        }

        caduBtnCadastrar.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

    }
}