package com.example.wayfindingdev.Model

import com.google.gson.annotations.SerializedName

class SchoolList (

    @field:SerializedName("school")
    var schools: List<School>? = null,
    @field:SerializedName("statut")
    var statut: Int = 0,
    @field:SerializedName("message")
    var message: String? = null
)
