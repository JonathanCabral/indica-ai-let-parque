package br.com.letparque.indicaai.apresentacao

import br.com.letparque.indicaai.apresentacao.dto.CategoriaDto
import br.com.letparque.indicaai.apresentacao.exception.RecursoNaoEncontradoException
import br.com.letparque.indicaai.dominio.indicacao.CategoriaRepository
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.util.Assert.isTrue
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@Validated
@RestController
@RequestMapping("/categorias")
class CategoriaController(
    private val repository: CategoriaRepository
) {

    @GetMapping
    fun getCategorias(): ResponseEntity<List<CategoriaDto>> {
        val categoriasAtivas = repository.findAllByHabilitada();
        return ResponseEntity.ok().body(CategoriaDto.fromCategorias(categoriasAtivas))
    }

    @GetMapping("/{id}")
    fun getCategoriaById(@PathVariable(name = "id") id: Long): ResponseEntity<CategoriaDto?> {
        val categoria = repository.findById(id).orElseThrow {
            throw RecursoNaoEncontradoException("Categoria com ID '$id' não encontrada")
        }
        return ResponseEntity.ok(CategoriaDto.fromCategoria(categoria))
    }

    @PostMapping
    fun novoCategoria(@RequestBody @Valid categoriaDto: CategoriaDto): ResponseEntity<CategoriaDto> {
        isTrue(categoriaDto.id == null, "O ID do DTO deve ser nulo para criar uma nova categoria")
        val categoria = CategoriaDto.toCategoria(categoriaDto)
        val novaCategoria = repository.save(categoria)
        return ResponseEntity.ok(CategoriaDto.fromCategoria(novaCategoria))
    }

    @PostMapping("/atualiza/id/{id}")
    fun atualizarCategoria(@RequestBody @Valid categoriaDto: CategoriaDto, @PathVariable(name = "id") id: Long): ResponseEntity<CategoriaDto> {
        isTrue(categoriaDto.id == id, "O ID do DTO deve ser igual ao ID do parâmetro da URL")
        val categoria = CategoriaDto.toCategoria(categoriaDto)
        val categoriaAtualizada = repository.save(categoria)

        return ResponseEntity.accepted().body(CategoriaDto.fromCategoria(categoriaAtualizada))
    }
}