package com.example.swars.services

import com.example.swars.model.PlanetasModel

interface PlanetasServices {
    fun create(planeta: PlanetasModel)
    fun getById(id: Int) : PlanetasModel?
    fun update(planeta: PlanetasModel)
    fun delete(id: Int)
    fun getAll() : List<PlanetasModel>
    fun filterByName(name : String) : List<PlanetasModel>
}