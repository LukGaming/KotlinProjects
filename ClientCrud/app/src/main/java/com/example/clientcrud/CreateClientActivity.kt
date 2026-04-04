package com.example.clientcrud

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CreateClientActivity  : AppCompatActivity() {

    lateinit var etName: TextView
    lateinit var etAge: TextView

    lateinit var btnSave: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acitivity_create_client)
        etName = findViewById<TextView>(R.id.etName)
        etAge = findViewById<TextView>(R.id.etAge)
        btnSave = findViewById<Button>(R.id.btnSave)


        btnSave.setOnClickListener {

        }
    }

    private fun saveClient(){
    }

}