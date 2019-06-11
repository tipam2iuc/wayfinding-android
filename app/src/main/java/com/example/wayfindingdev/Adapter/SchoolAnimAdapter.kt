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
import com.example.wayfindingdev.Model.School
import com.example.wayfindingdev.R
import com.google.android.gms.maps.MapView
import com.ramotion.foldingcell.FoldingCell
import java.util.ArrayList

class SchoolAnimAdapter(val context:Context,val schoolsList: ArrayList<School>) :
    RecyclerView.Adapter<SchoolAnimAdapter.ViewHolder>(){
    class ViewHolder(itemView: View) :  RecyclerView.ViewHolder(itemView){
        val schoolName:TextView = itemView.findViewById(R.id.textView_school_item_school_name)
        val location:TextView = itemView.findViewById(R.id.textView_item_school_location)
        val nbreFiliere:TextView = itemView.findViewById(R.id.textView_item_school_nombre_filiere)
        val nbreCampus:TextView = itemView.findViewById(R.id.textView_item_school_nombre_campus)
        val note:ProgressBar = itemView.findViewById(R.id.progressBar_item_school_note)
        val profil:ImageView = itemView.findViewById(R.id.imageView_item_school_profil)
        val type:TextView = itemView.findViewById(R.id.textView_item_school_type)
        val creation:TextView = itemView.findViewById(R.id.textView_item_school_creation_date)
        val description:TextView = itemView.findViewById(R.id.textView_item_school_description)
        val btnConsulter:Button = itemView.findViewById(R.id.button_item_school_consulter)
        val folidingCell:FoldingCell = itemView.findViewById(R.id.folding_cell_school)
        val map:MapView = itemView.findViewById(R.id.mapView_school_item_position)

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): SchoolAnimAdapter.ViewHolder {
        val v:View = LayoutInflater.from(context).inflate(R.layout.recycler_item_school,viewGroup,false)

        return ViewHolder(v)
    }

    override fun getItemCount() = schoolsList.size

    override fun onBindViewHolder(viewHolder: SchoolAnimAdapter.ViewHolder, i: Int) {
        val item = schoolsList[i]
        Glide.with(context).load(item.chemin_photo_profil_ecole).fallback(R.drawable.icons_university_48px).into(viewHolder.profil)
        viewHolder.schoolName.text = item.nom_ecole
        //Ange-Raphael-Douala-Cameroun
        viewHolder.location.text = "${item.quartier}-${item.ville}-Cameroun"
        viewHolder.nbreFiliere.text = "10 Filières"
        viewHolder.nbreCampus.text = "03 Campus"
        viewHolder.type.text = item.intitule_type_ecole
        viewHolder.creation.text = "23 mai 2019"
        viewHolder.description.text = item.presentation_ecole
        viewHolder.folidingCell.setOnClickListener{
            viewHolder.folidingCell.toggle(false)
        }
        viewHolder.note.progress = (item.note * 300f / 20f).toInt()
    }

}