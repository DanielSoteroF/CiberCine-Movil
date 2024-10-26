package com.example.cibercine.Activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cibercine.Adapter.CinemasAdapter
import com.example.cibercine.Adapter.FilmListAdapter
import com.example.cibercine.Models.Cinemas
import com.example.cibercine.Models.Film
import com.example.cibercine.R
import com.example.cibercine.databinding.ActivityCinemasBinding
import com.example.cibercine.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.ismaeldivita.chipnavigation.ChipNavigationBar

class CinemasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCinemasBinding
    private lateinit var database: FirebaseDatabase
    private lateinit var bottomNavigation: ChipNavigationBar

    private val sliderHandler = Handler()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCinemasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance()

        // Configura el menú de navegación
        bottomNavigation = binding.btnChipNavigationBar
        bottomNavigation.setOnItemSelectedListener { id ->
            when (id) {
                R.id.btnFilms -> startActivity(Intent(this, MainActivity::class.java))
                R.id.btnCinemas -> startActivity(Intent(this, CinemasActivity::class.java))
                R.id.btnProfile -> startActivity(Intent(this, ProfileActivity::class.java))
            }
        }

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        initCinemas()
    }

    private fun initCinemas() {
        val myRef: DatabaseReference =database.getReference("Cinemas")
        binding.progressBar.visibility= View.VISIBLE
        val items=ArrayList<Cinemas>()

        myRef.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for (issue in snapshot.children){
                        items.add(issue.getValue(Cinemas::class.java)!!)
                    }
                    if(items.isNotEmpty()){
                        binding.recyclerViewCines.layoutManager= LinearLayoutManager(
                            this@CinemasActivity,
                            LinearLayoutManager.VERTICAL,
                            false
                        )
                        binding.recyclerViewCines.adapter= CinemasAdapter(items)
                    }
                    binding.progressBar.visibility= View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}