package com.example.cibercine.Activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.cibercine.Adapter.FilmListAdapter
import com.example.cibercine.Adapter.SliderAdapter
import com.example.cibercine.Models.Film
import com.example.cibercine.Models.SliderItems
import com.example.cibercine.R
import com.example.cibercine.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.ismaeldivita.chipnavigation.ChipNavigationBar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database: FirebaseDatabase
    private lateinit var bottomNavigation: ChipNavigationBar

    private val sliderHandler = Handler()
    private val sliderRunnable = Runnable {
        binding.viewPager2.currentItem = binding.viewPager2.currentItem + 1
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
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

        binding.btnCerrarSesion.setOnClickListener {
            startActivity(Intent(this, LoginActivity
            ::class.java))
        }

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        initBanner()
        initPeliculasTop()
        initProximas()
    }

    private fun initPeliculasTop() {
        val myRef:DatabaseReference=database.getReference("Items")
        binding.progressBarPeliculasTop.visibility=View.VISIBLE
        val items=ArrayList<Film>()

        myRef.addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for (issue in snapshot.children){
                        items.add(issue.getValue(Film::class.java)!!)
                    }
                    if(items.isNotEmpty()){
                        binding.recyclerViewPeliculasTop.layoutManager=GridLayoutManager(
                            this@MainActivity,3,
                            LinearLayoutManager.VERTICAL,
                            false
                        )
                        binding.recyclerViewPeliculasTop.adapter= FilmListAdapter(items)
                    }
                    binding.progressBarPeliculasTop.visibility=View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }


    private fun initProximas() {
        val myRef:DatabaseReference=database.getReference("Upcomming")
        binding.progressBarProximas.visibility=View.VISIBLE
        val items=ArrayList<Film>()

        myRef.addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for (issue in snapshot.children){
                        items.add(issue.getValue(Film::class.java)!!)
                    }
                    if(items.isNotEmpty()){
                        binding.recyclerViewProximas.layoutManager=LinearLayoutManager(
                            this@MainActivity,
                            LinearLayoutManager.HORIZONTAL,
                            false
                        )
                        binding.recyclerViewProximas.adapter= FilmListAdapter(items)
                    }
                    binding.progressBarProximas.visibility=View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }


    private fun initBanner() {
        val myRef:DatabaseReference=database.getReference("Banners")
        binding.progressBarSlider.visibility= View.VISIBLE

        myRef.addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists= mutableListOf<SliderItems>()
                for(childSnapshot in snapshot.children){
                    val list=childSnapshot.getValue(SliderItems::class.java)
                    if(list!=null){
                        lists.add(list)
                    }
                }
                binding.progressBarSlider.visibility = View.GONE
                banners(lists)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun banners(lists: MutableList<SliderItems>) {
        binding.viewPager2.adapter = SliderAdapter(lists, binding.viewPager2)
        binding.viewPager2.clipToPadding = false
        binding.viewPager2.clipChildren = false
        binding.viewPager2.offscreenPageLimit = 3
        binding.viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))
            addTransformer(ViewPager2.PageTransformer{ page, position ->
                val r = 1 - Math.abs(position)
                page.scaleY = 0.85f + r * 0.15f
            })
        }

        binding.viewPager2.setPageTransformer(compositePageTransformer)
        binding.viewPager2.currentItem=1
        binding.viewPager2.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                sliderHandler.removeCallbacks(sliderRunnable)
            }
        })
    }

    override fun onPause() {
        super.onPause()
        sliderHandler.removeCallbacks(sliderRunnable)
    }

    override fun onResume() {
        super.onResume()
        sliderHandler.removeCallbacks(sliderRunnable, 2000)
    }
}