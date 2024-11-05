package com.example.cibercine.Activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
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
                    Intent(this, SeatListActivity
                    ::class.java)
        }

        binding.buyBtn3.setOnClickListener{
            if (binding.VisaEdit.text.toString().isEmpty() ||
                binding.NumberCardEdit.text.toString().isEmpty() ||
                binding.DateExpirationEdit.text.toString().isEmpty() ||
                binding.CVVEdit.text.toString().isEmpty()) {

                // Mostrar alerta si algún campo está vacío
                showAlert()
            } else {
                // Si todos los campos están completos, abrir SeatListActivity
                val intent = Intent(this, CheckBuyActivity::class.java)
                startActivity(intent)
            }
        }


    }

    fun showAlert(){
        val alertDialog = AlertDialog.Builder(this)
            .setTitle("Error")
            .setMessage("Porfavor ingrese datos a todos los campos")
            .setPositiveButton("Aceptar", null)
        val dialog = alertDialog.create()
        dialog.show()
    }
}