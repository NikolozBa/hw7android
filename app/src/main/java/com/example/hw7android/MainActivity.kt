package com.example.hw7android

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recycler : RecyclerView
    private lateinit var adapter : RecyclerViewAdapter
    private lateinit var addButton : Button
    private lateinit var textField: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        addButton = findViewById(R.id.button)
        textField = findViewById(R.id.editText)

        val sharedPref = getSharedPreferences("MyApp", Context.MODE_PRIVATE)
        val savedSet = sharedPref.getStringSet("NOTES", mutableSetOf())


        recycler = findViewById(R.id.recycler)
        adapter = RecyclerViewAdapter(savedSet!!, this)


        recycler.adapter = adapter
        adapter.notifyDataSetChanged()
        recycler.layoutManager = LinearLayoutManager(this)


        addButton.setOnClickListener {
            if(textField.text.isNotEmpty()){


                if(savedSet.add(textField.text.toString())){

                    adapter.notifyItemInserted(savedSet.indexOf(textField.text.toString()))

                    sharedPref.edit().remove("NOTES").apply()
                    sharedPref.edit().putStringSet("NOTES", savedSet).apply()
                }

                textField.setText("")


            }
        }

    }


}