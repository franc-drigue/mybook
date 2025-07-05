package com.example.mybooks.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mybooks.entity.BookEntity
import com.example.mybooks.repository.BookRepository

class FavoriteViewModel : ViewModel() {

    private val _books = MutableLiveData<List<BookEntity>>();

    val books: LiveData<List<BookEntity>> = _books;

    private val repository: BookRepository = BookRepository.getInstance();

    fun getAllBooksFavorite() {
        _books.value = repository.getFavoriteBooks();
    }

    fun favorite(bookId: Int) {
        repository.toggleFavoriteStatus(bookId)
    }
}