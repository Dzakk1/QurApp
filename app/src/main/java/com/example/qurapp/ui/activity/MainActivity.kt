package com.example.qurapp.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ReportFragment
import com.example.qurapp.R
import com.example.qurapp.databinding.ActivityMainBinding
import com.example.qurapp.ui.fragment.DoaFragment
import com.example.qurapp.ui.fragment.HomeFragment
import com.example.qurapp.ui.fragment.ProfileFragment
import com.example.qurapp.ui.fragment.SurahFragment
import com.example.qurapp.ui.fragment.TafsirFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)


        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        setSupportActionBar(binding.topAppBar)
        replaceFragment(HomeFragment())

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {

                R.id.nav_home -> {
                    replaceFragment(HomeFragment())
                    supportActionBar?.title = "Home"
                    true
                }

                R.id.nav_surah -> {
                    replaceFragment(SurahFragment())
                    supportActionBar?.title = "Surah"
                    true
                }

                R.id.nav_tafsir -> {
                    replaceFragment(TafsirFragment())
                    supportActionBar?.title = "Tafsir"
                    true
                }

                R.id.nav_doa -> {
                    replaceFragment(DoaFragment())
                    supportActionBar?.title = "Doa"
                    true
                }

                R.id.nav_profile -> {
                    replaceFragment(ProfileFragment())
                    supportActionBar?.title = "Profile"
                    true
                }

                else -> false
            }
        }
    }


    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

//    try navigate
    fun navigateTo(menuId: Int) {

        when(menuId){

            1 -> binding.bottomNavigation.selectedItemId = R.id.nav_surah
            2 -> binding.bottomNavigation.selectedItemId = R.id.nav_tafsir
            3 -> binding.bottomNavigation.selectedItemId = R.id.nav_doa

//            4 -> binding.bottomNavigation.selectedItemId = R.id.nav_prayer
//            5 -> binding.bottomNavigation.selectedItemId = R.id.nav_qibla
//            6 -> binding.bottomNavigation.selectedItemId = R.id.nav_bookmark

            else -> {
                Toast.makeText(this, "On Development", Toast.LENGTH_SHORT).show()
            }

        }

    }
}