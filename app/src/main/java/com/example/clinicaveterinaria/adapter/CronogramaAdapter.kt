package com.example.clinicaveterinaria.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.*
import com.example.clinicaveterinaria.databinding.ItemListaCronogramaBinding
import com.example.clinicaveterinaria.model.Cronograma

class CronogramaAdapter(
    val onClickExcluir: (Int) -> Unit,
    val onClickEditar: (Cronograma) -> Unit
) : Adapter<CronogramaAdapter.CronogramaViewHolder>() {

    private var listaCronogr: List<Cronograma> = emptyList()
    fun adicionarLista(lista:List<Cronograma>){
        this.listaCronogr = lista
        notifyDataSetChanged()
    }

    inner class CronogramaViewHolder(itemListaCronogramaBinding: ItemListaCronogramaBinding)
        : ViewHolder(itemListaCronogramaBinding.root){

        private val binding: ItemListaCronogramaBinding
        init {
            binding = itemListaCronogramaBinding
        }

        fun bind(cronograma: Cronograma){
            binding.itvetTxtvNome.text = cronograma.nome
            binding.itvetTxtvEspecial.text = cronograma.dia


            binding.ibtnDeletarVet.setOnClickListener {
                onClickExcluir(cronograma.codigo)
            }
            binding.ibtnEditarVet.setOnClickListener {
                onClickEditar(cronograma)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CronogramaViewHolder {
        val layoutInflater = LayoutInflater.from(
            parent.context
        )
        val itemListaCronogramaBinding = ItemListaCronogramaBinding.inflate(
            layoutInflater,
            parent,
            false
        )
        return CronogramaViewHolder(itemListaCronogramaBinding)
    }

    override fun onBindViewHolder(holder: CronogramaViewHolder, position: Int) {
        var cronogr = listaCronogr[position]
        holder.bind(cronogr)
    }

    override fun getItemCount(): Int {
        return listaCronogr.size
    }

}

