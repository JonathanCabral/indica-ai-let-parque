package br.com.letparque.indicaai.dominio.indicacao

import java.math.BigDecimal
import java.time.LocalDate

class Indicacao (
    var id: Long?,
    var profissional: Profissional,
    var categoria: Categoria,
    var idMorador: Long,
    //Aqui o morador deve fazer uma descrição do trabalho que foi feito, se ela gostou, pontos positivos, pontos negativos
    // pontos de alerta... Tudo que ele achar interessante deixar registrado para os proximos moradores.
    var descricaoTrabalho: String,
    //Esse campo pode ser no estilo de estrelas, então a nota pode ser de 1 a 5 (1 não indicaria, 5 indicaria muito)
    var notaDoMorador: Int,
    var indico: Int, //0 -> Não indico; 1 -> indico;
    // Deixar
    var valorAproximado: BigDecimal? = null, //valor aproximado pago pelo morador, mas não obrigatório.

    var dataServico: LocalDate, //data que o serviço foi realizado
    var dataCadastro: LocalDate = LocalDate.now(), //data de cadastro da indicacao.

    //TODO add o campo para o historico de avaliacoes. Quem devem ser feitas por outro morador, cada avaliacao extra add +1 no contador.
    // mas avaliar se é melhor abordagem é só o morador colocar um joinha, adicionando +1 na indicaçao, ou se ele por indicar, seja obrigado a fazer uma avaliação completa tbm.
) {
    /** TODO PENSAR NA SOLUÇÃO
     *  NA PARTE DE INDICO OU NÃO INDICO, QUEM PODE ALTERAR?
     *  SOMENTE O MORADOR QUE FEZ A INDICAÇÃO?
     *  E SE UM MORADOR QUE UTILIZOU ESSA INDICAÇÃO TEVE PROBLEMAS E NÃO GOSTOU DO TRABALHO E DESEJA NÃO INDICAR?
     */

    // limitar a tipos de arquivos ex: png, jpg, pdf. Maximo 5mb
    var anexos: List<String> = mutableListOf()
}

