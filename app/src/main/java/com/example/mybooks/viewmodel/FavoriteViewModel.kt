package com.example.mybooks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.mybooks.entity.BookEntity
import com.example.mybooks.repository.BookRepository
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: BookRepository = BookRepository.getInstance(application.applicationContext);

    val books: LiveData<List<BookEntity>> = repository.getFavoriteBooks().asLiveData();

    fun favorite(bookId: Int) {
        viewModelScope.launch {
            repository.toggleFavoriteStatus(bookId)
        }
    }
}