package br.com.letparque.indicaai.infra.apartamento

import br.com.letparque.indicaai.dominio.apartamento.Apartamento
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ApartamentoRepository : JpaRepository<Apartamento, Long>
