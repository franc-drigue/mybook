package com.example.mybooks.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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

        setListeners();
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

        viewModel.bookRemove.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(
                    requireContext(),
                    R.string.msg_removed_successfully,
                    Toast.LENGTH_SHORT
                ).show()

                requireActivity().supportFragmentManager.popBackStack()
            }
        }
    }

    private fun setGenreBackground(genre: String) {
        when (genre) {
            "Terror" -> binding.categoryGenre.setBackgroundResource(R.drawable.rounded_label_red)
            "Fantasia" -> binding.categoryGenre.setBackgroundResource(R.drawable.rounded_label_fantasy)
            else -> binding.categoryGenre.setBackgroundResource(R.drawable.rounded_label_teal)
        }
    }

    private fun setListeners() {
        binding.icBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        binding.buttonRemoveBook.setOnClickListener { handleRemove() }

        binding.checkboxIsFavorite.setOnClickListener { handleFavorite() }
    }

    private fun handleRemove() {
        val builder = AlertDialog.Builder(requireContext());
        builder.setMessage(getString(R.string.dialog_message_delete_item))
        builder.setPositiveButton(getString(R.string.dialog_positive_button_yes)) { _, _ ->
            //Implementar remoção
            viewModel.deleteBook(bookId)
        }
        builder.setNegativeButton(getString(R.string.dialog_negative_button_no)) { dialog, _ ->
            dialog.dismiss()
        }
        builder.create().show()
    }

    private fun handleFavorite() {
        viewModel.favorite(bookId)
    }
}