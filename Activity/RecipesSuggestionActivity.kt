package com.example.foodbuddy

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ListView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.navigation.NavigationView
import com.intellif.dblib.bean.FoodDatabase


class RecipesSuggestionActivity : AppCompatActivity() {

    var list: ArrayList<String> = ArrayList()
    lateinit var listView: ListView
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipes)

        listView = findViewById(R.id.listView)

        val drawerLayout =
            findViewById<androidx.drawerlayout.widget.DrawerLayout>(R.id.drawerLayout)
        val navView =
            findViewById<com.google.android.material.navigation.NavigationView>(R.id.navView)

        toggle = ActionBarDrawerToggle(
            this, drawerLayout,
            R.string.open,
            R.string.close
        )
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

                //endregion
            }
            drawerLayout.closeDrawers()
            true

        }

        initAdapter()
        itemClick()
    }

    private fun initAdapter() {
        val db = FoodDatabase.getInstance(this).getFoodDao()
        val allFood = db.getAllFood()
        if (allFood != null && allFood.isNotEmpty()) {
            for (i in allFood.indices) {
                list.add("${allFood[i].foodName}  ${allFood[i].Num}  ${allFood[i].date}")
            }
            var arrayAdapter = MessageAdapter(
                this,
                R.layout.item_nav,
                list
            )
            listView.adapter = arrayAdapter
        }
    }

    private fun itemClick(){
        val dialog = RecipesDialog(this)
        listView.setOnItemClickListener { adapterView, view, i, l ->
            run {
                val bean = list[i]
                val split = bean.split("  ")
                dialog.itemClickListener = {
                    
                }
                dialog.show()
                Log.d("ez", "onCreate: ${split[0]} ${split[1]} ${split[2]}")
            }
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
