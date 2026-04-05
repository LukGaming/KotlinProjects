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

    lateinit var clients: MutableList<Client>;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        recyclerClientes = findViewById(R.id.recyclerClients)
        btnAddClient = findViewById<Button>(R.id.btnAddClient)




         clients = mutableListOf<Client>(
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
                val client = data?.getSerializableExtra("CLIENT") as? Client


                if(client != null){
                    addClient(client)
                }
            }
        }

        btnAddClient.setOnClickListener {
            val intent = Intent(this, CreateClientActivity::class.java)
            createClientLauncher.launch(intent)
        }

    }

    fun addClient(client: Client){
        clients.add(client)
        recyclerClientes.adapter!!.notifyItemInserted(clients.size)
    }
}