package br.com.letparque.indicaai.apresentacao.dto.response

import br.com.letparque.indicaai.dominio.apartamento.SituacaoApartamento
import jakarta.validation.Validation
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource

class ApartamentoResponseTest {
    @ParameterizedTest(name = "Apartamento {0} no andar {1}")
    @MethodSource("apartamentosValidos")
    fun `deve aceitar todos os apartamentos existentes`(
        numero: Long,
        andar: Int,
    ) {
        val response =
            ApartamentoResponse(
                numero = numero,
                andar = andar,
                situacao = SituacaoApartamento.VAZIO,
            )

        assertEquals(
            numero,
            response.numero,
            "Deveria preservar o número $numero do apartamento",
        )

        assertEquals(
            andar,
            response.andar,
            "Deveria preservar o andar $andar do apartamento $numero",
        )

        assertEquals(
            SituacaoApartamento.VAZIO,
            response.situacao,
            "Deveria preservar a situação VAZIO do apartamento $numero",
        )
    }

    @ParameterizedTest
    @ValueSource(longs = [-1L, 0L, 100L, 1505L, 1510L, 1601L])
    fun `deve rejeitar numero fora do intervalo permitido`(numero: Long) {
        assertThrows<IllegalArgumentException>(
            "Deveria rejeitar o número $numero por estar fora do intervalo de 101 a 1504",
        ) {
            ApartamentoResponse(
                numero = numero,
                andar = 1,
                situacao = SituacaoApartamento.VAZIO,
            )
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, 0, 16])
    fun `deve rejeitar andar fora do intervalo permitido`(andar: Int) {
        assertThrows<IllegalArgumentException>(
            "Deveria rejeitar o andar $andar por estar fora do intervalo de 1 a 15",
        ) {
            ApartamentoResponse(
                numero = 101L,
                andar = andar,
                situacao = SituacaoApartamento.VAZIO,
            )
        }
    }

    @ParameterizedTest
    @CsvSource(
        "101, 2",
        "110, 10",
        "905, 8",
        "1410, 13",
        "1504, 14",
    )
    fun `deve rejeitar andar diferente do indicado pelo numero`(
        numero: Long,
        andar: Int,
    ) {
        assertThrows<IllegalArgumentException>(
            "Deveria rejeitar o apartamento $numero no andar $andar, " +
                "pois seu número indica o andar ${numero / 100L}",
        ) {
            ApartamentoResponse(
                numero = numero,
                andar = andar,
                situacao = SituacaoApartamento.VAZIO,
            )
        }
    }

    @ParameterizedTest
    @CsvSource(
        "111, 1",
        "199, 1",
        "200, 2",
        "211, 2",
        "1400, 14",
        "1411, 14",
    )
    fun `deve rejeitar unidade inexistente no andar`(
        numero: Long,
        andar: Int,
    ) {
        assertThrows<IllegalArgumentException>(
            "Deveria rejeitar o apartamento $numero, " +
                "pois a unidade ${numero % 100L} não existe no andar $andar",
        ) {
            ApartamentoResponse(
                numero = numero,
                andar = andar,
                situacao = SituacaoApartamento.VAZIO,
            )
        }
    }

    @Test
    fun `deve validar apartamento com Bean Validation sem violacoes`() {
        val response =
            ApartamentoResponse(
                numero = 1504L,
                andar = 15,
                situacao = SituacaoApartamento.VAZIO,
            )

        Validation.buildDefaultValidatorFactory().use { factory ->
            val violacoes = factory.validator.validate(response)

            assertTrue(
                violacoes.isEmpty(),
                "O apartamento 1504 no andar 15 deveria ser válido. " +
                    "Violações encontradas: $violacoes",
            )
        }
    }

    companion object {
        @JvmStatic
        fun apartamentosValidos(): List<Array<Any>> {
            val apartamentos = mutableListOf<Array<Any>>()

            for (andar in 1..14) {
                for (unidade in 1..10) {
                    apartamentos.add(
                        arrayOf<Any>(andar * 100L + unidade, andar),
                    )
                }
            }

            for (unidade in 1..4) {
                apartamentos.add(
                    arrayOf<Any>(1500L + unidade, 15),
                )
            }

            return apartamentos
        }
    }
}
