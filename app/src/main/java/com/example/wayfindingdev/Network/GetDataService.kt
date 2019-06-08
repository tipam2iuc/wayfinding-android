package com.example.wayfindingdev.Network

import com.example.wayfindingdev.Model.DomaineList
import com.example.wayfindingdev.Model.LoginResponse
import com.example.wayfindingdev.Model.SchoolList
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface GetDataService {
    @FormUrlEncoded
    @POST("Ecoles/getEcolesBestLimit_BLL.php")
    fun getSchoolBest(
        @Field("limit") limit:Int
    ):Call<SchoolList>

    @FormUrlEncoded
    @POST("User/Login_BLL.php")
    fun Login(
        @Field("email") email:String,
        @Field("password") password:String
    ):Call<LoginResponse>

    @FormUrlEncoded
    @POST("domaines/getDomainesAll.php")
    fun getAllDomaines(
        @Field("bb") dd: Int
    ): Call<DomaineList>

}