package com.example.clinicaveterinaria.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.*
import com.example.clinicaveterinaria.databinding.ItemListaReceitaBinding
import com.example.clinicaveterinaria.model.Receita  // Change import here

class ReceitaAdapter(
    val onClickExcluir: (Int) -> Unit,
    val onClickEditar: (Receita) -> Unit, // Change parameter type here
    val onClickDetalhes: (Receita) -> Unit
) : Adapter<ReceitaAdapter.ReceitaViewHolder>() {

    private var listaReceita: List<Receita> = emptyList()  // Change variable name here
    fun adicionarLista(lista: List<Receita>) {
        this.listaReceita = lista
        notifyDataSetChanged()
    }

    inner class ReceitaViewHolder(itemListaReceitaBinding: ItemListaReceitaBinding)
        : ViewHolder(itemListaReceitaBinding.root) {

        private val binding: ItemListaReceitaBinding
        init {
            binding = itemListaReceitaBinding
        }

        fun bind(receita: Receita) {
            binding.itvetTxtvNome.text = receita.nome

            binding.ibtnDeletarVet.setOnClickListener {
                onClickExcluir(receita.codigo)
            }
            binding.ibtnEditarVet.setOnClickListener {
                onClickEditar(receita)
            }

            // Adicione o listener para o clique no nome
            binding.itvetTxtvNome.setOnClickListener {
                onClickDetalhes(receita)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceitaViewHolder {
        val layoutInflater = LayoutInflater.from(
            parent.context
        )
        val itemListaReceitaBinding = ItemListaReceitaBinding.inflate(
            layoutInflater,
            parent,
            false
        )
        return ReceitaViewHolder(itemListaReceitaBinding)
    }

    override fun onBindViewHolder(holder: ReceitaViewHolder, position: Int) {
        var receita = listaReceita[position]
        holder.bind(receita)
    }

    override fun getItemCount(): Int {
        return listaReceita.size
    }
}
