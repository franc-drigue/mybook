package com.example.mybooks.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mybooks.R
import com.example.mybooks.consts.Consts
import com.example.mybooks.databinding.FragmentFavoriteBinding
import com.example.mybooks.listener.BookListener
import com.example.mybooks.ui.adapter.BookAdapter
import com.example.mybooks.viewmodel.FavoriteViewModel

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    // código que substitui a inicialização do viewModel dentro do inCreateView
    private val favoriteViewModel: FavoriteViewModel by viewModels()

    private val adapter: BookAdapter = BookAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val favoriteViewModel =
//            ViewModelProvider(this).get(FavoriteViewModel::class.java)

        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)

        binding.recyclerviewBooksFavorite.layoutManager = LinearLayoutManager(context)

        //adapter
        binding.recyclerviewBooksFavorite.adapter = adapter

        // função para buscar todos os livros, implementada no homeViewModal
        favoriteViewModel.getAllBooksFavorite();

        attachListener();

        setObserver();

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onResume() {
        super.onResume()
        favoriteViewModel.getAllBooksFavorite()
    }


    private fun attachListener() {
        adapter.attachListener(object: BookListener {
            override fun onClick(id: Int) {
                //Realizar navegação

                var bundle: Bundle = Bundle()
                bundle.putInt(Consts.KEY.BOOK_ID, id)

                findNavController().navigate(R.id.navigation_details, args = bundle)
            }

            override fun onFavoriteClick(id: Int) {
                favoriteViewModel.favorite(id);
                favoriteViewModel.getAllBooksFavorite();
            }
        })
    }

    private fun setObserver() {
        favoriteViewModel.books.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                binding.bookImg.visibility = View.VISIBLE
                binding.textviewNoBooks.visibility = View.VISIBLE
            }
            adapter.updateBooks(it)
        }
    }
}