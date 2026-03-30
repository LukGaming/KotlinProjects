package com.example.firstproject

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etName = findViewById<EditText>(R.id.etName)
        val btnShow = findViewById<Button>(R.id.btnShow)
        val tvResult = findViewById<TextView>(R.id.tvResult)
        val etAge = findViewById<EditText>(R.id.etAge)
        val btnClear = findViewById<Button>(R.id.btnClear)

        btnShow.setOnClickListener {
            val name = etName.text.toString();
            val idade = etAge.text.toString()



            if(name.trim().isEmpty()){
                tvResult.setText("Digite um nome!")

            }else{
                tvResult.setText("Olá, $name, $idade anos!");
            }

        }

        btnClear.setOnClickListener {
            etName.setText("")
            etAge.setText("")
            btnShow.setText("")
            tvResult.setText("")
        }
    }
}