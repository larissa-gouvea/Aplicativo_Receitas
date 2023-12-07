package com.example.clinicaveterinaria

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

lateinit var InfoBtnSair: Button
lateinit var InfoBtnTrocarDados: Button
lateinit var InfoBtnExcluirConta: Button
lateinit var InfoBtnRetornar: Button

class InformacoesContaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_informacoes_conta)

        InfoBtnSair = findViewById(R.id.info_btn_sair)
        InfoBtnTrocarDados = findViewById(R.id.info_btn_trocar_dados)
        InfoBtnExcluirConta = findViewById(R.id.btn_excluir_conta)
        InfoBtnRetornar = findViewById(R.id.info_btn_retornar)

        InfoBtnSair.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        InfoBtnTrocarDados.setOnClickListener {
            val intent = Intent(this, TelaTrocarDadosActivity::class.java)
            startActivity(intent)

        }
        InfoBtnExcluirConta.setOnClickListener {
            val intent = Intent(this, TelaExcluirContaActivity::class.java)
            startActivity(intent)

        }
        InfoBtnRetornar.setOnClickListener {
            val intent = Intent(this, TipoComidaActivity::class.java)
            startActivity(intent)

        }


    }
}