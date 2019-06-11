package com.example.wayfindingdev.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import com.example.wayfindingdev.R
import de.hdodenhof.circleimageview.CircleImageView

class Slider1 : AppCompatActivity() {

    private lateinit var smalbig : Animation
    private lateinit var nothingcome : Animation
    private lateinit var btnanime: Animation
    private lateinit var mImageView  : CircleImageView
    private lateinit var way  : TextView
    private lateinit var btn  : Button
    private lateinit var text  : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slider1)
        way= findViewById(R.id.textwayfinding)
        btn= findViewById(R.id.button)
        text= findViewById(R.id.textdesc2)
        mImageView = findViewById(R.id.logo)

        smalbig= AnimationUtils.loadAnimation(this, R.anim.smallbig)
        nothingcome= AnimationUtils.loadAnimation(this, R.anim.nothingtocome)
        btnanime = AnimationUtils.loadAnimation(this, R.anim.btnanim)
        mImageView.startAnimation(smalbig)
        way.startAnimation(nothingcome)
        text.startAnimation(nothingcome)
        btn.startAnimation(btnanime)



        btn.setOnClickListener {
            val i = Intent(this@Slider1, Slider2::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(i)
        }
    }

}

