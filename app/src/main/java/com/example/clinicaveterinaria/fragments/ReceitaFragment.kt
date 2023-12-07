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
import com.example.clinicaveterinaria.CadastroReceitaActivity
import com.example.clinicaveterinaria.TelaReceitaActivity

import com.example.clinicaveterinaria.adapter.ReceitaAdapter
import com.example.clinicaveterinaria.database.ReceitaDAO
import com.example.clinicaveterinaria.databinding.FragmentReceitaBinding
import com.example.clinicaveterinaria.model.Receita

class ReceitaFragment : Fragment() {

    private lateinit var binding: FragmentReceitaBinding
    private var listaReceita = emptyList<Receita>()
    private var receitaAdapter: ReceitaAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReceitaBinding.inflate(layoutInflater)
        val view = binding.root
        //implementar codigo aqui

        binding.fvetFABtnAdicionar.setOnClickListener {
            val intent = Intent(view.context, CadastroReceitaActivity::class.java);
            startActivity(intent);
        }

        receitaAdapter = ReceitaAdapter(
            { codigo -> confirmarExclusao(codigo) },
            { rec -> editar(rec) },
            {receita -> abrirDetalhesReceita(receita)}

        )

        binding.recyclerReceita.adapter = receitaAdapter
        binding.recyclerReceita.layoutManager = LinearLayoutManager(
            view.context,
            RecyclerView.VERTICAL,
            false
        )
        binding.recyclerReceita.addItemDecoration(
            DividerItemDecoration(
                view.context,
                RecyclerView.VERTICAL
            )
        )
        return view
    }



    private fun editar(receita: Receita) {
        val intent = Intent(requireContext(), CadastroReceitaActivity::class.java);
        intent.putExtra("receita", receita)
        startActivity(intent);
    }

    private fun confirmarExclusao(codigo: Int) {
        val alertBuilder = AlertDialog.Builder(requireContext())
        alertBuilder.setTitle("Confimar exclusão")
        alertBuilder.setMessage("Deseja excluir o receita?")
        alertBuilder.setPositiveButton("Sim") { _, _ ->
            val vetDAO = ReceitaDAO(requireContext())
            vetDAO.deletar(codigo)
            atualizarListaReceita()
        }
        alertBuilder.setNegativeButton("Não") { _, _ -> }
        alertBuilder.create().show()
    }

    private fun atualizarListaReceita() {
        val vetDao = ReceitaDAO(requireContext())
        listaReceita = vetDao.listar()
        receitaAdapter?.adicionarLista(listaReceita)
    }

    private fun abrirDetalhesReceita(receita: Receita) {
        val intent = Intent(requireContext(), TelaReceitaActivity::class.java)
        intent.putExtra("receita", receita)
        startActivity(intent)
    }
    override fun onStart() {
        super.onStart()
        atualizarListaReceita()
    }

}