package com.example.mybooks.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.mybooks.entity.BookEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDAO {

    @Query("SELECT * FROM Book")
    fun getAllBooks(): Flow<List<BookEntity>>

    @Query("SELECT * FROM Book WHERE favorite = 1")
    fun getFavoriteBooks(): List<BookEntity>

    @Query("SELECT * FROM BOOK WHERE id = :id")
    fun getBookById(id: Int): BookEntity

    @Update
    fun update(book: BookEntity)

    @Delete
    fun delete(book: BookEntity): Int

    @Insert
    fun insert(books: List<BookEntity>)
}