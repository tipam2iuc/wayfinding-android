package com.example.wayfindingdev.Adapter

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.wayfindingdev.Model.School
import com.example.wayfindingdev.R
import kotlinx.android.synthetic.main.cell_content_school_layout.view.*
import kotlinx.android.synthetic.main.cell_school.view.*

class ShoolAdapter (private val layoutInflater: LayoutInflater, private val schools:List<School>, @LayoutRes private val rowLayout:Int):
    RecyclerView.Adapter<ShoolAdapter.ViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ShoolAdapter.ViewHolder {
        val v = layoutInflater.inflate(rowLayout,parent,false)
        return ViewHolder(v)
    }


    override fun getItemCount() = schools.size

    override fun onBindViewHolder(viewHolder: ShoolAdapter.ViewHolder, position: Int) {
        val item = schools[position]
        viewHolder.schoolName.text = item.nom_ecole
        viewHolder.location.text = "${item.quartier}-${item.ville}-Cameroun"
        viewHolder.nbreFiliere.text = "10 Filières"
        viewHolder.nbreCampus.text = "03 Campus"
        Glide.with(layoutInflater.context)
            .load(schools[position].chemin_logo)
            .fallback(R.drawable.icons_university_48px)
            .error(R.drawable.icons_university_48px)
            .into(viewHolder.profil)

        Glide.with(layoutInflater.context)
            .load(schools[position].chemin_logo)
            .fallback(R.drawable.icons_university_48px)
            .error(R.drawable.icons_university_48px)
            .into(viewHolder.profil2)

        Glide.with(layoutInflater.context)
            .load(schools[position].chemin_photo_couverture_ecole)
            .fallback(R.drawable.arr)
            .error(R.drawable.arr)
            .into(viewHolder.couverture)

        viewHolder.schoolName2.text = item.nom_ecole
        viewHolder.location2.text = "${item.quartier}-${item.ville}-Cameroun"
        viewHolder.nbreFiliere2.text = "10 Filières"
        viewHolder.nbreCampus2.text = "03 Campus"


        viewHolder.type.text = item.intitule_type_ecole
        viewHolder.creation.text = "23 mai 2019"
        viewHolder.description.text = item.presentation_ecole
        viewHolder.note.progress = (item.note * 300f / 20f).toInt()
        viewHolder.note2.progress = (item.note * 300f / 20f).toInt()

        viewHolder.folding_cell_school.setOnClickListener {
            viewHolder.folding_cell_school.toggle(false)
        }
    }




    class ViewHolder(val view:View) : RecyclerView.ViewHolder(view) {
        val schoolName2:TextView = itemView.findViewById(R.id.textView_school_item_school_name)
        val location2:TextView = itemView.findViewById(R.id.textView_item_school_location)
        val nbreFiliere2:TextView = itemView.findViewById(R.id.textView_item_school_nombre_filiere)
        val nbreCampus2:TextView = itemView.findViewById(R.id.textView_item_school_nombre_campus)
        val note2: ProgressBar = itemView.findViewById(R.id.progressBar_item_school_note)
        val profil2: ImageView = itemView.findViewById(R.id.imageView_item_school_profil)

        val schoolName:TextView = itemView.findViewById(R.id.textView_school_item_school_name2)
        val location:TextView = itemView.findViewById(R.id.textView_item_school_location2)
        val nbreFiliere:TextView = itemView.findViewById(R.id.textView_item_school_nombre_filiere2)
        val nbreCampus:TextView = itemView.findViewById(R.id.textView_item_school_nombre_campus2)
        val note: ProgressBar = itemView.findViewById(R.id.progressBar_item_school_note2)
        val profil: ImageView = itemView.findViewById(R.id.imageView_item_school_profil2)

        val type:TextView = itemView.findViewById(R.id.textView_item_school_type)
        val creation:TextView = itemView.findViewById(R.id.textView_item_school_creation_date)
        val description:TextView = itemView.findViewById(R.id.textView_item_school_description)
        val btnConsulter: Button = itemView.findViewById(R.id.button_item_school_consulter)
        val couverture = itemView.imageView_item_couverture_school

        val folding_cell_school = view.folding_cell_school

    }

}