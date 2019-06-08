package com.example.wayfindingdev.Model

import com.google.gson.annotations.SerializedName

data class School(
    @field:SerializedName("id_ecole")
    var id_ecole:Int, @field:SerializedName("nom_ecole")
    var nom_ecole:String, @field:SerializedName("presentation_ecole")
    var presentation_ecole:String, @field:SerializedName("chemin_logo")
    var chemin_logo:String, @field:SerializedName("chemin_photo_profil_ecole")
    var chemin_photo_profil_ecole:String, @field:SerializedName("chemin_photo_couverture_ecole")
    var chemin_photo_couverture_ecole:String ,@field:SerializedName("quartier")
    var quartier:String, @field:SerializedName("BP")
    var BP:String,@field:SerializedName("emails")
    var emails:String, @field:SerializedName("telephones")
    var telephones:String, @field:SerializedName("longitude")
    var longitude:String, @field:SerializedName("latitude")
    var latitude:String,@field:SerializedName("ville")
    var ville:String, @field:SerializedName("note")
    var note:Float, @field:SerializedName("intitule_type_ecole")
    var intitule_type_ecole:String
)