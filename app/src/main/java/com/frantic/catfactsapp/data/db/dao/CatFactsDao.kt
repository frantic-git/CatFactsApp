package com.frantic.catfactsapp.data.db.dao

import androidx.room.*
import com.frantic.catfactsapp.data.db.Fields
import com.frantic.catfactsapp.data.db.Tables
import com.frantic.catfactsapp.data.db.entity.CatFactsEntity
import com.frantic.catfactsapp.data.db.entity.PreferencesEntity
import com.frantic.catfactsapp.data.db.entity.UsersEntity
import com.frantic.catfactsapp.presentation.catfactslistfragment.CatFactItem

@Dao
interface CatFactsDao {

    @Insert
    fun insertUser(user: UsersEntity): Long

    @Query(
        "select * from ${Tables.USERS_TABLE}" +
                " where ${Tables.USERS_TABLE}.LOGIN_FIELD = :login"
    )
    fun getUser(login: String): List<UsersEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCatFact(catFact: CatFactsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPreference(preference: PreferencesEntity)

    @Query(
        "delete from ${Tables.PREFERENCES_TABLE}" +
                " where id = :userId and ${Fields.NET_ID_FIELD} = :netId"
    )
    fun deletePreference(userId: Long, netId: String)

    @Query(
        "select ${Tables.CAT_FACTS_TABLE}.id as id," +
                " ${Tables.CAT_FACTS_TABLE}.${Fields.TEXT_FIELD} as text," +
                " case when ${Tables.PREFERENCES_TABLE}.${Fields.NET_ID_FIELD} is null then 0 else 1 end as preference" +
                " from ${Tables.CAT_FACTS_TABLE} left outer join ${Tables.PREFERENCES_TABLE}" +
                " on ${Tables.PREFERENCES_TABLE}.${Fields.NET_ID_FIELD} = ${Tables.CAT_FACTS_TABLE}.id" +
                " and ${Tables.PREFERENCES_TABLE}.id = :userId"
    )
    fun getAllCatFacts(userId: Long): List<CatFactItem>

    @Query(
        "select ${Tables.CAT_FACTS_TABLE}.id as id," +
                " ${Tables.CAT_FACTS_TABLE}.${Fields.TEXT_FIELD} as text," +
                " case when ${Tables.PREFERENCES_TABLE}.${Fields.NET_ID_FIELD} is null then 0 else 1 end as preference" +
                " from ${Tables.CAT_FACTS_TABLE} left outer join ${Tables.PREFERENCES_TABLE}" +
                " on ${Tables.PREFERENCES_TABLE}.${Fields.NET_ID_FIELD} = ${Tables.CAT_FACTS_TABLE}.id" +
                " and ${Tables.PREFERENCES_TABLE}.id = :userId where ${Tables.PREFERENCES_TABLE}.${Fields.NET_ID_FIELD} is not null"
    )
    fun getFavoriteCatFacts(userId: Long): MutableList<CatFactItem>

    @Query("select * from ${Tables.CAT_FACTS_TABLE} where id = :id")
    fun getCatFact(id: String): List<CatFactsEntity>

}