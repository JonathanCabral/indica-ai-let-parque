package br.com.letparque.indicaai.dominio.morador

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size

@Entity
@Table(name = "morador")
class Morador(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,

    @Size(max = 50, min = 5)
    var nome: String,

    @Email
    @Size(max = 50, min = 10)
    var email: String,

    @NotEmpty
    @NotBlank
    @Size(max = 11, min = 20)
    var telefone: String? = null,

    @NotEmpty
    @Positive
    @Column(unique = true)
    var apartamento: Long,

    var tipo: String, //TODO MUDAR PARA ENUM TipoMoradorEnum

    //TODO pensar em como colocar mais de um morador no mesmo apartamento
    //ex: no apartamento 905, mora o Jonathan e a Stefania.
)