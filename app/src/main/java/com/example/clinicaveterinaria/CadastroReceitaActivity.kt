package com.example.clinicaveterinaria

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.clinicaveterinaria.database.ReceitaDAO
import com.example.clinicaveterinaria.databinding.ActivityCadastroReceitaBinding
import com.example.clinicaveterinaria.model.Receita

class CadastroReceitaActivity : AppCompatActivity() {

    private val binding by lazy { ActivityCadastroReceitaBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //implementar codigo aqui

        //recuperar veterinario
        var receita: Receita? = null
        val bundle = intent.extras

        if (bundle != null) {
            if (Build.VERSION.SDK_INT >= 33) {
                receita = bundle.getParcelable("receita", Receita::class.java)
                if (receita != null) {
                    val codigo = receita.codigo
                    binding.cadReceitaTxtNome.setText(receita.nome)
                }
            } else {
                receita = bundle.getParcelable("receita")
                if (receita != null) {
                    val codigo = receita.codigo
                    binding.cadReceitaTxtNome.setText(receita.nome)
                }
            }
        }


        binding.cadReceitaBtnAcao.setOnClickListener {
            if (receita != null) {
                editar(receita)
            } else {
                salvar()
            }
        }
    }


    private fun salvar() {
        val vetDao = ReceitaDAO(this)
        val receita = Receita(
            -1,
            binding.cadReceitaTxtNome.text.toString(),


            )
        if (vetDao.salvar(receita)) {
            Log.i("db", "Receita ${receita.nome} salvo com sucesso.")
            finish()
        } else {
            Log.i("db", "Erro ao salver ${receita.nome}.")
        }
    }

    private fun editar(receita: Receita) {
        val codigo:Int = receita.codigo
        val nome = binding.cadReceitaTxtNome.text.toString()



        val receitaAtualizada = Receita( codigo, nome)

        val vetDao = ReceitaDAO(this)

        if (vetDao.atualizar(receitaAtualizada)) {
            Log.i("database", "Veterinario ${receitaAtualizada.nome} editado com sucesso.")
            finish()
        } else {
            Log.i("database", "Erro ao editar ${receitaAtualizada.nome}.")
        }
    }

}