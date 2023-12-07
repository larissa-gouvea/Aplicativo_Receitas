package com.example.clinicaveterinaria

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.clinicaveterinaria.database.CronogramaDAO
import com.example.clinicaveterinaria.databinding.ActivityCadastroCronogramaBinding
import com.example.clinicaveterinaria.model.Cronograma

class CadastroCronogramaActivity : AppCompatActivity() {

    private val binding by lazy { ActivityCadastroCronogramaBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //implementar codigo aqui

        //recuperar veterinario
        var cronograma: Cronograma? = null
        val bundle = intent.extras

        if (bundle != null) {
            if (Build.VERSION.SDK_INT >= 33) {
                cronograma = bundle.getParcelable("cronograma", Cronograma::class.java)
                if (cronograma != null){
                    val codigo = cronograma.codigo
                    binding.cadVetETxtNome.setText(cronograma.nome)
                    binding.cadVetETxtEsp.setText(cronograma.dia)

                }
            }else{
                cronograma = bundle.getParcelable("cronograma")
                if (cronograma != null){
                    val codigo = cronograma.codigo
                    binding.cadVetETxtNome.setText(cronograma.nome)

                    binding.cadVetETxtEsp.setText(cronograma.dia)

                }
            }
        }


        binding.cadVetBtnAcao.setOnClickListener {
            if (cronograma != null) {
                editar(cronograma)
            } else {
                salvar()
            }
        }
    }


    private fun salvar() {
        val vetDao = CronogramaDAO(this)
        val cronograma = Cronograma(
            -1,
            binding.cadVetETxtNome.text.toString(),
            binding.cadVetETxtEsp.text.toString(),

            )
        if (vetDao.salvar(cronograma)) {
            Log.i("db", "Cronograma ${cronograma.nome} salvo com sucesso.")
            finish()
        } else {
            Log.i("db", "Erro ao salver ${cronograma.nome}.")
        }
    }

    private fun editar(cronograma: Cronograma) {
        val codigo:Int = cronograma.codigo
        val nome = binding.cadVetETxtNome.text.toString()
        val dia = binding.cadVetETxtEsp.text.toString()


        val cronogramaAtualizado = Cronograma( codigo, nome, dia)

        val vetDao = CronogramaDAO(this)

        if (vetDao.atualizar(cronogramaAtualizado)) {
            Log.i("database", "Veterinario ${cronogramaAtualizado.nome} editado com sucesso.")
            finish()
        } else {
            Log.i("database", "Erro ao editar ${cronogramaAtualizado.nome}.")
        }
    }

}
