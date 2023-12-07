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
import com.example.clinicaveterinaria.CadastroIngredienteActivity

import com.example.clinicaveterinaria.adapter.IngredienteAdapter
import com.example.clinicaveterinaria.database.IngredienteDAO
import com.example.clinicaveterinaria.databinding.FragmentIngredienteBinding
import com.example.clinicaveterinaria.model.Ingrediente

class IngredienteFragment : Fragment() {

    private lateinit var binding: FragmentIngredienteBinding
    private var listaIngredientes = emptyList<Ingrediente>()
    private var ingredienteAdapter: IngredienteAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIngredienteBinding.inflate(layoutInflater)
        val view = binding.root
        //implementar codigo aqui

        binding.fvetFABtnAdicionar.setOnClickListener {
            val intent = Intent(view.context, CadastroIngredienteActivity::class.java);
            startActivity(intent);
        }

        ingredienteAdapter = IngredienteAdapter(
            { codigo -> confirmarExclusao(codigo) },
            { veterinario -> editar(veterinario) }
        )

        binding.recyclerIngrediente.adapter = ingredienteAdapter
        binding.recyclerIngrediente.layoutManager = LinearLayoutManager(
            view.context,
            RecyclerView.VERTICAL,
            false
        )
        binding.recyclerIngrediente.addItemDecoration(
            DividerItemDecoration(
                view.context,
                RecyclerView.VERTICAL
            )
        )
        return view
    }

    private fun editar(ingrediente: Ingrediente) {
        val intent = Intent(requireContext(), CadastroIngredienteActivity::class.java);
        intent.putExtra("ingrediente", ingrediente)
        startActivity(intent);
    }

    private fun confirmarExclusao(codigo: Int) {
        val alertBuilder = AlertDialog.Builder(requireContext())
        alertBuilder.setTitle("Confimar exclusão")
        alertBuilder.setMessage("Deseja excluir o ingrediente?")
        alertBuilder.setPositiveButton("Sim") { _, _ ->
            val vetDAO = IngredienteDAO(requireContext())
            vetDAO.deletar(codigo)
            atualizarListaIngrediente()
        }
        alertBuilder.setNegativeButton("Não") { _, _ -> }
        alertBuilder.create().show()
    }

    private fun atualizarListaIngrediente() {
        val vetDao = IngredienteDAO(requireContext())
        listaIngredientes = vetDao.listar()
        ingredienteAdapter?.adicionarLista(listaIngredientes)
    }

    override fun onStart() {
        super.onStart()
        atualizarListaIngrediente()
    }

}