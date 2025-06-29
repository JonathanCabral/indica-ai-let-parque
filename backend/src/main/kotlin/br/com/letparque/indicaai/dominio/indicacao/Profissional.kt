package br.com.letparque.indicaai.dominio.indicacao

class Profissional(
    var id: Long?,
    var nome: String,
    var nomeEmpresa: String?,
    var telefone: String,
    var celular: String,
    var descricao: String,
    var ativo: Int = 0, //0 -> inativo; 1 -> ativo
    var instagram: String? = null,
    var site: String? = null,
)