package com.example.mybooks.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.mybooks.consts.DatabaseConsts


class BookDataBaseHelper(context: Context) :
    SQLiteOpenHelper(context, CREATE_TABLE, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        // Momento da criação do banco de dados
        // Essa função é executada apenas uma vez, quando o banco de dados ainda não foi criado
        db.execSQL(CREATE_DATABASE)
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
}