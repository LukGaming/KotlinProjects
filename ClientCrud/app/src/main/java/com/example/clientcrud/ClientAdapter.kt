package com.example.clientcrud

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ClientAdapter(
    private val clients: MutableList<Client>
) : RecyclerView.Adapter<ClientAdapter.ClientViewHolder>(){

    class ClientViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val name = view.findViewById<TextView>(R.id.tvName)
        val age = view.findViewById<TextView>(R.id.tvAge)
        val btnDelete = view.findViewById<Button>(R.id.btnDelete)

        val progress = view.findViewById<ProgressBar>(R.id.progressDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_client, parent, false)

        return ClientViewHolder(view)
    }

    override fun onBindViewHolder(holder: ClientViewHolder, position: Int) {
        val client = clients[position]

        holder.name.text = client.name
        holder.age.text = "Idade: ${client.age}"

        holder.btnDelete.setOnClickListener {
            val pos: Int = holder.bindingAdapterPosition

            if(pos != RecyclerView.NO_POSITION){
                showRemoveDialog(pos, holder)
            }
        }
    }

    fun removeItem(position: Int, holder: ClientViewHolder){
        CoroutineScope(Dispatchers.Main).launch {
            holder.btnDelete.visibility = View.GONE
            holder.progress.visibility = View.VISIBLE
            delay(3000)
            clients.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, clients.size)
            holder.progress.visibility = View.GONE
        }
    }

    fun showRemoveDialog(pos: Int, holder: ClientViewHolder){
        AlertDialog.Builder(holder.itemView.context)
            .setTitle("Excluir")
            .setMessage("Tem certeza?")
            .setPositiveButton("Sim"){_, _ -> removeItem(pos, holder)}
            .setNegativeButton("Cancelar", null)
            .show()
    }

    override fun getItemCount() = clients.size
}