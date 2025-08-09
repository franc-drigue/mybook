package com.example.mybooks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.mybooks.entity.BookEntity
import com.example.mybooks.repository.BookRepository
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    //private val _books = MutableLiveData<List<BookEntity>>();

    private val repository: BookRepository = BookRepository.getInstance(application.applicationContext)

    val books: LiveData<List<BookEntity>> = repository.getAllBooks().asLiveData();

//    fun getAllBooks() {
//      _books.value = repository.getAllBooks();
//    }

    fun favorite(bookId: Int) {
        viewModelScope.launch {
            repository.toggleFavoriteStatus(bookId)
        }
    }
}