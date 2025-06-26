package com.example.mybooks.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.mybooks.R
import com.example.mybooks.consts.Consts
import com.example.mybooks.databinding.FragmentDetailsBinding
import com.example.mybooks.viewmodel.DetailsViewModel

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailsViewModel by viewModels()
    private var bookId = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)

        bookId = arguments?.getInt(Consts.KEY.BOOK_ID) ?: 0

        viewModel.getBookById(bookId);

        setClickButtonIsBack();
        setObserve();

        return binding.root
    }

    private fun setObserve() {
        viewModel.book.observe(viewLifecycleOwner) {
            binding.titleBook.text = it.title
            binding.categoryGenre.text = it.genre
            binding.authorName.text = it.author
            binding.checkboxIsFavorite.isChecked = it.favorite

            setGenreBackground(it.genre)
        }
    }

    private fun setGenreBackground(genre: String) {
        when(genre) {
            "Terror" -> binding.categoryGenre.setBackgroundResource(R.drawable.rounded_label_red)
            "Fantasia" -> binding.categoryGenre.setBackgroundResource(R.drawable.rounded_label_fantasy)
            else -> binding.categoryGenre.setBackgroundResource(R.drawable.rounded_label_teal)
        }
    }

    private fun setClickButtonIsBack(){
        binding.icBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}