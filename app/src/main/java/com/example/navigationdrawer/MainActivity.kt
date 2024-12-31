package com.example.navigationdrawer

import android.os.Bundle
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.navigationdrawer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView(){
        val header = binding.navView.getHeaderView(0)
        val llProfile = header.findViewById<LinearLayoutCompat>(R.id.llProfile)
        val imgProfile = header.findViewById<AppCompatImageView>(R.id.imgProfile)
        val tvUserName = header.findViewById<TextView>(R.id.tvUserName)
        val tvMno = header.findViewById<TextView>(R.id.tvMno)

        llProfile.setOnClickListener {
            binding.drawerLayout.closeDrawer(GravityCompat.START)

            // Open profile or register activity
        }

        // Set up the DrawerListener
        binding.drawerLayout.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerSlide(drawerView: android.view.View, slideOffset: Float) {
                // Called when a drawer's position changes
                println("Drawer sliding with offset: $slideOffset")
            }

            override fun onDrawerOpened(drawerView: android.view.View) {
                // Called when the drawer is fully opened
             //   Glide.with(this@MainActivity).load(getUserImage().toString()).placeholder(R.drawable.ic_camera).into(imgProfile)
            }

            override fun onDrawerClosed(drawerView: android.view.View) {
                // Called when the drawer is fully closed
                println("Drawer closed")
            }

            override fun onDrawerStateChanged(newState: Int) {
                // Called when the drawer motion state changes (Idle, Dragging, Settling)
                println("Drawer state changed to: $newState")
            }
        })

        binding.navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    findNavController(R.id.nav_host_fragment).navigate(R.id.nav_home)
                }

                R.id.nav_profile -> {
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    findNavController(R.id.nav_host_fragment).navigate(R.id.nav_profile)
                }
            }
            true
        }

    }

    fun openDrawer() {
        binding.drawerLayout.openDrawer(GravityCompat.START)
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}