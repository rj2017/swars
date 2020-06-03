package com.example.swars.services.impl

import com.example.swars.model.PlanetasModel
import com.example.swars.repository.PlanetasRepository
import com.example.swars.services.PlanetasServices
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Component

@Component
class PlanetasServicesImpl(val planetaRepository: PlanetasRepository) : PlanetasServices {

    @CacheEvict("planetas", allEntries = true)
    override fun create(planeta: PlanetasModel) {
        this.planetaRepository.save(planeta)
    }

    @Cacheable("planetas")
    override fun getById(id: Int): PlanetasModel? {
        return this.planetaRepository.findById(id).orElse(null)
    }


    @CacheEvict("planetas", allEntries = true)
    override fun update(planeta: PlanetasModel) {
        this.planetaRepository.update(planeta.id,planeta.clima,planeta.idade,planeta.nome,planeta.povoado,planeta.terreno)

    }

    @CacheEvict("planetas", allEntries = true)
    override fun delete(id: Int) {
        this.planetaRepository.deleteById(id)
    }

    @Cacheable("planetas")
    override fun getAll(): List<PlanetasModel> {
        return this.planetaRepository.findAll().toList()
    }

    @Cacheable("planetas")
    override fun filterByName(name: String): List<PlanetasModel> {
        return this.planetaRepository.findByName(name)
    }
}