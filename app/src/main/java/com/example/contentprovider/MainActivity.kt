package com.example.contentprovider

import android.content.ContentValues
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonNext: Button = findViewById(R.id.buttonNext)
        val buttonPrevious: Button = findViewById(R.id.buttonPrevious)
        val buttonInsert: Button = findViewById(R.id.buttonInsert)
        val buttonUpdate: Button = findViewById(R.id.buttonUpdate)
        val buttonDelete: Button = findViewById(R.id.buttonDelete)
        val buttonClear: Button = findViewById(R.id.buttonClear)
        val editTextName: EditText = findViewById(R.id.editTextName)
        val editTextDesignation: EditText = findViewById(R.id.editTextDesignation)
        val cv = ContentValues()
        var rs = contentResolver.query(AcronymProvider.CONTENT_URI,
            arrayOf(AcronymProvider.ID,AcronymProvider.NAME,AcronymProvider.DESIGNATION),null,null, AcronymProvider.NAME)

        buttonNext.setOnClickListener {
            if (rs != null) {
                if(rs.moveToNext()){
                    editTextName.setText(rs.getString(1))
                    editTextDesignation.setText(rs.getString(2))
                }
            }
        }

        buttonPrevious.setOnClickListener {
            if(rs?.moveToPrevious()==true){
                editTextName.setText(rs.getString(1))
                editTextDesignation.setText(rs.getString(2))
            }
        }

        buttonInsert.setOnClickListener {
            cv.put(AcronymProvider.NAME,editTextName.text.toString())
            cv.put(AcronymProvider.DESIGNATION,editTextDesignation.text.toString())
            contentResolver.insert(AcronymProvider.CONTENT_URI,cv)
            rs?.requery()
        }

        buttonUpdate.setOnClickListener {
            cv.put(AcronymProvider.DESIGNATION,editTextDesignation.text.toString())
            contentResolver.update(AcronymProvider.CONTENT_URI,cv,"NAME = ?", arrayOf(editTextName.text.toString()))
            rs?.requery()
        }

        buttonDelete.setOnClickListener {
            contentResolver.delete(AcronymProvider.CONTENT_URI,"NAME = ?", arrayOf(editTextName.text.toString()))
            rs?.requery()
        }

        buttonClear.setOnClickListener {
            if(rs?.moveToNext()!!){
                editTextName.setText("")
                editTextDesignation.setText("")
            }
        }
    }
}
