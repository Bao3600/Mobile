package com.example.foodbuddy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.appcompat.app.ActionBarDrawerToggle
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.fragment.app.FragmentTransaction
import com.example.foodbuddy.Fragment.*
import com.google.android.material.navigation.NavigationView


class NavbarActivity : AppCompatActivity(){

    lateinit var toggle : ActionBarDrawerToggle

    lateinit var homeFragment: HomeFragment
    lateinit var recipesFragment: RecipesFragment
    lateinit var addfriendFragment: AddfriendFragment
    lateinit var myaccountFragment: MyaccountFragment
    lateinit var logoutFragment: LogoutFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navbar)

        val drawerLayout = findViewById<androidx.drawerlayout.widget.DrawerLayout>(R.id.drawerLayout)
        val navView = findViewById<com.google.android.material.navigation.NavigationView>(R.id.navView)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        toggle.isDrawerIndicatorEnabled = true
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            when(it.itemId)
            {
                R.id.itemHome -> Toast.makeText(applicationContext, "Clicked Home", Toast.LENGTH_SHORT).show()
                R.id.itemRecipes -> Toast.makeText(applicationContext, "Clicked Recipes", Toast.LENGTH_SHORT).show()
                R.id.itemAddFriend -> Toast.makeText(applicationContext, "Clicked Friend", Toast.LENGTH_SHORT).show()
                R.id.itemAccount -> Toast.makeText(applicationContext, "Clicked Account", Toast.LENGTH_SHORT).show()
            }
            true
        }
        var navigationView: NavigationView = findViewById(R.id.navView)
        homeFragment = HomeFragment()
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_layout,homeFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()
        navigationView.setNavigationItemSelectedListener { item ->

            // Related fragement
            when (item.itemId) {
                R.id.itemHome -> {
                    homeFragment = HomeFragment()
                    supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.frame_layout, homeFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit()
                            drawerLayout.closeDrawers()
                }
                R.id.itemRecipes -> {
                    recipesFragment = RecipesFragment()
                    supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.frame_layout, recipesFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit()
                            drawerLayout.closeDrawers()
                }
                R.id.itemAddFriend -> {
                    addfriendFragment = AddfriendFragment()
                    supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.frame_layout, addfriendFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit()
                            drawerLayout.closeDrawers()
                }
                R.id.itemAccount -> {
                    myaccountFragment = MyaccountFragment()
                    supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.frame_layout, MyaccountFragment())
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit()
                            drawerLayout.closeDrawers()
                }
                R.id.itemLogout -> {
                    logoutFragment = LogoutFragment()
                    supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.frame_layout, LogoutFragment())
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit()
                            drawerLayout.closeDrawers()
                }

            }
            true

        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
