package com.example.wayfindingdev.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.SwitchCompat
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.wayfindingdev.R

class ProfileActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)


        val returnHome = findViewById<ImageView>  (R.id.arrow)
        returnHome.setOnClickListener{

            val i = Intent(this@ProfileActivity, HomeActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(i)
        }


    }


}
