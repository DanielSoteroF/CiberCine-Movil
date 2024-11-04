package com.example.cibercine.Activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cibercine.R
import com.example.cibercine.databinding.ActivityPaymentBinding
import com.example.cibercine.databinding.ActivityProfileBinding

class PaymentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPaymentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflar el layout usando view binding
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar el ajuste de insets para el diseño
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.backBtn.setOnClickListener{
            startActivity(
                Intent(this, SeatListActivity
            ::class.java)
            )
        }

        binding.backBtn.setOnClickListener {
            startActivity(
                Intent(this, SeatListActivity
                    ::class.java)
                )
            }
        }
    }