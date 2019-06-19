package com.example.wayfindingdev.Fragment


import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.example.wayfindingdev.Adapter.RecyclerSectionItemDecoration
import com.example.wayfindingdev.Adapter.ShoolAdapter
import com.example.wayfindingdev.Model.School
import com.example.wayfindingdev.Model.SchoolList
import com.example.wayfindingdev.Network.GetDataService
import com.example.wayfindingdev.Network.RetrofitInstance

import com.example.wayfindingdev.R
import kotlinx.android.synthetic.main.fragment_school.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class SchoolFragment : Fragment() {

    lateinit var schools : List<School>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_school, container, false)

        val recycler = view.recycler_school_fragement
        //initSchoolRecyclerView(recycler,getSchool())


         netWorkData(recycler)




        return view
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

    private fun netWorkData(recycler: RecyclerView) {
        val dataService: GetDataService = RetrofitInstance.retrofitInstance.create(GetDataService::class.java)
        val call = dataService.getSchoolBest(100)
        val dialog = Dialog(context!!)

        call.enqueue(object : Callback<SchoolList> {
            override fun onFailure(call: Call<SchoolList>, t: Throwable) {
                showErrorMsg(t.message!!,dialog)
            }

            override fun onResponse(call: Call<SchoolList>, response: Response<SchoolList>) {
                val b = response.body()
                if(b != null){
                    if(b.statut == 1){
                        initSchoolRecyclerView(recycler,b.schools!!.sortedBy { x->x.nom_ecole })
                    } else {
                        showErrorMsg(b.message,dialog)
                    }
                }else{
                    showErrorMsg("Erreur de connexion !",dialog)
                }
            }

        })
    }

    fun initSchoolRecyclerView(recycler: RecyclerView, schools: List<School>){
        recycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)

        val sectionItemDecoration = RecyclerSectionItemDecoration(resources.getDimensionPixelSize(R.dimen.recycler_section_header_height),sticky=true,sectionCallback=getSectionCallback(schools))

        recycler.addItemDecoration((sectionItemDecoration))

        val h = setShool(schools)!!

        recycler.adapter = ShoolAdapter(this.layoutInflater,h,R.layout.cell_school)
    }

    private fun setShool(schools: List<School>?) : List<School>?{
        this.schools = schools!!
        this.schools.sortedBy { x -> x.nom_ecole }

        return schools
    }

    private fun showErrorMsg(message: String?,dialog:Dialog) {
        dialog.setContentView(R.layout.layout_popup_error_login)
        val closeMsg: ImageButton = dialog.findViewById(R.id.imageButton_close_msg)
        val msgText: TextView = dialog.findViewById(R.id.textview_msg_text)

        closeMsg.setOnClickListener {
            dialog.dismiss()
        }
        if (message != null) {
            msgText.text = message
        }else{
            msgText.text = "Erreur inconnue !"
        }
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()


    }

    private fun getSchool():ArrayList<School>{
        val s = School(1,"Université de douala",context?.resources!!.getString(R.string.lorem),"any","any","any","Ange Raphael","1025","mevaajules1566@mail.com","690981056","1","1","Douala",15f,"any")
        val h = ArrayList<School>()

        h.add(s)
        h.add(s)
        h.add(s)
        h.add(s)
        h.add(s)
        h.add(s)
        h.add(s)
        h.add(s)
        h.add(s)
        h.add(s)
        h.add(s)
        h.add(s)
        h.add(s)
        h.add(s)

        return h
    }


}
