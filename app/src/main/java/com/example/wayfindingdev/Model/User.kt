package com.example.wayfindingdev.Model

import com.google.gson.annotations.SerializedName
import java.util.*

class User{
    @SerializedName("id_utilisateur")
    var id_utilisateur: Int = 0
    @SerializedName("nom_utilisateur")
    var nom_utilisateur: String? = null
    @SerializedName("prenom_utilisateur")
    var prenom_utilisateur: String? = null
    @SerializedName("sexe_utilisateur")
    var sexe_utilisateur: Int = 0
    @SerializedName("date_naissance")
    var date_naissance: Date? = null
    @SerializedName("email_utilisateur")
    var email_utilisateur: String? = null
    @SerializedName("mot_de_passe")
    var mot_de_passe: String? = null
    @SerializedName("chemin_photo_profil_utilisateur")
    var chemin_photo_profil_utilisateur: String? = null
    @SerializedName("chemin_photo_couverture_utilisateur")
    var chemin_photo_couverture_utilisateur: String? = null


    constructor()
}