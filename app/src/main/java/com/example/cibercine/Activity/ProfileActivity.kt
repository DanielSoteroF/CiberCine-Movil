package com.example.cibercine.Activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cibercine.R
import com.example.cibercine.databinding.ActivityMainBinding
import com.example.cibercine.databinding.ActivityProfileBinding
import com.ismaeldivita.chipnavigation.ChipNavigationBar

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private lateinit var bottomNavigation: ChipNavigationBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configura el menú de navegación
        bottomNavigation = binding.btnChipNavigationBar
        bottomNavigation.setOnItemSelectedListener { id ->
            when (id) {
                R.id.btnFilms -> startActivity(Intent(this, MainActivity::class.java))
                R.id.btnCinemas -> startActivity(Intent(this, CinemasActivity::class.java))
                R.id.btnProfile -> startActivity(Intent(this, ProfileActivity::class.java))
            }
        }

        binding.btnCerrarSesion.setOnClickListener {
            startActivity(
                Intent(this, LoginActivity
            ::class.java)
            )
        }
    }


}