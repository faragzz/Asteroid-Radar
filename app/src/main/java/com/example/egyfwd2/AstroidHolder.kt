package com.example.egyfwd2

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "asteroid_table")
data class AstroidHolder(
    @PrimaryKey
    val id: Long,
    val name:String,
    val date:String,
    val magnitude:Double,
    val diameter:Double,
    val velocity:Double,
    val distance:Double,
    val hazardous:Boolean
)