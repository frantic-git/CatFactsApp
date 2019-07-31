package com.frantic.catfactsapp.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.frantic.catfactsapp.data.db.Fields
import com.frantic.catfactsapp.data.db.Tables

@Entity(tableName = Tables.USERS_TABLE)
class UsersEntity(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    @ColumnInfo(name = Fields.LOGIN_FIELD) val login: String,
    @ColumnInfo(name = Fields.PASSWORD_FIELD) val password: String
)

@Entity(tableName = Tables.CAT_FACTS_TABLE)
class CatFactsEntity(
    @PrimaryKey(autoGenerate = false) val id: String,
    @ColumnInfo(name = Fields.USED_FIELD) val used: Boolean,
    @ColumnInfo(name = Fields.SOURCE_FIELD) val source: String,
    @ColumnInfo(name = Fields.TYPE_FIELD) val type: String,
    @ColumnInfo(name = Fields.DELETED_FIELD) val deleted: Boolean,
    @ColumnInfo(name = Fields.V_FIELD) val v: Int,
    @ColumnInfo(name = Fields.TEXT_FIELD) val text: String,
    @ColumnInfo(name = Fields.UPDATE_DATE_FIELD) val updateDate: String,
    @ColumnInfo(name = Fields.CREATE_DATE_FIELD) val createDate: String
)

@Entity(tableName = Tables.PREFERENCES_TABLE)
class PreferencesEntity(
    @PrimaryKey(autoGenerate = false) val id: Long,
    @ColumnInfo(name = Fields.NET_ID_FIELD) val netId: String
)
