package br.com.letparque.indicaai.apresentacao

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController(value = "/apartamentos")
class ApartamentosController {
    // TODO AQUI PODEMOS TER FILTRO POR SITUACAO
    fun lista(): ResponseEntity<Any> = ResponseEntity.ok().build()

    fun lista(id: Long): ResponseEntity<Any> = ResponseEntity.ok().build()
}
