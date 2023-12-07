package com.example.clinicaveterinaria.fragments

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.clinicaveterinaria.CadastroCronogramaActivity
import com.example.clinicaveterinaria.adapter.CronogramaAdapter

import com.example.clinicaveterinaria.database.CronogramaDAO
import com.example.clinicaveterinaria.databinding.FragmentCronogramaBinding

import com.example.clinicaveterinaria.model.Cronograma


class CronogramaFragment : Fragment() {

    private lateinit var binding: FragmentCronogramaBinding
    private var listaVets = emptyList<Cronograma>()
    private var vetAdapter: CronogramaAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCronogramaBinding.inflate(layoutInflater)
        val view = binding.root
        //implementar codigo aqui

        binding.fvetFABtnAdicionar.setOnClickListener {
            val intent = Intent(view.context, CadastroCronogramaActivity::class.java);
            startActivity(intent);
        }

        vetAdapter = CronogramaAdapter(
            { codigo -> confirmarExclusao(codigo) },
            { veterinario -> editar(veterinario) }
        )

        binding.recyclerCronograma.adapter = vetAdapter
        binding.recyclerCronograma.layoutManager = LinearLayoutManager(
            view.context,
            RecyclerView.VERTICAL,
            false
        )
        binding.recyclerCronograma.addItemDecoration(
            DividerItemDecoration(
                view.context,
                RecyclerView.VERTICAL
            )
        )
        return view
    }

    private fun editar(cronograma: Cronograma) {
        val intent = Intent(requireContext(), CadastroCronogramaActivity::class.java);
        intent.putExtra("cronograma", cronograma)
        startActivity(intent);
    }

    private fun confirmarExclusao(codigo: Int) {
        val alertBuilder = AlertDialog.Builder(requireContext())
        alertBuilder.setTitle("Confimar exclusão")
        alertBuilder.setMessage("Deseja excluir o cronograma?")
        alertBuilder.setPositiveButton("Sim") { _, _ ->
            val vetDAO = CronogramaDAO(requireContext())
            vetDAO.deletar(codigo)
            atualizarListaVet()
        }
        alertBuilder.setNegativeButton("Não") { _, _ -> }
        alertBuilder.create().show()
    }

    private fun atualizarListaVet() {
        val vetDao = CronogramaDAO(requireContext())
        listaVets = vetDao.listar()
        vetAdapter?.adicionarLista(listaVets)
    }

    override fun onStart() {
        super.onStart()
        atualizarListaVet()
    }

}