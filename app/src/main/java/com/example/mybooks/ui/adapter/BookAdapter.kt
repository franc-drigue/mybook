package com.example.mybooks.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mybooks.databinding.ItemBookBinding
import com.example.mybooks.entity.BookEntity
import com.example.mybooks.ui.viewholder.BookViewHolder

class BookAdapter: RecyclerView.Adapter<BookViewHolder>() {

    private var bookList = listOf<BookEntity>()

    // cria o elemento de layout
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookViewHolder {
        val view = ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(view)
    }

    // Responsável em vincular os dados com o layout
    override fun onBindViewHolder(
        holder: BookViewHolder,
        position: Int
    ) {
        holder.bind(bookList[position])
    }

    // Retorna quantos elemetos existem na RecyclerView
    override fun getItemCount(): Int {
        //poderia usar o bookList.count( )
        return  bookList.size
    }

    fun updateBooks(list: List<BookEntity>) {
        bookList = list;
        notifyDataSetChanged();
    }
}