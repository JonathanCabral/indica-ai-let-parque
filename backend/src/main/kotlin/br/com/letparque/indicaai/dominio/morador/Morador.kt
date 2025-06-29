package br.com.letparque.indicaai.dominio.morador

class Morador(
    var id: Long?,
    var nome: String,
    var email: String,
    var telefone: String? = null,
    var apartamento: Long,
    var tipo: TipoMoradorEnum,
)