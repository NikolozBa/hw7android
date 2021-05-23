package com.example.hw7android

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class RecyclerViewAdapter (private val list: MutableSet<String>, private val c : Context): RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {


    class RecyclerViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        val note: TextView = itemView.findViewById(R.id.note)
        val delete: TextView = itemView.findViewById(R.id.delete)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {

        holder.note.text = list.elementAt(position)

        holder.delete.setOnClickListener {
            list.remove(list.elementAt(position))
            notifyDataSetChanged()

            val prf = c.getSharedPreferences("MyApp", Context.MODE_PRIVATE)
            prf.edit().remove("NOTES").apply()
            prf.edit().putStringSet("NOTES", list).apply()
        }

    }

}