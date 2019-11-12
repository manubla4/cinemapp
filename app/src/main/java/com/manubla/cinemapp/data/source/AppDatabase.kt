package com.manubla.cinemapp.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.manubla.cinemapp.data.dao.NoteDao
import com.manubla.cinemapp.data.helper.converter.Converters
import com.manubla.cinemapp.data.model.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

}