package com.example.swars.model

import javax.persistence.*

@Entity
@Table(name = "planetas")
class PlanetasModel {
    @Id
    @GeneratedValue
    val id : Int = 0
    val nome : String = ""
    val clima : String = ""
    val terreno : String = ""
    val idade : Int = 0
    val povoado : Boolean = false
}