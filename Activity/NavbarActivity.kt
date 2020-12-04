package com.example.foodbuddy

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.example.foodbuddy.API.*
import com.google.android.material.navigation.NavigationView
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpCookie
import com.intellif.dblib.bean.Food
import com.intellif.dblib.bean.FoodDatabase
import com.blankj.utilcode.util.KeyboardUtils


class NavbarActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle
    lateinit var editText: EditText
    lateinit var editText2: EditText
    lateinit var editText3: EditText
    lateinit var button: Button
    lateinit var btnUpdate: Button
    lateinit var listView: ListView
    var list: ArrayList<String> = ArrayList()

    var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navbar)
        initView()
        val drawerLayout =
            findViewById<DrawerLayout>(R.id.drawerLayout)
        val navView =
            findViewById<NavigationView>(R.id.navView)

        toggle = ActionBarDrawerToggle(
            this, drawerLayout,
            R.string.open,
            R.string.close
        )
        toggle.isDrawerIndicatorEnabled = true
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()



        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.itemHome -> Toast.makeText(
                    applicationContext,
                    "Clicked Home",
                    Toast.LENGTH_SHORT
                ).show()
                R.id.itemRecipes -> Toast.makeText(
                    applicationContext,
                    "Clicked Recipes",
                    Toast.LENGTH_SHORT
                ).show()
                R.id.itemAccount -> Toast.makeText(
                    applicationContext,
                    "Clicked Account",
                    Toast.LENGTH_SHORT
                ).show()
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
                }
                R.id.itemRecipes -> {
                    //recipesFragment = RecipesFragment()
//                    supportFragmentManager
//                        .beginTransaction()
//                    val intent = Intent(this, RecipesActivity::class.java);
//                    startActivity(intent)
//                    drawerLayout.closeDrawers()

                    toRecipes()
                }
                //region

                R.id.itemAccount -> {
                    toMyAccount()
                }
                /*R.id.itemLogout -> {
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

        var arrayAdapter = MessageAdapter(
            this,
            R.layout.item_nav,
            list
        )
        val db=FoodDatabase.getInstance(this).getFoodDao()
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

        val search = findViewById<SearchView>(R.id.searchView)
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                search.clearFocus()
                if (list.contains(query)) {
                    arrayAdapter.filter.filter((query))
                } else {
                    Toast.makeText(applicationContext, "Item not found", Toast.LENGTH_LONG).show()
                }
                arrayAdapter.notifyDataSetChanged()
                return false
            }


            override fun onQueryTextChange(query: String?): Boolean {
                arrayAdapter.filter.filter(query)
                arrayAdapter.notifyDataSetChanged()
                return false
            }


        })

        // arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)

//        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1)
//        listView.adapter = arrayAdapter

        //添加
        button.setOnClickListener {
            var text = editText.text.toString()
            var text2 = editText2.text.toString()
            var text3 = editText3.text.toString()
            if (TextUtils.isEmpty(text) && TextUtils.isEmpty(text2) && TextUtils.isEmpty(text3)) {
                Toast.makeText(this, "Content can not be blank", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            list.add("$text  $text2  $text3")
            val food = Food(foodName = "$text",Num = "$text2",date = "$text3")
            val insert = db.insert(food)
            EzLog.d("inset-->${insert}")
            arrayAdapter = MessageAdapter(
                this,
                R.layout.item_nav,
                list
            )
            listView.adapter = arrayAdapter
            editText.setText("")
            editText2.setText("")
            editText3.setText("")
            KeyboardUtils.hideSoftInput(this)
        }



        //修改
        btnUpdate.setOnClickListener {
            val text = editText.text.toString()
            val text2 = editText2.text.toString()
            val text3 = editText3.text.toString()
            if (TextUtils.isEmpty(text) || TextUtils.isEmpty(text2) || TextUtils.isEmpty(text3)) {
                Toast.makeText(this, "Please enter the content to be updated", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val food = Food(foodName = "$text",Num = "$text2",date = "$text3")
            val update = db.update("$text","$text2","$text3")
            EzLog.d("update-->${update}")
            if(update==0){
                val date = list[position].split("  ")[2]
                val delete = db.delete(date)
                EzLog.d("delete--->${date}   ${delete}")
                val insert = db.insert(food)
                EzLog.d("update inset-->${insert}")
            }
            editText.setText("")
            editText2.setText("")
            editText3.setText("")
            list[position] = "$text  $text2  $text3"
            arrayAdapter.notifyDataSetChanged()
            KeyboardUtils.hideSoftInput(this)
        }
        val dialog = MyDialog(this)

        listView.setOnItemClickListener { adapterView, view, i, l ->
            run {
                position = i
                val bean = list[i]
                val split = bean.split("  ")
                dialog.deleteListener = {

                    arrayAdapter =
                        MessageAdapter(
                            this,
                            R.layout.item_nav,
                            list
                        )
                    val date = list[position].split("  ")[2]
                    val delete = db.delete(date)
                    EzLog.d("delete--->${date}   ${delete}")
                    list.removeAt(position)
                    listView.adapter = arrayAdapter
                    arrayAdapter.notifyDataSetChanged()
                }
                dialog.updateListener = {
                    editText.setText(split[0])
                    editText2.setText(split[1])
                    editText3.setText(split[2])
                }
                dialog.show()
                Log.d("ez", "onCreate: ${split[0]} ${split[1]} ${split[2]}")
            }
        }
    }


    private fun initView() {
        listView = findViewById(R.id.listView)
        editText = findViewById(R.id.editText)
        editText2 = findViewById(R.id.editText2)
        editText3 = findViewById(R.id.editText3)
        button = findViewById(R.id.btnAdd)
        btnUpdate = findViewById(R.id.btnUpdate)
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
