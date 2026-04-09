package com.example.myapplication

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.ui.cep.CepViewModel

class MainActivity : AppCompatActivity() {

    lateinit var etCep: EditText
    lateinit var btnBuscar: Button
    lateinit var tvResultado: TextView

    lateinit var viewModel: CepViewModel

    lateinit var progress: ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        etCep = findViewById(R.id.etCep)
        btnBuscar = findViewById(R.id.btnBuscar)
        tvResultado = findViewById(R.id.tvResultado)
        progress = findViewById(R.id.progress)

        viewModel = ViewModelProvider(this)[CepViewModel::class.java]

        btnBuscar.setOnClickListener {
            val cep = etCep.text.toString().replace("-", "")
            viewModel.buscarCep(cep)
        }


        viewModel.loading.observe(this) { loading ->

            if (loading) {
                progress.visibility = View.VISIBLE
            } else {
                progress.visibility = View.GONE

            }

        }

        viewModel.cepResult.observe(this) { cep ->
            tvResultado.text = """
             Rua: ${cep.logradouro}
             Bairro: ${cep.bairro}
             Cidade: ${cep.localidade}
             UF: ${cep.uf}
         """.trimIndent()
        }

    }
}