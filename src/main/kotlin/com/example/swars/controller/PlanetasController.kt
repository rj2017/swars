package com.example.swars.controller

import com.example.swars.model.ErroMessage
import com.example.swars.model.PlanetasModel
import com.example.swars.model.RespostaJson
import com.example.swars.services.PlanetasServices
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.websocket.server.PathParam

@RestController
@RequestMapping(value = ["/planetas"])
class PlanetasController {

    @Autowired
    lateinit var planetasService : PlanetasServices

    @PostMapping
    fun create(@RequestBody planeta : PlanetasModel) : ResponseEntity<RespostaJson>{
        this.planetasService.create(planeta)
        val respostaJson = RespostaJson("OK", Date())
        return ResponseEntity(respostaJson, HttpStatus.CREATED)
    }

    @GetMapping
    fun getAll(): ResponseEntity<List<PlanetasModel>>{
        val list = this.planetasService.getAll()
        var status = HttpStatus.OK
        if (list.size == 0){
            status = HttpStatus.NOT_FOUND
        }
        return ResponseEntity(list, status)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Int) : ResponseEntity<Any>{
        val planeta = this.planetasService.getById(id)
        val s =""
        if (planeta != null){
            return  ResponseEntity(planeta, HttpStatus.OK)
        }else{
            return ResponseEntity(ErroMessage("Planeta nao localizado","promocao ${id} não localizado"),HttpStatus.NOT_FOUND)
        }

    }

    @PutMapping
    fun update(@RequestBody planeta: PlanetasModel) : ResponseEntity<ErroMessage>{
        val planetas =this.planetasService.getById(planeta.id)
        if ( planetas!= null){
            this.planetasService.update(planeta)
            return ResponseEntity(ErroMessage("OK","Planeta atualizado com sucesso!"),HttpStatus.ACCEPTED)
        }else{
            return ResponseEntity(ErroMessage("Error","Não localizado o planeta pelo Id informado!"),HttpStatus.NOT_FOUND)
        }

    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id : Int) : ResponseEntity<Unit>{
        var status = HttpStatus.NOT_FOUND
        if (this.planetasService.getById(id) != null){
            status = HttpStatus.OK
            this.planetasService.delete(id)
        }

        return ResponseEntity(Unit,status)
    }

    @GetMapping("/getbyname")
    fun serchbyNome(@RequestParam(required = true) name : String) : ResponseEntity<List<PlanetasModel>>{
        val planetas = this.planetasService.filterByName(name)
        var status =HttpStatus.NOT_FOUND
        if (planetas.size > 0){
            status = HttpStatus.OK
        }
        return ResponseEntity(planetas,status)
    }
}