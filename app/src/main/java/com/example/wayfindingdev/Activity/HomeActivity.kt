package com.example.wayfindingdev.Activity

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.wayfindingdev.Fragment.ConcourFragment
import com.example.wayfindingdev.Fragment.DomaineFragment
import com.example.wayfindingdev.Fragment.HomeFragment
import com.example.wayfindingdev.Fragment.SchoolFragment
import com.example.wayfindingdev.R
import com.example.wayfindingdev.R.id.toolbar
import com.example.wayfindingdev.Tools.currentUser
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {
    private lateinit var dialog:Dialog
    private lateinit var closeMsg:ImageButton
    private lateinit var msgText:TextView

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var OnNavigationItemReselectedListener: BottomNavigationView.OnNavigationItemSelectedListener
    private lateinit var actif: Fragment
    private lateinit var imageView: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
       val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navview  : NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.map, R.string.map
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navview.setNavigationItemSelectedListener(this)

        bottomNavigationView = findViewById(R.id.bottomNavigationView_home)
        imageView = findViewById(R.id.profile_image)

        val userPic:String? = currentUser?.chemin_photo_profil_utilisateur

        if(userPic != null){
            Glide.with(this)
                .load(userPic)
                .into(imageView)
        }



        dialog = Dialog(this)
        showMsg("Welcome ${currentUser?.nom_utilisateur}")

        val fragmentManager = supportFragmentManager

        val homeFragment = HomeFragment()
        val domaineFragment = DomaineFragment()
        val schoolFragment = SchoolFragment()
        val concourFragment = ConcourFragment()

        fragmentManager.beginTransaction().add(R.id.framelayout_hom, homeFragment, "1").show(homeFragment).commit()
        fragmentManager.beginTransaction().add(R.id.framelayout_hom, domaineFragment, "2").hide(domaineFragment).commit()
        fragmentManager.beginTransaction().add(R.id.framelayout_hom, schoolFragment, "3").hide(schoolFragment).commit()
        fragmentManager.beginTransaction().add(R.id.framelayout_hom, concourFragment, "4").hide(concourFragment).commit()

        actif = homeFragment

        OnNavigationItemReselectedListener =
            BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
                return@OnNavigationItemSelectedListener when (menuItem.itemId) {
                    R.id.menu_item_home -> {
                        fragmentManager.beginTransaction().hide(actif).show(homeFragment).commit()
                        actif = homeFragment
                        true
                    }
                    R.id.menu_item_domaines ->{
                        fragmentManager.beginTransaction().hide(actif).show(domaineFragment).commit()
                        actif = domaineFragment
                        true
                    }
                    R.id.menu_item_Ecoles -> {
                        fragmentManager.beginTransaction().hide(actif).show(schoolFragment).commit()
                        actif = schoolFragment
                        true
                    }
                    R.id.menu_item_Concours -> {
                        fragmentManager.beginTransaction().hide(actif).show(concourFragment).commit()
                        actif = concourFragment
                        true
                    }
                    else -> true
                }
            }
        bottomNavigationView.setOnNavigationItemSelectedListener(OnNavigationItemReselectedListener)


    }
    private fun showMsg(message: String?) {
        dialog.setContentView(R.layout.layout_popup_login)
        closeMsg = dialog.findViewById(R.id.imageButton_close_msg)
        msgText = dialog.findViewById(R.id.textview_msg_text)

        closeMsg.setOnClickListener {dialog.dismiss()}
        if (message != null) {
            msgText.text = message
        }else{
            msgText.text = resources.getString(R.string.errorText)
        }
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()


    }
    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.toolbar -> true
            else -> super.onOptionsItemSelected(item)
        }


    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {

        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }


}

