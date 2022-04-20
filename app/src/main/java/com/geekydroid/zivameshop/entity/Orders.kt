package com.geekydroid.zivameshop.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.DateFormat

@Entity(tableName = "ORDERS")
data class Orders(
    @PrimaryKey(autoGenerate = true)
    val orderId: Int = 0,
    @Embedded val product: Product,
    var orderStatus: String,
    var orderAmount: Double = 0.0,
    val createdOn: Long = System.currentTimeMillis(),
    var updatedOn: Long = System.currentTimeMillis()
) {
    val createdOnFormatted: String
        get() = DateFormat.getDateTimeInstance().format(createdOn)

    val updatedOnFormatted: String
        get() = DateFormat.getDateTimeInstance().format(updatedOn)
}