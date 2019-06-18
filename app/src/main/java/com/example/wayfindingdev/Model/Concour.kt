package com.example.wayfindingdev.Model

import com.google.gson.annotations.SerializedName
import java.util.*

class Concour(@field:SerializedName("id_concours") var id:Int,
              @field:SerializedName("nom") var intitule:String?,
              @field:SerializedName("date_limite_des_dossiers") var dateLimite:Date?,
              @field:SerializedName("date_debut_concours") var date: Date?,
              @field:SerializedName("nom_ecole") var ecole:String?,
              @field:SerializedName("emails") var email:String?,
              @field:SerializedName("telephones") var number:Int?,
              @field:SerializedName("description") var decription:String?,
              @field:SerializedName("niveau") var niveau:Int?,
              @field:SerializedName("nom_campus") var campus:String?,
              @field:SerializedName("photo") var photo:String?,
              @field:SerializedName("prix") var prix:Int?
)