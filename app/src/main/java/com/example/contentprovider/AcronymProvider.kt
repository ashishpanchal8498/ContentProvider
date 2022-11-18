package com.example.contentprovider

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri

class AcronymProvider : ContentProvider() {

    companion object {
        private const val PROVIDER_NAME = "package com.example.contentprovider/AcronymProvider"
        private const val URL = "content://$PROVIDER_NAME/ATABLE"
        val CONTENT_URI: Uri = Uri.parse(URL)

        const val ID = "ID"
        const val NAME = "NAME"
        const val DESIGNATION = "DESIGNATION"
    }

    private lateinit var db: SQLiteDatabase

    override fun onCreate(): Boolean {
        val helper = Helper(context)
        db = helper.writableDatabase
        return true
    }

    override fun insert(uri: Uri, cv: ContentValues?): Uri {
        db.insert("ATABLE", null, cv)
        context?.contentResolver?.notifyChange(uri, null)
        return uri
    }

    override fun update(uri: Uri, cv: ContentValues?, condition: String?, condition_val: Array<out String>?): Int {
        val count = db.update("ATABLE", cv, condition, condition_val)
        context?.contentResolver?.notifyChange(uri, null)
        return count
    }

    override fun delete(uri: Uri, condition: String?, condition_val: Array<out String>?): Int {
        val count = db.delete("ATABLE", condition, condition_val)
        context?.contentResolver?.notifyChange(uri, null)
        return count
    }

    override fun query(
        uri: Uri,
        cols: Array<out String>?,
        condition: String?,
        condition_val: Array<out String>?,
        order: String?
    ): Cursor? {
        return db.query("ATABLE", cols, condition, condition_val, null, null, order)
    }

    override fun getType(p0: Uri): String {
        return "vnd.android.cursor.dir/vnd.example.atable"
    }
}