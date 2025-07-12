package com.example.mybooks.repository

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.mybooks.consts.DatabaseConsts
import com.example.mybooks.entity.BookEntity


class BookDataBaseHelper(context: Context) :
    SQLiteOpenHelper(context, CREATE_TABLE, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        // Momento da criação do banco de dados
        // Essa função é executada apenas uma vez, quando o banco de dados ainda não foi criado
        db.execSQL(CREATE_DATABASE)

        insertBooks(db)
    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
        TODO("Not yet implemented")
    }

    companion object {
        private const val CREATE_TABLE = "booksDB"
        private const val DATABASE_VERSION = 1

        private const val CREATE_DATABASE = """
            CREATE TABLE ${DatabaseConsts.BOOK.TABLE_NAME}(
            ${DatabaseConsts.BOOK.COLUMNS.ID} INTEGER PRIMARY KEY AUTOINCREMENT,
            ${DatabaseConsts.BOOK.COLUMNS.TITLE} TEXT NOT NULL,
            ${DatabaseConsts.BOOK.COLUMNS.AUTHOR} TEXT NOT NULL,
            ${DatabaseConsts.BOOK.COLUMNS.GENRE} TEXT NOT NULL,
            ${DatabaseConsts.BOOK.COLUMNS.FAVORITE} INTEGER NOT NULL
            ); 
        """
    }


    private fun insertBooks(db: SQLiteDatabase) {
        val books = getInitalBooks()

        for (book in books) {
            var isFavarite: Int = if (book.favorite) 1 else 0

            val values = ContentValues().apply {
                put(DatabaseConsts.BOOK.COLUMNS.TITLE, book.title)
                put(DatabaseConsts.BOOK.COLUMNS.AUTHOR, book.author)
                put(DatabaseConsts.BOOK.COLUMNS.GENRE, book.genre)
                put(DatabaseConsts.BOOK.COLUMNS.FAVORITE, isFavarite)
            }

            // Inserir os dados
            db.insert(DatabaseConsts.BOOK.TABLE_NAME, null, values)

        }
    }


    private fun getInitalBooks(): List<BookEntity> {
        return listOf(
            BookEntity(1, "To Kill a Mockingbird", "Harper Lee", true, "Ficção"),
            BookEntity(2, "Dom Casmurro", "Machado de Assis", false, "Romance"),
            BookEntity(3, "O Hobbit", "J.R.R. Tolkien", true, "Fantasia"),
            BookEntity(4, "Cem Anos de Solidão", "Gabriel García Márquez", false, "Romance"),
            BookEntity(5, "O Pequeno Príncipe", "Antoine de Saint-Exupéry", false, "Fantasia"),
            BookEntity(6, "Crime e Castigo", "Fiódor Dostoiévski", false, "Ficção policial"),
            BookEntity(7, "Frankenstein", "Mary Shelley", false, "Terror"),
            BookEntity(8, "Harry Potter e a Pedra Filosofal", "J.K. Rowling", false, "Fantasia"),
            BookEntity(9, "Neuromancer", "William Gibson", false, "Cyberpunk"),
            BookEntity(10, "Senhor dos Anéis", "J.R.R. Tolkien", false, "Fantasia"),
            BookEntity(11, "Drácula", "Bram Stoker", false, "Terror"),
            BookEntity(12, "Orgulho e Preconceito", "Jane Austen", false, "Romance"),
            BookEntity(13, "Harry Potter e a Câmara Secreta", "J.K. Rowling", false, "Fantasia"),
            BookEntity(14, "As Crônicas de Nárnia", "C.S. Lewis", false, "Fantasia"),
            BookEntity(15, "O Código Da Vinci", "Dan Brown", false, "Mistério"),
            BookEntity(16, "It: A Coisa", "Stephen King", false, "Terror"),
            BookEntity(17, "Moby Dick", "Herman Melville", true, "Aventura"),
            BookEntity(18, "O Nome do Vento", "Patrick Rothfuss", true, "Fantasia"),
            BookEntity(19, "O Conde de Monte Cristo", "Alexandre Dumas", true, "Aventura"),
            BookEntity(20, "Os Miseráveis", "Victor Hugo", false, "Romance")
        )
    }
}