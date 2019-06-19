package com.example.wayfindingdev.Fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.wayfindingdev.Adapter.DomainePageAdapter
import com.example.wayfindingdev.Model.Domaine
import com.example.wayfindingdev.Model.DomaineList
import com.example.wayfindingdev.Network.GetDataService
import com.example.wayfindingdev.Network.RetrofitInstance

import com.example.wayfindingdev.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class DomaineFragment : Fragment() {
    private lateinit var dataService: GetDataService
    private lateinit var domaines: List<Domaine>
    private lateinit var primaire: RecyclerView
    private lateinit var tertiaire:RecyclerView
    private lateinit var indust:RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var nbreInTech: TextView
    private lateinit var nbrePrim:TextView
    private lateinit var nbreTer:TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_domaine, container, false)
        // Inflate the layout for this fragment
        primaire = v.findViewById(R.id.recyclerView_domaines_prim)
        tertiaire = v.findViewById(R.id.recyclerView_domaines_ter)
        indust = v.findViewById(R.id.recyclerView_domaines_tech_ind)
        swipeRefreshLayout = v.findViewById(R.id.refrech_domaine)
        nbreInTech = v.findViewById(R.id.textView_nbre_domaine_tech_ind)
        nbrePrim = v.findViewById(R.id.textView_domaine_nbre_prim)
        nbreTer = v.findViewById(R.id.textView_domaine_nbre_ter)

        swipeRefreshLayout.setOnRefreshListener {
            initDomainesData()
            swipeRefreshLayout.isRefreshing = false
        }

        initDomainesData()

        return v
    }

    private fun initDomainesData() {

        dataService = RetrofitInstance.retrofitInstance.create(GetDataService::class.java)
        val call = dataService.getAllDomaines(10)
        call.enqueue(object : Callback<DomaineList> {
            override fun onResponse(call: Call<DomaineList>, response: Response<DomaineList>) {
                if (response.body() != null) {
                    domaines = response.body()!!.domaines!!
                    initAllRecyclerDomaine(domaines)

                } else {
                    Toast.makeText(context, "Aucun élement dans la base de donnée !", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<DomaineList>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun initAllRecyclerDomaine(domaines: List<Domaine>) {
        val indu = ArrayList<Domaine>()
        val pri = ArrayList<Domaine>()
        val ter = ArrayList<Domaine>()

        for (item in domaines) {
            when {
                item.id_secteur == 1 -> indu.add(item)
                item.id_secteur == 2 -> ter.add(item)
                else -> pri.add(item)
            }
        }
        initRecyclerDomaine(pri, primaire)
        var texte:String
        var debut = ""
        if (pri.size < 1)
            texte = " Domaine trouvé"
        else if (pri.size < 10) {
            debut = "0"
            texte = " Domaine trouvés"
        } else {
            texte = " Domaines trouvés"
        }
        nbrePrim.text = debut + pri.size + texte
        initRecyclerDomaine(ter, tertiaire)
        if (pri.size < 1)
            texte = " Domaine trouvé"
        else if (pri.size < 10) {
            debut = "0"
            texte = " Domaine trouvés"
        } else {
            texte = " Domaines trouvés"
        }
        nbreTer.text = debut + ter.size + texte
        initRecyclerDomaine(indu, indust)
        if (pri.size < 1)
            texte = " Domaine trouvé"
        else if (pri.size < 10) {
            debut = "0"
            texte = " Domaine trouvés"
        } else {
            texte = " Domaines trouvés"
        }
        nbreInTech.text = debut + indu.size + texte

    }

    fun initRecyclerDomaine(domaines: List<Domaine>, recyclerView: RecyclerView) {
        val domaineAdapter = DomainePageAdapter(context, domaines)
        recyclerView.adapter = domaineAdapter
        recyclerView.layoutManager = GridLayoutManager(context, 2) as RecyclerView.LayoutManager?

    }


}
