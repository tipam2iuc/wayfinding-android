package com.example.wayfindingdev.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.wayfindingdev.Adapter.SchoolAnimAdapter
import com.example.wayfindingdev.Model.School
import com.example.wayfindingdev.R
import com.ramotion.foldingcell.FoldingCell
import java.util.ArrayList

class AnimRecycler : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anim_recycler)

        val recyclerView:RecyclerView = findViewById(R.id.recycler_test)

        val scholls:ArrayList<School> = ArrayList()
        scholls.add(School(1,"IUC","KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK"
            ,"any","any","any","Logbessou","100","mlml","66666","1.222","1.22"
        ,"Douala",14.5f,"Etat"
        ))
        scholls.add(School(1,"IUC","KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK"
            ,"any","any","any","Logbessou","100","mlml","66666","1.222","1.22"
        ,"Douala",14.5f,"Etat"
        ))
        scholls.add(School(1,"IUC","KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK"
            ,"any","any","any","Logbessou","100","mlml","66666","1.222","1.22"
        ,"Douala",14.5f,"Etat"
        ))
        scholls.add(School(1,"IUC","KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK"
            ,"any","any","any","Logbessou","100","mlml","66666","1.222","1.22"
        ,"Douala",14.5f,"Etat"
        ))
        scholls.add(School(1,"IUC","KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK"
            ,"any","any","any","Logbessou","100","mlml","66666","1.222","1.22"
        ,"Douala",14.5f,"Etat"
        ))
        scholls.add(School(1,"IUC","KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK"
            ,"any","any","any","Logbessou","100","mlml","66666","1.222","1.22"
        ,"Douala",14.5f,"Etat"
        ))

        val schoolAnimRecycler:SchoolAnimAdapter = SchoolAnimAdapter(this,scholls)
        recyclerView.adapter = schoolAnimRecycler
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}
