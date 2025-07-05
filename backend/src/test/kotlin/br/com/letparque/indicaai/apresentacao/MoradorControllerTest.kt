package br.com.letparque.indicaai.apresentacao

import org.junit.jupiter.api.Assertions.*
import org.springframework.context.annotation.PropertySource

class MoradorControllerTest {


    fun `recupera todos os moradores`() {

        //TODO so no começo, depois nao tem necessidade de testr findall.
    }

    fun `retorna morador valido ao buscar por ID existente`() {

        assertAll(
            { assertEquals(200, 200, ) },
        )
    }

    fun `retorna 404 quando morador com ID informado nao for encontrado`() {

    }

    fun `retorna 400 quando ID informado for invalido - negativo`() {

    }

    fun `cadastra um novo morador com sucesso`() {

    }

    @PropertySource()
    fun `deve retornar 400 ao tentar cadastrar morador com dados invalidos`() {
        //todo tentar fazer com property, um for por cada usuario. Receber os usuarios pre setados via property source
        // nome em branco
        // nome com menos de 5 char
        // nome com mais de 50 char
        // email em branco
        // email invalido,
        // telefone null, telefone branco, telefone com menos de 10char
        // apartamento null
        // apartamento negativo ou zero
    }


    fun `retorna 422 quando morador ja existir`() {

    }


    fun `retorna 422 quando ja houver morador vinculado ao apartamento informado`() {

    }

}