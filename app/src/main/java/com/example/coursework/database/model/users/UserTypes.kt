package com.example.coursework.database.model.users

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_types")
data class UserTypes(

    @PrimaryKey(autoGenerate = true   ) val userTypeID: Int? = null,
    @ColumnInfo(name = "type_name"    ) val typeName: String

)
