package br.com.letparque.indicaai.apresentacao.dto

import br.com.letparque.indicaai.dominio.indicacao.Categoria
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

data class CategoriaDto(
    val id: Long?,

    @field:NotBlank
    @field:NotEmpty
    @field:Size(max = 50, min = 5)
    val nome: String,

    @field:NotBlank
    @field:Size(max = 100, min = 10, message = "A descrição deve ter entre 10 e 50 caracteres")
    val descricao: String? = null,

    val habilitada: Int
) {
    companion object {
        fun fromCategoria(categoria: Categoria): CategoriaDto {
            return CategoriaDto(
                id = categoria.id,
                nome = categoria.nome,
                descricao = categoria.descricao,
                habilitada = categoria.habilitada
            )
        }

        fun fromCategorias(categorias: List<Categoria>): List<CategoriaDto> {
            return categorias.map { categoria -> fromCategoria(categoria) }
        }

        fun toCategoria(categoriaDto: CategoriaDto): Categoria {
            return Categoria(
                id = categoriaDto.id,
                nome = categoriaDto.nome,
                descricao = categoriaDto.descricao,
                habilitada = categoriaDto.habilitada
            )
        }
    }
}