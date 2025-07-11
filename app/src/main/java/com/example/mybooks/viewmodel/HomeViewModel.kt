package com.example.mybooks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mybooks.entity.BookEntity
import com.example.mybooks.repository.BookRepository

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val _books = MutableLiveData<List<BookEntity>>();

    val books: LiveData<List<BookEntity>> = _books;

    private val repository: BookRepository = BookRepository.getInstance(application.applicationContext);

    fun getAllBooks() {
      _books.value = repository.getAllBooks();
    }

    fun favorite(bookId: Int) {
        repository.toggleFavoriteStatus(bookId)
    }
}