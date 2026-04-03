package com.example.clientcrud

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeActivity: AppCompatActivity() {
    private lateinit var recyclerClientes: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home)

        recyclerClientes = findViewById(R.id.recyclerClients)


        val clients = mutableListOf<Client>(
            Client("Paulo", 27),
            Client("João", 22),
            Client("Elliezer", 25),
        )

        recyclerClientes.layoutManager = LinearLayoutManager(this)
        recyclerClientes.adapter = ClientAdapter(clients)

    }
}