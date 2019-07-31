package com.frantic.catfactsapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.frantic.catfactsapp.data.db.dao.CatFactsDao
import com.frantic.catfactsapp.data.db.entity.CatFactsEntity
import com.frantic.catfactsapp.data.db.entity.PreferencesEntity
import com.frantic.catfactsapp.data.db.entity.UsersEntity

@Database(
    entities = [UsersEntity::class,
        CatFactsEntity::class,
        PreferencesEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CatFactsDataBase : RoomDatabase() {

    abstract fun catFactsDao(): CatFactsDao

    companion object {

        private var INSTANCE: CatFactsDataBase? = null

        fun getInstance(context: Context): CatFactsDataBase? {
            if (INSTANCE == null) {
                synchronized(CatFactsDataBase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        CatFactsDataBase::class.java,
                        "CatFactsDataBase"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}