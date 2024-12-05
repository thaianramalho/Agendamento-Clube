package com.thaianramalho.trabalho3etapa

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "sports_complex.db"
        private const val DATABASE_VERSION = 2

        private const val TABLE_ADMIN = "admin"
        private const val TABLE_USER = "user"
        private const val TABLE_SPORTS = "sports"
        private const val TABLE_BOOKINGS = "bookings"

        private const val CREATE_TABLE_ADMIN = """
            CREATE TABLE $TABLE_ADMIN (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                email TEXT NOT NULL,
                password TEXT NOT NULL
            )
        """

        private const val CREATE_TABLE_USER = """
            CREATE TABLE $TABLE_USER (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                cpf TEXT NOT NULL UNIQUE,
                name TEXT NOT NULL,
                phone TEXT NOT NULL,
                email TEXT NOT NULL,
                birthdate TEXT NOT NULL,
                password TEXT NOT NULL
            )
        """

        private const val CREATE_TABLE_SPORTS = """
            CREATE TABLE $TABLE_SPORTS (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL
            )
        """

        private const val CREATE_TABLE_BOOKINGS = """
            CREATE TABLE $TABLE_BOOKINGS (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                date TEXT NOT NULL,
                sport TEXT NOT NULL
            )
        """
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_ADMIN)
        db.execSQL(CREATE_TABLE_USER)
        db.execSQL(CREATE_TABLE_SPORTS)
        db.execSQL(CREATE_TABLE_BOOKINGS)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_ADMIN")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USER")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_SPORTS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_BOOKINGS")
        onCreate(db)
    }
}