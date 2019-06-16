package com.example.wayfindingdev.Adapter

import android.content.Context
import android.support.annotation.LayoutRes
import android.support.annotation.NonNull
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import com.example.wayfindingdev.Model.Concour
import com.example.wayfindingdev.R
import com.ramotion.foldingcell.FoldingCell
import kotlinx.android.synthetic.main.cell.view.*
import kotlinx.android.synthetic.main.cell_content_layout.view.*
import java.util.*


class ConcourAdapter(private val layoutInflater: LayoutInflater,private val concours:List<Concour>,@LayoutRes private val rowLayout:Int) :
    RecyclerView.Adapter<ConcourAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val v = layoutInflater.inflate(rowLayout,parent,false)

        return ViewHolder(v)
    }

    override fun getItemCount() = concours.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val c = concours[position]

        viewHolder.name2.text = c.intitule
        viewHolder.niveau.text = getString(R.string.niveau) + c.niveau?.toString()
        viewHolder.ecole.text = getString(R.string.organise)+ if(c.ecole != null) c.ecole else "inconnue"
        viewHolder.tel.text = c.number?.toString()
        viewHolder.email.text = c.email?.toString()



        val cal = Calendar.getInstance()
        if(concours[position].date != null){

            cal.time = concours[position].date

            viewHolder.day.text = when{
                cal.get(Calendar.DAY_OF_MONTH) < 10 -> "0"+cal.get(Calendar.DAY_OF_MONTH).toString()
                else -> cal.get(Calendar.DAY_OF_MONTH).toString()
            }
            viewHolder.monthYear.text = cal.get(Calendar.MONTH).toString() + "-" + cal.get(Calendar.YEAR).toString()

            viewHolder.day2.text = cal.get(Calendar.DAY_OF_MONTH).toString()
            viewHolder.monthYear2.text = cal.get(Calendar.MONTH).toString() +"-"+ cal.get(Calendar.YEAR).toString()
        }

        viewHolder.names.text = concours[position].intitule
        viewHolder.niveau2.text = getString(R.string.niveau) + concours[position].niveau.toString()
        viewHolder.ecole2.text = getString(R.string.organise)+concours[position].ecole
        viewHolder.tel2.text = c.number?.toString()
        viewHolder.email2.text = c.email?.toString()



        viewHolder.campus.text = concours[position].campus
        viewHolder.prix.text = concours[position].prix.toString() + " XAF"


        if(concours[position].dateLimite != null){
            cal.time = concours[position].dateLimite

            viewHolder.dateLimite.text = cal.get(Calendar.DAY_OF_MONTH).toString()+ "-" + cal.get(Calendar.MONTH).toString() +"-"+ cal.get(Calendar.YEAR).toString()
            viewHolder.description.text = concours[position].decription

        }


        viewHolder.foldingCell.setOnClickListener{
            viewHolder.foldingCell.toggle(false)
        }



    }

    class ViewHolder(val view:View) : RecyclerView.ViewHolder(view) {

        val foldingCell:FoldingCell = view as FoldingCell
        /**
         * close()
         */
        val names:TextView = view.findViewById(R.id.text_view_concour_name2)
        val niveau:TextView = view.findViewById(R.id.text_view_concour_niveau2)
        val ecole:TextView = view.findViewById(R.id.text_view_concour_school2)
        val tel:TextView = view.findViewById(R.id.text_view_concour_tel2)
        val email:TextView = view.findViewById(R.id.text_view_concour_email2)
        val day:TextView = view.findViewById(R.id.text_view_concour_day2)
        val monthYear:TextView = view.findViewById(R.id.text_view_concour_monthyear2)

        /**
         * open()
         */

        val content = view.cell_content

        val name2:TextView = content.findViewById(R.id.text_view_concour_name)
        val niveau2:TextView = content.findViewById(R.id.text_view_concour_niveau)
        val ecole2:TextView = content.findViewById(R.id.text_view_concour_school)
        val tel2:TextView = content.findViewById(R.id.text_view_concour_tel)
        val email2:TextView = content.findViewById(R.id.text_view_concour_email)
        val day2:TextView = content.findViewById(R.id.text_view_concour_day)
        val monthYear2:TextView = content.findViewById(R.id.text_view_concour_monthyear)

        val campus:TextView = content.textView_content_cell_campus
        val prix:TextView = content.textView_content_cell_price
        val dateLimite:TextView = content.textView_content_cell_date_inscription
        val description:TextView = content.textView_content_cell_description

        val btn_inscip:Button = content.btn_content_cell_inscription
        val btn_share = content.btn_content_cell_partage
    }

    fun getString(int: Int):String = layoutInflater.context.resources.getString(int)


}
