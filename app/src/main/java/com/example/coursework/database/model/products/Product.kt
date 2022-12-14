package com.example.coursework.database.model.products

import androidx.room.*


@Entity (
    tableName = "products",
    foreignKeys = [ForeignKey(
        entity = ProductType::class,
        parentColumns = ["productTypeID"],
        childColumns = ["productTypeID"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Product(

    @PrimaryKey(autoGenerate = true      ) val productID     : Int? = null,
    @ColumnInfo(name = "productTypeID"   ) var productTypeID : Int,
    @ColumnInfo(name = "name"            ) var name          : String,
    @ColumnInfo(name = "pictureURL"      ) var pictureURL    : String?,
    @ColumnInfo(name = "calories"        ) var calories      : Int,
    @ColumnInfo(name = "protein"         ) var protein       : Double,
    @ColumnInfo(name = "fat"             ) var fat           : Double,
    @ColumnInfo(name = "carbohydrates"   ) var carbohydrates : Double

)
