package br.com.letparque.indicaai.apresentacao

import br.com.letparque.indicaai.apresentacao.dto.MoradorDto
import br.com.letparque.indicaai.dominio.morador.MoradorRepository
import jakarta.validation.Valid
import jakarta.validation.constraints.Positive
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Validated
@RestController
@RequestMapping("/moradores")
class MoradorController(
    private val repository: MoradorRepository
) {

    @GetMapping
    fun getMoradores(): ResponseEntity<List<MoradorDto>> {
        val moradores = repository.findAll()
        return ResponseEntity.ok().body(MoradorDto.fromMoradores(moradores))
    }

    @GetMapping("/{id}")
    fun getMoradorById(@PathVariable @Positive id: Long): ResponseEntity<MoradorDto?> {
        val morador = repository.findById(id)
        if (morador.isPresent)
            return ResponseEntity.ok(MoradorDto.fromMorador(morador.get()))
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    fun novoMorado(@RequestBody @Valid novoMoradorDto: MoradorDto): ResponseEntity<MoradorDto> {
        val morador = novoMoradorDto.toMorador()
        val novoMorador = repository.save(morador)
        return ResponseEntity.ok(MoradorDto.fromMorador(novoMorador))
    }
}