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
    /*
     Tipos de retornos de uma query na camada DAO
      -> Entity
      -> List<Entity>
      -> LiveData<Entity>
      -> Flow<Entity> | Flow<List<Entity>>
      -> Cursor
      -> Int
      -> Long
      -> Boolean
    * */

    @Query("SELECT * FROM Book")
    fun getAllBooks(): Flow<List<BookEntity>>

    @Query("SELECT * FROM Book WHERE favorite = 1")
    fun getFavoriteBooks(): Flow<List<BookEntity>>

    @Query("SELECT * FROM BOOK WHERE id = :id")
    suspend fun getBookById(id: Int): BookEntity

    @Update
    suspend fun update(book: BookEntity)

    @Delete
    suspend fun delete(book: BookEntity): Int

    @Insert
    suspend fun insert(books: List<BookEntity>)
}