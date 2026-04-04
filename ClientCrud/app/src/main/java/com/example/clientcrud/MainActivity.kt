package com.example.clientcrud

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerClientes: RecyclerView
    private lateinit var createClientLauncher: ActivityResultLauncher<Intent>

    private lateinit var btnAddClient: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        recyclerClientes = findViewById(R.id.recyclerClients)
        btnAddClient = findViewById<Button>(R.id.btnAddClient)


        val clients = mutableListOf<Client>(
            Client("Paulo", 27),
            Client("João", 22),
            Client("Elliezer", 25),
            Client("Júlio", 30),
            Client("Marcelo", 55),
        )

        recyclerClientes.layoutManager = LinearLayoutManager(this)
        recyclerClientes.adapter = ClientAdapter(clients)

        createClientLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
            if(result.resultCode == RESULT_OK){
                val data = result.data
                val name = data?.getStringExtra("CLIENT_NAME")
                val age = data?.getIntExtra("CLIENT_AGE", 0)

                if(name != null && age != null){
                    println("Age: $age, name: $name")
                }
            }
        }

        btnAddClient.setOnClickListener {
            val intent = Intent(this, CreateClientActivity::class.java)
            createClientLauncher.launch(intent)
        }

    }
}