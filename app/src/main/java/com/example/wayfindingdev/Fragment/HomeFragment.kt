package com.example.wayfindingdev.Fragment


import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.wayfindingdev.Adapter.DomaineAdapter
import com.example.wayfindingdev.Adapter.SchoolAdapter
import com.example.wayfindingdev.Model.Domaine
import com.example.wayfindingdev.Model.DomaineList
import com.example.wayfindingdev.Model.School
import com.example.wayfindingdev.Model.SchoolList
import com.example.wayfindingdev.Network.GetDataService
import com.example.wayfindingdev.Network.RetrofitInstance

import com.example.wayfindingdev.R
import com.example.wayfindingdev.Tools.domaines
import io.supercharge.shimmerlayout.ShimmerLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList
import com.example.wayfindingdev.Tools.domaines
import com.example.wayfindingdev.Tools.schools


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class HomeFragment : Fragment() {
    private lateinit var recyclerViewDomaine: RecyclerView
    private lateinit var recyclerViewSchool:RecyclerView
    private lateinit var progress_home: ConstraintLayout
    private lateinit var refreshLayout: SwipeRefreshLayout

    private lateinit var domaineAdapter: DomaineAdapter

    private lateinit var schoolAdapter: SchoolAdapter

    private lateinit var dataService: GetDataService
    private lateinit var shimmerLayout: ShimmerLayout

    internal val nombre = 10

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerViewDomaine = v.findViewById(R.id.recyclerView_home_domaines)
        recyclerViewSchool = v.findViewById(R.id.recyclerView_home_school)
        progress_home = v.findViewById(R.id.progress_home)
        shimmerLayout = v.findViewById(R.id.shinmerLayout)
        refreshLayout = v.findViewById(R.id.refrech_school)

        intitSchoolData(nombre)
        initDomainesData()

        refreshLayout.setOnRefreshListener {
            initDomainesData()
            intitSchoolData(nombre)
            refreshLayout.isRefreshing = false
        }
        return v
    }


    private fun initDomainesData() {
        dataService = RetrofitInstance.retrofitInstance.create(GetDataService::class.java)
        val call = dataService.getAllDomaines(10)
        call.enqueue(object : Callback<DomaineList> {
            override fun onResponse(call: Call<DomaineList>, response: Response<DomaineList>) {
                if (response.body() != null) {
                    domaines = response.body()!!.domaines!!
                    initRecyclerDomaine(domaines!!)
                } else {
                    Toast.makeText(context, "Aucun élement dans la base de donnée !", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<DomaineList>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun intitSchoolData(top: Int) {
        shimmerLayout.visibility = View.VISIBLE
        shimmerLayout.startShimmerAnimation()
        recyclerViewSchool.visibility = View.GONE

        dataService = RetrofitInstance.retrofitInstance.create(GetDataService::class.java)
        val call = dataService.getSchoolBest(top)
        call.enqueue(object : Callback<SchoolList> {
            override fun onResponse(call: Call<SchoolList>, response: Response<SchoolList>) {
                if (response.body() != null) {
                    if (response.body()!!.statut == 1) {
                        schools = response.body()!!.schools!!
                        initRecyclerSchool(schools!!)
                        shimmerLayout.stopShimmerAnimation()
                        shimmerLayout.visibility = View.INVISIBLE
                        recyclerViewSchool.visibility = View.VISIBLE
                    } else {
                        schools = ArrayList()
                        Toast.makeText(context, response.body()!!.message, Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(context, "Erreur lors de la connexion au serveur", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<SchoolList>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }
        })


    }

    fun initRecyclerDomaine(domaines: List<Domaine>) {
        domaineAdapter = DomaineAdapter(context, domaines)
        recyclerViewDomaine.adapter = domaineAdapter
        recyclerViewDomaine.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

    }

    fun initRecyclerSchool(schools: List<School>) {
        schoolAdapter = SchoolAdapter(context, schools)
        recyclerViewSchool.adapter = schoolAdapter
        recyclerViewSchool.layoutManager = LinearLayoutManager(context)
    }


}
