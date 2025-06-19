package com.example.mybooks.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mybooks.databinding.FragmentHomeBinding
import com.example.mybooks.ui.adapter.BookAdapter
import com.example.mybooks.viewmodels.HomeViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()

    //Forma de instanciar o adapter
    private val adapter: BookAdapter = BookAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.recyclerviewBooks.layoutManager = LinearLayoutManager(context)

        //adapter
        binding.recyclerviewBooks.adapter = adapter

        // função para buscar todos os livros, implementada no homeViewModal
        homeViewModel.getAllBooks();

        setObserver();

        return binding.root
    }

    private fun setObserver() {
        homeViewModel.books.observe(viewLifecycleOwner) {
            adapter.updateBooks(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}