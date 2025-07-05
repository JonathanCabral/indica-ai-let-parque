package br.com.letparque.indicaai.apresentacao.dto

import br.com.letparque.indicaai.dominio.morador.Morador
import br.com.letparque.indicaai.dominio.morador.TipoMoradorEnum
import jakarta.validation.constraints.*

class MoradorDto(
    var id: Long?,
    var email: String,
    @NotBlank
    @Size(min = 5, max = 50)
    var nome: String,

    @Email
    @NotEmpty
    @NotBlank
    @Size(min = 10, max = 50)
    var telefone: String? = null,

    @NotNull
    @Positive
    var apartamento: Long,

    @NotBlank
    @NotNull
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
                tipo = morador.tipo.name
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
            tipo = TipoMoradorEnum.valueOf(this.tipo)
        );
    }
}