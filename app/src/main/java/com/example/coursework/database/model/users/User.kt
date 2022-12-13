package com.example.coursework.database.model.users

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity (
    tableName = "users",
    foreignKeys = [ForeignKey(
        entity = UserTypes::class,
        parentColumns = ["userTypeID"],
        childColumns = ["userTypeID"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class User(

    @PrimaryKey(autoGenerate = true   ) val userID     : Int? = null,
    @ColumnInfo(name = "userTypeID"   ) var userTypeID : Int,
    @ColumnInfo(name = "login"        ) val login      : String,
    @ColumnInfo(name = "password"     ) var password   : String

)
