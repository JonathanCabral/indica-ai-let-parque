package br.com.letparque.indicaai.apresentacao.dto

import br.com.letparque.indicaai.dominio.morador.Morador
import jakarta.validation.constraints.*

class MoradorDto(
    var id: Long?,

    @field:NotBlank
    @field:Size(min = 5, max = 50)
    var nome: String,

    @field:Email
    @field:NotEmpty
    @field:NotBlank
    var email: String,

    @field:NotEmpty
    @field:NotBlank
    @field:Size(min = 10, max = 50)
    var telefone: String? = null,

    @field:NotNull
    @field:Positive(message = "O apartamento deve ser um número positivo")
    var apartamento: Long,

    @field:NotBlank
    @field:NotNull
    var tipo: String,
) {

    companion object {
        fun fromMorador(morador: Morador): MoradorDto {
            return MoradorDto(
                id= morador.id,
                nome = morador.nome,
                email = morador.email,
                telefone = morador.telefone,
                apartamento = morador.apartamento,
                tipo = morador.tipo
            );
        }

        fun fromMoradores(moradores: List<Morador>): List<MoradorDto> {
            return moradores.map { morador -> fromMorador(morador) }
        }
    }

    fun toMorador(): Morador {
        return Morador(
            id = this.id,
            nome = this.nome,
            email = this.email,
            telefone = this.telefone,
            apartamento = this.apartamento,
            tipo = this.tipo
        );
    }
}