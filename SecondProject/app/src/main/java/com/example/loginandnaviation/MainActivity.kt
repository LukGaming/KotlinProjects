package com.example.loginandnaviation

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etAge: EditText
    private lateinit var btnShow: Button
    private lateinit var tvResult: TextView
    private lateinit var btnNavigate: Button



    override fun onCreate(savedInstanceState: Bundle?, ) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etName = findViewById(R.id.etName)
        etAge = findViewById(R.id.etAge)
        btnShow = findViewById(R.id.btnShow);
        tvResult = findViewById(R.id.tvResult)
        btnNavigate = findViewById(R.id.btnNavigate)

        btnShow.setOnClickListener {
            if(!validateForm()) return@setOnClickListener

            val name = etName.text.toString();
            val age = etAge.text.toString()



            tvResult.setText("Olá $name, você tem $age anos")
        }

        btnNavigate.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("USER_NAME", etName.text.toString());
            startActivity(intent);
        }

    }

    private fun validateForm(): Boolean {
        val name = etName.text.toString()
        val age = etAge.text.toString()

        if(name.isEmpty()){
            etName.error = "Digite seu nome"
            return false;
        }

        val ageInt = age.toIntOrNull()

        if(ageInt == null || ageInt <=0){
            etAge.error = "Idade Invalida"
            return false;
        }
        return true;
    }
}