package edu.ecu.cs.pirateplaces.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import edu.ecu.cs.pirateplaces.PiratePlace

@Database(entities = [ PiratePlace::class ], version = 2)
@TypeConverters(PiratePlacesTypeConverters::class)
abstract class PiratePlacesDatabase : RoomDatabase() {
    abstract fun piratePlacesDao() : PiratePlacesDao
}

val migration_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "ALTER TABLE PiratePace" +
                    " ADD COLUMN latitude REAL NOT NULL DEFAULT ''" +
                    "ADD COLUMN longitude REAl NOT NULL DEFAULT ''" +
                    "ADD COLUMN hasLocation INTEGER NOT NULL DEFAULT ''"

        )
    } }