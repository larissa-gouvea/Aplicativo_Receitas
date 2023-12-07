package com.example.clinicaveterinaria

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

lateinit var logEmail:TextInputEditText
lateinit var logSenha:TextInputEditText
lateinit var logBtnLogar:Button
lateinit var logBtnCadastrar:Button


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        logEmail = findViewById(R.id.logu_itxt_email)
        logSenha = findViewById(R.id.logu_itxt_senha)
        logBtnLogar = findViewById(R.id.logu_btn_logar)
        logBtnCadastrar = findViewById(R.id.logu_btn_cadastrar)

        logBtnLogar.setOnClickListener {
            val intent = Intent(this, TipoComidaActivity::class.java)
            startActivity(intent)
        }
        logBtnCadastrar.setOnClickListener {
            val intent = Intent(this, CadastroUserActivity::class.java)
            startActivity(intent)

        }



    }
}