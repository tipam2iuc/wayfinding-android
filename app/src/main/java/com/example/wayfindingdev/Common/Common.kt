package com.example.wayfindingdev.Common

import com.example.wayfindingdev.Model.School
import java.util.ArrayList

object Common {
    val VIEWTYPE_GROUP = 0
    val VIEWTYPE_SCHOOL = 1
    val RESULT_CODE = 1000
    var alphabet_available : MutableList<String> = ArrayList()

    /**
     * This function will sort list by alphabetic orther
     */
    fun sortList(list:ArrayList<School>) : ArrayList<School>{
        list.sortWith(Comparator{
            sch1,sch2 -> sch1.nom_ecole.compareTo(sch2.nom_ecole)
        })
        return list
    }
    /**
     *  This function will add alphabet to list
     */
    fun addAlphabets(list: ArrayList<School>):ArrayList<School>{
        var i = 0
        val custumList = ArrayList<School>()
        val firstMember = list[0]
        alphabet_available.add(list[0].nom_ecole[0].toString())

        custumList.add(firstMember)
        while(i<list.size-1){
            val name1 = list[i].nom_ecole[0]
            val name2=list[i+1].nom_ecole[0]

            if(name1 == name2){
                list[i].viewType = VIEWTYPE_SCHOOL
                custumList.add(list[i])
            }else{
                list[i].viewType = VIEWTYPE_SCHOOL
                custumList.add(list[i])
                var school = list[i+1]
                school.viewType = VIEWTYPE_GROUP
                alphabet_available.add(name2.toString())
                custumList.add(school)
            }
            i++
        }
        list[i].viewType = VIEWTYPE_SCHOOL
        custumList.add(list[i])
        return custumList
    }
    /**
     * This function will return index of name in list
     */
    fun findPositionWithName(name:String,list: ArrayList<School>) =  list.indexOf(list.find { x->x.nom_ecole == name })
    /**
     * THis function will generate list A->Z
     */
    fun genAlphabetsList():ArrayList<String>{
        val result = ArrayList<String>()
        for(i in 65..90)
            result.add((i.toChar()).toString())
        return result
    }
    /**
     * This function will create an simple person group with fake name data
     */
    fun genSchoolGroup():ArrayList<School>{
        val schoolList = ArrayList<School>()
        var school = School(1,"Institut Universitaire de la cote","Any","Any","Any","Any","Logbessou","120","Any","690981056","10.25","10.25","Douala",15f,"Any")

        schoolList.add(school)
        school = School(1,"Anstitut Universitaire de la cote","Any","Any","Any","Any","Logbessou","120","Any","690981056","10.25","10.25","Douala",15f,"Any")
        schoolList.add(school)
        school = School(1,"Bnstitut Universitaire de la cote","Any","Any","Any","Any","Logbessou","120","Any","690981056","10.25","10.25","Douala",15f,"Any")
        schoolList.add(school)
        school = School(1,"Bnstitut Universitaire de la cote","Any","Any","Any","Any","Logbessou","120","Any","690981056","10.25","10.25","Douala",15f,"Any")
        schoolList.add(school)
        school = School(1,"Bnstitut Universitaire de la cote","Any","Any","Any","Any","Logbessou","120","Any","690981056","10.25","10.25","Douala",15f,"Any")
        schoolList.add(school)
        school = School(1,"Bnstitut Universitaire de la cote","Any","Any","Any","Any","Logbessou","120","Any","690981056","10.25","10.25","Douala",15f,"Any")
        schoolList.add(school)
        school = School(1,"Cnstitut Universitaire de la cote","Any","Any","Any","Any","Logbessou","120","Any","690981056","10.25","10.25","Douala",15f,"Any")
        schoolList.add(school)
        school = School(1,"Dnstitut Universitaire de la cote","Any","Any","Any","Any","Logbessou","120","Any","690981056","10.25","10.25","Douala",15f,"Any")
        schoolList.add(school)
        school = School(1,"Enstitut Universitaire de la cote","Any","Any","Any","Any","Logbessou","120","Any","690981056","10.25","10.25","Douala",15f,"Any")
        schoolList.add(school)

        return schoolList
    }
}