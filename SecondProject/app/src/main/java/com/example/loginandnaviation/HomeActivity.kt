package com.example.loginandnaviation

import android.os.Bundle
import android.os.PersistableBundle
import android.view.ActionMode
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar

class HomeActivity : AppCompatActivity() {

    private lateinit var welcomeEt: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_home)

        var toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        welcomeEt = findViewById(R.id.welcome);

        val name = intent.getStringExtra("USER_NAME")

        welcomeEt.setText("Bem-vindo, $name!")
    }
}