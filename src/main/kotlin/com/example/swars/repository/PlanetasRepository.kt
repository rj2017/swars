package com.example.swars.repository

import com.example.swars.model.PlanetasModel
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface PlanetasRepository: CrudRepository<PlanetasModel, Int> {

    @Query(value = "SELECT * FROM swars.planetas WHERE nome = :name",nativeQuery = true)
    fun findByName(@Param("name") name : String) : List<PlanetasModel>

    @Query(value = "UPDATE swars.planetas SET clima = :clima, idade = :idade, nome = :nome, povoado = :povoado, terreno = :terreno WHERE id = :id",nativeQuery = true)
    @Transactional @Modifying
    fun update(@Param("id")id: Int,@Param("clima")clima : String, @Param("idade") idade : Int, @Param("nome") nome : String, @Param("povoado") povoado : Boolean,@Param("terreno") terreno : String)
}