package com.example.foodbuddy

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.navigation.NavigationView


class MyAccountActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_account)
        val drawerLayout =
                findViewById<androidx.drawerlayout.widget.DrawerLayout>(R.id.drawerLayout)
        val navView =
                findViewById<com.google.android.material.navigation.NavigationView>(R.id.navView)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        toggle.isDrawerIndicatorEnabled = true
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        var navigationView: NavigationView = findViewById(R.id.navView)

        navigationView.setNavigationItemSelectedListener { item ->

            // Related fragement
            when (item.itemId) {
                R.id.itemHome -> {
                    //homeFragment = HomeFragment()
                    supportFragmentManager
                            .beginTransaction()
                    val intent = Intent(this, NavbarActivity::class.java);
                    startActivity(intent)
                }
                R.id.itemRecipes -> {
                    toRecipes()
                }
                //region

                R.id.itemAccount -> {
                    toMyAccount()
                }
                /* R.id.itemLogout -> {
                     logoutFragment = LogoutFragment()
                     supportFragmentManager
                             .beginTransaction()
                             .replace(R.id.frame_layout, LogoutFragment())
                             .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                             .commit()
                             drawerLayout.closeDrawers()
                 }
 */
                //endregion
            }
            drawerLayout.closeDrawers()
            true

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun toRecipes() {
        launchActivity<RecipesSuggestionActivity>()
    }

    private fun toMyAccount() {
        launchActivity<MyAccountActivity>()
    }
}
