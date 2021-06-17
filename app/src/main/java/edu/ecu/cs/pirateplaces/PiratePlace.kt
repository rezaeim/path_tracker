package edu.ecu.cs.pirateplaces

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity
data class PiratePlace(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    var name: String = "",
    var visitedWith: String = "",
    var hasLocation: Boolean = false,
    var latitude: Double = 0.00,
    var longitude: Double = 0.00,
    var lastVisited: Date = Date()) {

    val photoFileName
        get() = "IMG_$id.jpg"

}

