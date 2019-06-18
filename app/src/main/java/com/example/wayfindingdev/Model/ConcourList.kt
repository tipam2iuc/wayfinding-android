package com.example.wayfindingdev.Model

import com.google.gson.annotations.SerializedName

class ConcourList {
    @SerializedName("concours") var concours:List<Concour>? = null
    @SerializedName("statut") var statut:Int? = 0
}