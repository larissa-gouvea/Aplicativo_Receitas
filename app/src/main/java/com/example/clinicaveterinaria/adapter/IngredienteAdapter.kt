package com.example.clinicaveterinaria.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.*
import com.example.clinicaveterinaria.databinding.ItemListaIngredienteBinding
import com.example.clinicaveterinaria.model.Ingrediente

class IngredienteAdapter(
    val onClickExcluir: (Int) -> Unit,
    val onClickEditar: (Ingrediente) -> Unit
) : Adapter<IngredienteAdapter.IngrViewHolder>() {

    private var listaIngrediente: List<Ingrediente> = emptyList()
    fun adicionarLista(lista:List<Ingrediente>){
        this.listaIngrediente = lista
        notifyDataSetChanged()
    }

    inner class IngrViewHolder(itemListaIngredienteBinding: ItemListaIngredienteBinding)
        : ViewHolder(itemListaIngredienteBinding.root){

        private val binding: ItemListaIngredienteBinding
        init {
            binding = itemListaIngredienteBinding
        }

        fun bind(ingrediente: Ingrediente){
            binding.itvetTxtvNome.text = ingrediente.nome
            binding.itvetTxtvEspecial.text = ingrediente.quantidade


            binding.ibtnDeletarVet.setOnClickListener {
                onClickExcluir(ingrediente.codigo)
            }
            binding.ibtnEditarVet.setOnClickListener {
                onClickEditar(ingrediente)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngrViewHolder {
        val layoutInflater = LayoutInflater.from(
            parent.context
        )
        val itemListaIngredienteBinding = ItemListaIngredienteBinding.inflate(
            layoutInflater,
            parent,
            false
        )
        return IngrViewHolder(itemListaIngredienteBinding)
    }

    override fun onBindViewHolder(holder: IngrViewHolder, position: Int) {
        var ingr = listaIngrediente[position]
        holder.bind(ingr)
    }

    override fun getItemCount(): Int {
        return listaIngrediente.size
    }

}
