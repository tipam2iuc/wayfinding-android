package com.example.wayfindingdev.Model

import com.google.gson.annotations.SerializedName
import retrofit2.http.Field


data class Domaine(
    @field:SerializedName("id_domaine")
    var id_domaine: Int, @field:SerializedName("nom_domaine")
    var nom_domaine: String, @field:SerializedName("description_domaine")
    var description_domaine: String,@field:SerializedName("id_secteur")
    var id_secteur:Int, @field:SerializedName("intiule_secteur")
    var intiule_secteur:String
)
