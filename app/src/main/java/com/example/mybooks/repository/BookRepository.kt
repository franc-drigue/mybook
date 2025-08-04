package com.example.mybooks.repository

import android.content.Context
import com.example.mybooks.entity.BookEntity


class BookRepository private constructor(context: Context) {

    private var database = BookDatabase.getDatabase(context).bookDAO()

//    private val books = mutableListOf<BookEntity>()

//    init {
//        database.readableDatabase
//    }

    companion object {
        private lateinit var instance: BookRepository

        fun getInstance(context: Context): BookRepository {
            synchronized(this) {
                if (!::instance.isInitialized) {
                    instance = BookRepository(context)
                }
            }
            return instance
        }
    }


    // Retorna todos os livros armazenados
    fun getAllBooks(): List<BookEntity> {
//        val db = database.readableDatabase
//        val books = mutableListOf<BookEntity>()
//
//        val cursor = db.query(DatabaseConsts.BOOK.TABLE_NAME, null, null, null, null, null, null)
//
//        cursor?.use {
//            while (it.moveToNext()) {
//                val id = it.getInt(it.getColumnIndexOrThrow(DatabaseConsts.BOOK.COLUMNS.ID))
//                val title =
//                    it.getString(it.getColumnIndexOrThrow(DatabaseConsts.BOOK.COLUMNS.TITLE))
//                val author =
//                    it.getString(it.getColumnIndexOrThrow(DatabaseConsts.BOOK.COLUMNS.AUTHOR))
//                val genre =
//                    it.getString(it.getColumnIndexOrThrow(DatabaseConsts.BOOK.COLUMNS.GENRE))
//                val favorite =
//                    it.getInt(it.getColumnIndexOrThrow(DatabaseConsts.BOOK.COLUMNS.FAVORITE))
//                books.add(BookEntity(id, title, author, favorite == 1, genre))
//            }
//        }
//
//        db.close()
//        return books

        return database.getAllBooks()
    }


    // Retorna todos os livros marcados como favoritos
    fun getFavoriteBooks(): List<BookEntity> {
//        val db = database.readableDatabase
//        val books = mutableListOf<BookEntity>()
//
//        val cursor = db.query(
//            DatabaseConsts.BOOK.TABLE_NAME,
//            null,
//            "${DatabaseConsts.BOOK.COLUMNS.FAVORITE} = ?",
//            arrayOf("1"),
//            null,
//            null,
//            null
//        )
//
//        cursor?.use {
//            while (it.moveToNext()) {
//                val id = it.getInt(it.getColumnIndexOrThrow(DatabaseConsts.BOOK.COLUMNS.ID))
//                val title =
//                    it.getString(it.getColumnIndexOrThrow(DatabaseConsts.BOOK.COLUMNS.TITLE))
//                val author =
//                    it.getString(it.getColumnIndexOrThrow(DatabaseConsts.BOOK.COLUMNS.AUTHOR))
//                val genre =
//                    it.getString(it.getColumnIndexOrThrow(DatabaseConsts.BOOK.COLUMNS.GENRE))
//                val favorite =
//                    it.getInt(it.getColumnIndexOrThrow(DatabaseConsts.BOOK.COLUMNS.FAVORITE))
//                books.add(BookEntity(id, title, author, favorite == 1, genre))
//            }
//        }
//
//        db.close()
//        return books

        return  database.getFavoriteBooks()
    }


    // Busca um livro pelo o id
    fun getBookById(id: Int): BookEntity {
//        val db = database.readableDatabase
//        var book: BookEntity? = null
//
//        val cursor = db.query(
//            DatabaseConsts.BOOK.TABLE_NAME,
//            null,
//            "${DatabaseConsts.BOOK.COLUMNS.ID} = ?",
//            arrayOf(id.toString()),
//            null,
//            null,
//            null
//        )
//
//        cursor?.use {
//            if (it.moveToNext()) {
//                val id = it.getInt(it.getColumnIndexOrThrow(DatabaseConsts.BOOK.COLUMNS.ID))
//                val title =
//                    it.getString(it.getColumnIndexOrThrow(DatabaseConsts.BOOK.COLUMNS.TITLE))
//                val author =
//                    it.getString(it.getColumnIndexOrThrow(DatabaseConsts.BOOK.COLUMNS.AUTHOR))
//                val genre =
//                    it.getString(it.getColumnIndexOrThrow(DatabaseConsts.BOOK.COLUMNS.GENRE))
//                val favorite =
//                    it.getInt(it.getColumnIndexOrThrow(DatabaseConsts.BOOK.COLUMNS.FAVORITE))
//                book = BookEntity(id, title, author, favorite == 1, genre)
//            }
//        }
//
//        db.close()
//        return book

        return database.getBookById(id)
    }


    // Remove um livro pelo o id
    fun deleteBook(id: Int): Boolean {
//        val db = database.writableDatabase
//
//        val rawDeleted = db.delete(
//            DatabaseConsts.BOOK.TABLE_NAME,
//            "${DatabaseConsts.BOOK.COLUMNS.ID} = ?",
//            arrayOf(id.toString())
//        )
//
//        db.close()
//
//        return rawDeleted > 0

        return database.delete(getBookById(id)) > 0
    }


    // Alterna entre true e false o atributo 'favorite'
    fun toggleFavoriteStatus(id: Int) {
//        val book = getBookById(id)
//        val newFavoriteStatus = if (book?.favorite == true) 0 else 1
//
//        val db = database.writableDatabase
//        val values = ContentValues().apply {
//            put(DatabaseConsts.BOOK.COLUMNS.FAVORITE, newFavoriteStatus)
//        }
//
//        db.update(
//            DatabaseConsts.BOOK.TABLE_NAME,
//            values,
//            "${DatabaseConsts.BOOK.COLUMNS.ID} = ?",
//            arrayOf(id.toString())
//        )
//
//        db.close()

        val book = getBookById(id)
        book.favorite = !book.favorite
        database.update(book)
    }
}