package com.example.wayfindingdev.Model

import com.google.gson.annotations.SerializedName


class DomaineList {
    @SerializedName("domaines")
    var domaines: List<Domaine>? = null
}
