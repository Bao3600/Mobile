package com.example.foodbuddy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.FragmentTransaction
import com.example.foodbuddy.Fragment.*
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_home.*


class NavbarActivity : AppCompatActivity(){

    lateinit var toggle : ActionBarDrawerToggle
    lateinit var editText: EditText
    lateinit var button: Button
    lateinit var listView: ListView
    var list: ArrayList<String> = ArrayList()
    lateinit var arrayAdapter: ArrayAdapter<String>

    /*lateinit var homeFragment: HomeFragment
    lateinit var recipesFragment: RecipesFragment
    lateinit var addfriendFragment: AddfriendFragment
    lateinit var myaccountFragment: MyaccountFragment
    lateinit var logoutFragment: LogoutFragment
*/

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
        //homeFragment = HomeFragment()
       /* supportFragmentManager
            .beginTransaction()
            val intent = Intent(this, NavbarActivity::class.java);
            startActivity(intent)*/


        navigationView.setNavigationItemSelectedListener { item ->

            // Related fragement
            when (item.itemId) {
                R.id.itemHome -> {
                    //homeFragment = HomeFragment()
                    supportFragmentManager
                            .beginTransaction()
                            val intent = Intent(this, NavbarActivity::class.java);
                            startActivity(intent)
                            drawerLayout.closeDrawers()
                }
                /*R.id.itemRecipes -> {
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
*/
            }
            true

        }
        listView = findViewById(R.id.listView)
        editText = findViewById(R.id.editText)
        button = findViewById(R.id.btnAdd)
        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
        button.setOnClickListener {
            list.add(editText.text.toString())
            editText.setText(" ")
            arrayAdapter.notifyDataSetChanged()
            listView.adapter = arrayAdapter
        }
        val search = findViewById<SearchView>(R.id.searchView)
        search .setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                search.clearFocus()
                if (list.contains(query))
                {
                    arrayAdapter.filter.filter((query))
                }else{
                    Toast.makeText(applicationContext,"Item not found", Toast.LENGTH_LONG).show()
                }
                return false
            }


            override fun onQueryTextChange(newText: String?): Boolean {
                arrayAdapter.filter.filter(newText)
                return false
            }

        })
      

}

override fun onOptionsItemSelected(item: MenuItem): Boolean {

 if (toggle.onOptionsItemSelected(item)) {
     return true
 }
 return super.onOptionsItemSelected(item)
}
}
