package com.example.wayfindingdev.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.wayfindingdev.Common.Common
import com.example.wayfindingdev.Model.School
import com.example.wayfindingdev.R
import com.ramotion.foldingcell.FoldingCell

class ShoolAnimAlphabetAdapter (internal val context:Context,internal val schools: List<School>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var inflater = LayoutInflater.from(context)
        return when(viewType){
            Common.VIEWTYPE_GROUP ->{
                val group = inflater.inflate(R.layout.group_layout,viewGroup,false)
                GroupViewHolder(group)
            }
            else -> {
                val group = inflater.inflate(R.layout.recycler_item_school,viewGroup,false)
                SchoolViewHolder(group)
            }
        }
    }

    override fun getItemCount() = schools.size

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if(viewHolder is GroupViewHolder){
            viewHolder.txt_group_type.text = schools[position].nom_ecole
        }
        else if(viewHolder is SchoolViewHolder){
            val item = schools[position]
            Glide.with(context).load(item.chemin_photo_profil_ecole).fallback(R.drawable.icons_university_48px).error(R.drawable.icons_university_48px).into(viewHolder.profil)
            viewHolder.schoolName.text = item.nom_ecole
            viewHolder.location.text = "${item.quartier}-${item.ville}-Cameroun"
            viewHolder.nbreFiliere.text = "10 Filières"
            viewHolder.nbreCampus.text = "03 Campus"

            Glide.with(context).load(item.chemin_photo_profil_ecole).fallback(R.drawable.icons_university_48px).error(R.drawable.icons_university_48px).into(viewHolder.profil2)
            viewHolder.schoolName2.text = item.nom_ecole
            viewHolder.location2.text = "${item.quartier}-${item.ville}-Cameroun"
            viewHolder.nbreFiliere2.text = "10 Filières"
            viewHolder.nbreCampus2.text = "03 Campus"


            viewHolder.type.text = item.intitule_type_ecole
            viewHolder.creation.text = "23 mai 2019"
            viewHolder.description.text = item.presentation_ecole
            viewHolder.folidingCell.setOnClickListener{
                viewHolder.folidingCell.toggle(false)
            }
            viewHolder.note.progress = (item.note * 300f / 20f).toInt()

        }
    }

    override fun getItemViewType(position: Int): Int {
        return schools[position].viewType
    }

    class SchoolViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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
        val folidingCell: FoldingCell = itemView.findViewById(R.id.folding_cell_school)
    }

    internal inner class GroupViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txt_group_type:TextView = itemView.findViewById(R.id.txt_group_title)
    }
}