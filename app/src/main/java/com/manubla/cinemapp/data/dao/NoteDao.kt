package com.manubla.cinemapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.manubla.cinemapp.data.model.Note

@Dao
interface NoteDao {

    @Query("SELECT * FROM note")
    suspend fun getAll(): List<Note>

    @Insert
    suspend fun insertAll(vararg notes: Note)

    @Delete
    suspend fun delete(note: Note)

//    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
//    suspend fun loadAllByIds(userIds: IntArray): List<User>
//
//    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
//            "last_name LIKE :last LIMIT 1")
//    suspend fun findByName(first: String, last: String): User
}