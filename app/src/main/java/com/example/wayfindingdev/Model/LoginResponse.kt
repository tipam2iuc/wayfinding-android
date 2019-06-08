package com.example.wayfindingdev.Model

import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @field:SerializedName("user")
    var user: User, @field:SerializedName("status")
    var status:Int, @field:SerializedName("message")
    var message:String
)