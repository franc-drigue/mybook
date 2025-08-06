package com.example.mybooks.repository

import android.content.Context
import com.example.mybooks.entity.BookEntity
import kotlinx.coroutines.flow.Flow


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


//    private fun getInitalBooks(): List<BookEntity> {
//        return listOf(
//            BookEntity(1, "To Kill a Mockingbird", "Harper Lee", true, "Ficção"),
//            BookEntity(2, "Dom Casmurro", "Machado de Assis", false, "Romance"),
//            BookEntity(3, "O Hobbit", "J.R.R. Tolkien", true, "Fantasia"),
//            BookEntity(4, "Cem Anos de Solidão", "Gabriel García Márquez", false, "Romance"),
//            BookEntity(5, "O Pequeno Príncipe", "Antoine de Saint-Exupéry", false, "Fantasia"),
//            BookEntity(6, "Crime e Castigo", "Fiódor Dostoiévski", false, "Ficção policial"),
//            BookEntity(7, "Frankenstein", "Mary Shelley", false, "Terror"),
//            BookEntity(8, "Harry Potter e a Pedra Filosofal", "J.K. Rowling", false, "Fantasia"),
//            BookEntity(9, "Neuromancer", "William Gibson", false, "Cyberpunk"),
//            BookEntity(10, "Senhor dos Anéis", "J.R.R. Tolkien", false, "Fantasia"),
//            BookEntity(11, "Drácula", "Bram Stoker", false, "Terror"),
//            BookEntity(12, "Orgulho e Preconceito", "Jane Austen", false, "Romance"),
//            BookEntity(13, "Harry Potter e a Câmara Secreta", "J.K. Rowling", false, "Fantasia"),
//            BookEntity(14, "As Crônicas de Nárnia", "C.S. Lewis", false, "Fantasia"),
//            BookEntity(15, "O Código Da Vinci", "Dan Brown", false, "Mistério"),
//            BookEntity(16, "It: A Coisa", "Stephen King", false, "Terror"),
//            BookEntity(17, "Moby Dick", "Herman Melville", true, "Aventura"),
//            BookEntity(18, "O Nome do Vento", "Patrick Rothfuss", true, "Fantasia"),
//            BookEntity(19, "O Conde de Monte Cristo", "Alexandre Dumas", true, "Aventura"),
//            BookEntity(20, "Os Miseráveis", "Victor Hugo", false, "Romance")
//        )
//    }



    // Retorna todos os livros armazenados
    fun getAllBooks(): Flow<List<BookEntity>> {
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

        return database.getFavoriteBooks()
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