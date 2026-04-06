package com.example.myapplication

import android.os.Bundle
import android.text.Editable
import android.text.Layout
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.Data.remote.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    lateinit var etCep: EditText
    lateinit var btnBuscar: Button
    lateinit var tvResultado: TextView

    val service = RetrofitClient.instance


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        etCep = findViewById(R.id.etCep)
        btnBuscar = findViewById(R.id.btnBuscar)
        tvResultado = findViewById(R.id.tvResultado)

        etCep.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if(s == null) return;

                val clean = s.toString().replace("-", "")

                if(clean.length == 8){
                    try {
                        CoroutineScope(Dispatchers.Main).launch {
                            val response = withContext(Dispatchers.IO){
                                service.getCep(clean)
                            }

                            tvResultado.text = "Logradouro: ${response.logradouro} \n" +
                                    "Bairro: ${response.bairro}  \n" +
                                    "Localidade: ${response.bairro} \n" +
                                    "UF: ${response.uf}"
                        }
                    } catch(e: Exception){
                        tvResultado.text = "Erro ao buscar CEP"
                    }

                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        })

    }
}