package com.example.coursework.database.model.products

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_types")
data class ProductType(

    @PrimaryKey(autoGenerate = true             ) val productTypeID          : Int? = null,
    @ColumnInfo(name = "productTypeName"        ) var productTypeName        : String,
    @ColumnInfo(name = "productTypeDescription" ) var productTypeDescription : String?,
    @ColumnInfo(name = "productTypePictureURL"  ) var productTypePictureURL  : String?,

)
