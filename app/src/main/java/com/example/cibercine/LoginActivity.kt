package com.example.cibercine

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cibercine.Activity.IntroActivity
import com.example.cibercine.Activity.MainActivity
import com.example.cibercine.databinding.ActivityIntroBinding
import com.example.cibercine.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.BtnIngresar.setOnClickListener {
            if (binding.EmailTxt.text.toString().isNotEmpty() && binding.PasswordTxt.text.toString().isNotEmpty()) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    binding.EmailTxt.text.toString(), binding.PasswordTxt.text.toString()
                ).addOnCompleteListener{
                    if(it.isSuccessful){
                        startActivity(Intent(this, IntroActivity::class.java))
                    }else{
                        showAlert()
                    }

                }
            }
        }

        binding.BtnRegistrarse.setOnClickListener {
            if (binding.EmailTxt.text.toString().isNotEmpty() && binding.PasswordTxt.text.toString().isNotEmpty()) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    binding.EmailTxt.text.toString(), binding.PasswordTxt.text.toString()
                ).addOnCompleteListener{
                    if(it.isSuccessful){
                        startActivity(Intent(this, IntroActivity::class.java))
                    }else{
                        showAlert()
                    }

                }
            }
        }
    }

    fun showAlert(){
        val alertDialog = AlertDialog.Builder(this)
            .setTitle("Error")
            .setMessage("Por favor, ingrese un correo y contraseña válidos")
            .setPositiveButton("Aceptar", null)
        val dialog = alertDialog.create()
        dialog.show()
    }


}