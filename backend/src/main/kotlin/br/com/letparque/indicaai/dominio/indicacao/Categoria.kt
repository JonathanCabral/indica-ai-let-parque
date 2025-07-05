package br.com.letparque.indicaai.dominio.indicacao

import jakarta.persistence.*
import jakarta.validation.constraints.Size

@Entity
@Table(name = "categoria")
class Categoria(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,

    @Size(max = 50, min = 5)
    @Column(unique = true, nullable = false)
    val nome: String,

    @Size(max = 50, min = 10)
    @Column(nullable = true)
    val descricao: String? = null,

    val habilitada: Int // 1 = habilitada, 0 = desabilitada
)