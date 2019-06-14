package com.example.wayfindingdev.Activity

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.wayfindingdev.Fragment.DomaineFragment
import com.example.wayfindingdev.Fragment.HomeFragment
import com.example.wayfindingdev.Fragment.SchoolFragment
import com.example.wayfindingdev.R
import com.example.wayfindingdev.Tools.currentUser


class HomeActivity : AppCompatActivity() {
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

        bottomNavigationView = findViewById(R.id.bottomNavigationView_home)
        imageView = findViewById(R.id.profile_image)

        val userPic:String = currentUser?.chemin_photo_profil_utilisateur!!

        Glide.with(this)
            .load(userPic)
            .into(imageView)



        dialog = Dialog(this)
        showMsg("Welcome ${currentUser?.nom_utilisateur}")

        val fragmentManager = supportFragmentManager

        val homeFragment = HomeFragment()
        val domaineFragment = DomaineFragment()
        val schoolFragment = SchoolFragment()

        fragmentManager.beginTransaction().add(R.id.framelayout_hom, homeFragment, "1").show(homeFragment).commit()
        fragmentManager.beginTransaction().add(R.id.framelayout_hom, domaineFragment, "2").hide(domaineFragment).commit()
        fragmentManager.beginTransaction().add(R.id.framelayout_hom, schoolFragment, "3").hide(schoolFragment).commit()

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
                        actif = domaineFragment
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
            msgText.text = "Erreur inconnue !"
        }
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()


    }
}
