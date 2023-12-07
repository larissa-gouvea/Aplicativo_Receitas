package com.example.clinicaveterinaria

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.clinicaveterinaria.database.IngredienteDAO
import com.example.clinicaveterinaria.databinding.ActivityCadastroIngredienteBinding
import com.example.clinicaveterinaria.model.Ingrediente

class CadastroIngredienteActivity : AppCompatActivity() {

    private val binding by lazy { ActivityCadastroIngredienteBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //implementar codigo aqui

        //recuperar veterinario
        var ingrediente: Ingrediente? = null
        val bundle = intent.extras

        if (bundle != null) {
            if (Build.VERSION.SDK_INT >= 33) {
                ingrediente = bundle.getParcelable("ingrediente", Ingrediente::class.java)
                if (ingrediente != null){
                    val codigo = ingrediente.codigo
                    binding.cadVetETxtNome.setText(ingrediente.nome)
                    binding.cadVetETxtEsp.setText(ingrediente.quantidade)

                }
            }else{
                ingrediente = bundle.getParcelable("ingrediente")
                if (ingrediente != null){
                    val codigo = ingrediente.codigo
                    binding.cadVetETxtNome.setText(ingrediente.nome)

                    binding.cadVetETxtEsp.setText(ingrediente.quantidade)

                }
            }
        }


        binding.cadVetBtnAcao.setOnClickListener {
            if (ingrediente != null) {
                editar(ingrediente)
            } else {
                salvar()
            }
        }
    }


    private fun salvar() {
        val vetDao = IngredienteDAO(this)
        val ingrediente = Ingrediente(
            -1,
            binding.cadVetETxtNome.text.toString(),
            binding.cadVetETxtEsp.text.toString(),

        )
        if (vetDao.salvar(ingrediente)) {
            Log.i("db", "Ingrediente ${ingrediente.nome} salvo com sucesso.")
            finish()
        } else {
            Log.i("db", "Erro ao salver ${ingrediente.nome}.")
        }
    }

    private fun editar(ingrediente: Ingrediente) {
        val codigo:Int = ingrediente.codigo
        val nome = binding.cadVetETxtNome.text.toString()
        val especialidade = binding.cadVetETxtEsp.text.toString()


        val ingredienteAtualizado = Ingrediente( codigo, nome, especialidade)

        val vetDao = IngredienteDAO(this)

        if (vetDao.atualizar(ingredienteAtualizado)) {
            Log.i("database", "Veterinario ${ingredienteAtualizado.nome} editado com sucesso.")
            finish()
        } else {
            Log.i("database", "Erro ao editar ${ingredienteAtualizado.nome}.")
        }
    }

}