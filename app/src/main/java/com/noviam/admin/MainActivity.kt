package com.noviam.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView
import com.noviam.admin.kuliner.TambahKuliner
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),  NavigationView.OnNavigationItemSelectedListener {
    lateinit var toogle: ActionBarDrawerToggle
    lateinit var kulinerFragment: HalamanKuliner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toogle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toogle)
        toogle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        nav_view.setNavigationItemSelectedListener(this)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.hal_kuliner -> {
                kulinerFragment = HalamanKuliner()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, kulinerFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
                Toast.makeText(applicationContext, "Sumatra", Toast.LENGTH_SHORT)
                    .show()
            }

            R.id.hal_wisata -> {
                kulinerFragment = HalamanKuliner()
                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, kulinerFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                Toast.makeText(applicationContext, "Sumatra", Toast.LENGTH_SHORT)
                        .show()
            }

            R.id.hal_hotel -> {
                kulinerFragment = HalamanKuliner()
                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, kulinerFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                Toast.makeText(applicationContext, "Sumatra", Toast.LENGTH_SHORT)
                        .show()
            }
        }
            drawerLayout.closeDrawers()
                return true
        }


        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            if (toogle.onOptionsItemSelected(item)) {
                return true
            }
            return super.onOptionsItemSelected(item)
        }
    }
