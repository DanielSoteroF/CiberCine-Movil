package com.example.cibercine.Activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cibercine.R

class CheckBuyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_check_buy)

        // Espera de 3 segundos (3000 milisegundos) antes de pasar a la siguiente actividad o cerrar la ventana
        Handler(Looper.getMainLooper()).postDelayed({
            // Aquí puedes iniciar una nueva actividad
            startActivity(Intent(this, MainActivity::class.java))
            finish()  // Opcionalmente cierra esta actividad para que el usuario no vuelva a ella
        }, 3000) // 3000 ms = 3 segundos
    }
}