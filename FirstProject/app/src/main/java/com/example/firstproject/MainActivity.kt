package com.example.firstproject

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(){

    private lateinit var etName: EditText;
    private lateinit var  etAge: EditText;
    private lateinit var btnShow: Button;
    private lateinit var tvResult: TextView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        btnShow = findViewById(R.id.btnShow)
        tvResult = findViewById(R.id.tvResult)

        btnShow.setOnClickListener {
            if(!validateForm()) return@setOnClickListener;
            val name = etName.text.toString();
            val age = etAge.text.toString();

            tvResult.setText("Olá $name, você tem $age anos")

        }

    }

    private fun validateForm(): Boolean {
        val name = etName.text.toString();
        val age = etAge.text.toString();

        if(name.isEmpty()){
            etName.error = "Digite seu nome"
            return false;

        }
        val ageInt = age.toIntOrNull();

        if(ageInt == null || ageInt <=0){
            etAge.error = "Idade inválida"
            return false;
        }
        return true;
    }

}