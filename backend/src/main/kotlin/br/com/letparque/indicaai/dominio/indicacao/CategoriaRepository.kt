package br.com.letparque.indicaai.dominio.indicacao

import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface CategoriaRepository: JpaRepository<Categoria, Long> {
    fun findAllByHabilitada(habilitada: Int = 1): List<Categoria>
    fun findByNome(nome: String): Optional<Categoria>
}