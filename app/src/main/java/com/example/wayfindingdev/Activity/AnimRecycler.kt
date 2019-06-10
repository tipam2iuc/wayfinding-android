package com.example.wayfindingdev.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.wayfindingdev.R
import com.ramotion.foldingcell.FoldingCell

class AnimRecycler : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anim_recycler)

        val fcp = findViewById<View>(R.id.folding_cell) as FoldingCell

        fcp.setOnClickListener { fcp.toggle(false) }
    }
}
