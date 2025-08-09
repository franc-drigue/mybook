package com.example.mybooks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mybooks.entity.BookEntity
import com.example.mybooks.repository.BookRepository
import kotlinx.coroutines.launch

class DetailsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: BookRepository = BookRepository.getInstance(application.applicationContext);

    private val _book = MutableLiveData<BookEntity>();
    val book: LiveData<BookEntity> = _book;

    private val _bookRemove = MutableLiveData<Boolean>();
    val bookRemove: LiveData<Boolean> = _bookRemove;


    fun getBookById(bookId: Int) {
        viewModelScope.launch {
            _book.value = repository.getBookById(bookId)
        }
    }

    fun deleteBook(bookId: Int) {
        viewModelScope.launch {
            _bookRemove.value = repository.deleteBook(bookId)
        }
    }

    fun favorite(bookId: Int) {
        viewModelScope.launch {
            repository.toggleFavoriteStatus(bookId)
        }
    }
}