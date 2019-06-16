package com.example.wayfindingdev.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.wayfindingdev.Adapter.RecyclerSectionItemDecoration
import com.example.wayfindingdev.Adapter.ShoolAdapter
import com.example.wayfindingdev.Common.Common
import com.example.wayfindingdev.Model.School
import com.example.wayfindingdev.R
import kotlinx.android.synthetic.main.activity_anim_recycler.*
import java.util.*

class AnimRecycler : AppCompatActivity() {
    internal var schools = ArrayList<School>()
    internal lateinit var layoutManager: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anim_recycler)

        recycler_test.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        val schools = getSchool()

        val sectionItemDecoration = RecyclerSectionItemDecoration(getResources().getDimensionPixelSize(R.dimen.recycler_section_header_height),sticky=true,sectionCallback=getSectionCallback(schools))
        recycler_test.addItemDecoration((sectionItemDecoration))

        recycler_test.adapter = ShoolAdapter(this.layoutInflater,schools,R.layout.recycler_item_school)


    }

    private fun getSectionCallback(schools: List<School>): RecyclerSectionItemDecoration.SectionCallback {
        return object : RecyclerSectionItemDecoration.SectionCallback {
            override fun isSection(position: Int): Boolean {
                return position == 0 || schools[position].nom_ecole[0] != schools[position - 1].nom_ecole[0]
            }

            override fun getSectionHeader(position: Int): CharSequence {
                return schools[position].nom_ecole.subSequence(0,1)
            }
        }
    }

    private fun getSchool() : List<School> {
        val school:List<School> = Common.genSchoolGroup()
        val l = school.sortedBy { x->x.nom_ecole }
        return l
    }


}
