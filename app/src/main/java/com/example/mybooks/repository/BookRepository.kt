package com.example.mybooks.repository

import android.content.Context
import com.example.mybooks.consts.DatabaseConsts
import com.example.mybooks.entity.BookEntity


class BookRepository private constructor(context: Context){

    private var database: BookDataBaseHelper =  BookDataBaseHelper(context)

    private val books = mutableListOf<BookEntity>()

    init {
        database.readableDatabase
    }

    companion object{
        private lateinit var instance: BookRepository

        fun getInstance(context: Context): BookRepository {
            synchronized(this) {
                if(!::instance.isInitialized) {
                    instance = BookRepository(context)
                }
            }
            return instance
        }
    }



    fun getAllBooks(): List<BookEntity>{
        val db = database.readableDatabase
        val books = mutableListOf<BookEntity>()

        val cursor = db.query(DatabaseConsts.BOOK.TABLE_NAME, null, null, null, null, null, null)

        cursor?.use {
            while (it.moveToNext()) {
                val id = it.getInt(it.getColumnIndexOrThrow(DatabaseConsts.BOOK.COLUMNS.ID))
                val title = it.getString(it.getColumnIndexOrThrow(DatabaseConsts.BOOK.COLUMNS.TITLE))
                val author = it.getString(it.getColumnIndexOrThrow(DatabaseConsts.BOOK.COLUMNS.AUTHOR))
                val genre = it.getString(it.getColumnIndexOrThrow(DatabaseConsts.BOOK.COLUMNS.GENRE))
                val favorite = it.getInt(it.getColumnIndexOrThrow(DatabaseConsts.BOOK.COLUMNS.FAVORITE))
                books.add(BookEntity(id, title, author, favorite == 1, genre))
            }
        }

        db.close()
        return books
    }

    fun getFavoriteBooks(): List<BookEntity>{
       return  books.filter { it.favorite === true }
    }

    fun getBookById(id: Int): BookEntity? {
      return books.find { it.id === id }
    }

    fun deleteBook(id: Int): Boolean {
      return books.removeIf { it.id == id }
    }

    fun toggleFavoriteStatus(id: Int){
        val book = books.find {it.id === id}
        if (book != null) {
           book.favorite = !book.favorite
        }
    }
}