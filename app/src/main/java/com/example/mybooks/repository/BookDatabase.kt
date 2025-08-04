package com.example.mybooks.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mybooks.entity.BookEntity

@Database(entities = [BookEntity::class], version = 1)
abstract class BookDatabase: RoomDatabase() {

    abstract fun bookDAO() : BookDAO

    companion object {
        private lateinit var instance: BookDatabase
        private  const val DATABASE_NAME = "books_db"

        fun getDatabase(context: Context): BookDatabase {
            if(!::instance.isInitialized) {
                synchronized(this) {
                    instance = Room.databaseBuilder(context, BookDatabase::class.java, DATABASE_NAME)
                        .allowMainThreadQueries()
                        .build()
                }
            }

            return instance
        }
    }
}