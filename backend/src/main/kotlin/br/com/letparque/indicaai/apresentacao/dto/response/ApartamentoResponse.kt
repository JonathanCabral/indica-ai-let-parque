package br.com.letparque.indicaai.apresentacao.dto.response

import br.com.letparque.indicaai.dominio.apartamento.SituacaoApartamento
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull

@Schema(description = "Informações de um apartamento do condomínio")
data class ApartamentoResponse(
    @field:Min(
        value = 101,
        message = "O número do apartamento deve ser maior ou igual a 101",
    )
    @field:Max(
        value = 1504,
        message = "O número do apartamento deve ser menor ou igual a 1504",
    )
    @field:Schema(
        description = """
        Número único do apartamento, formado pelo andar seguido
        dos dois dígitos da unidade.

        Regras de numeração:
        - Andares 1 a 14: unidades de 01 a 10.
        - Andar 15: unidades de 01 a 04.
        - O térreo não possui apartamentos.
        - O andar informado deve corresponder ao prefixo do número.

        Exemplos válidos: 101, 110, 905, 1410 e 1504.
        Exemplos inválidos: 100, 111 e 1505.
    """,
        example = "1504",
        minimum = "101",
        maximum = "1504",
    )
    val numero: Long,
    @field:Min(
        value = 1,
        message = "O andar deve ser maior ou igual a 1",
    )
    @field:Max(
        value = 15,
        message = "O andar deve ser menor ou igual a 15",
    )
    @field:Schema(
        description = "Andar do apartamento. O térreo não possui apartamentos.",
        example = "15",
        minimum = "1",
        maximum = "15",
    )
    val andar: Int,
    @field:NotNull(message = "A situação do apartamento é obrigatória")
    @field:Schema(
        description = "Situação atual do apartamento",
        example = "VAZIO",
    )
    val situacao: SituacaoApartamento,
) {
    init {
        require(andar in 1..15) {
            "O andar deve estar entre 1 e 15."
        }

        val andarEsperado = numero / 100L

        require(andar.toLong() == andarEsperado) {
            "O apartamento $numero deve estar no andar $andarEsperado, " +
                "mas foi informado o andar $andar."
        }

        val unidade = numero % 100L

        val ultimaUnidade = if (andar == 15) 4L else 10L

        require(unidade in 1L..ultimaUnidade) {
            "A unidade do apartamento deve estar entre 01 e " +
                "${ultimaUnidade.toString().padStart(2, '0')} no andar $andar."
        }
    }
}
