package com.example.wayfindingdev.Fragment


import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.example.wayfindingdev.Adapter.ConcourAdapter
import com.example.wayfindingdev.Model.Concour
import com.example.wayfindingdev.Model.ConcourList
import com.example.wayfindingdev.Model.SchoolList
import com.example.wayfindingdev.Network.GetDataService
import com.example.wayfindingdev.Network.RetrofitInstance

import com.example.wayfindingdev.R
import com.ramotion.foldingcell.FoldingCell
import kotlinx.android.synthetic.main.fragment_concour.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ConcourFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_concour, container, false)
        val recycler = view.list_concour_fragment

        netGetData(recycler)
        return view
    }

    private fun netGetData(recycler: RecyclerView) {
        val dataService: GetDataService = RetrofitInstance.retrofitInstance.create(GetDataService::class.java)
        val call = dataService.getAllConcour(100)
        val dialog = Dialog(context!!)

        call.enqueue(object : Callback<ConcourList> {
            override fun onFailure(call: Call<ConcourList>, t: Throwable) {
                showErrorMsg(t.message!!,dialog)
            }

            override fun onResponse(call: Call<ConcourList>, response: Response<ConcourList>) {
                val b = response.body()
                if(b?.concours != null){
                    initRec(recycler,b.concours!!)
                }else{
                    showErrorMsg("Erreur de connexion !",dialog)
                }
            }

        })
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


    fun initRec(recycler:RecyclerView,concours:List<Concour>){
        recycler.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        recycler.adapter = ConcourAdapter(this.layoutInflater,concours,R.layout.cell)
    }


}
