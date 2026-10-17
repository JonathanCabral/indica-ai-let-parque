package br.com.letparque.indicaai.infra.apartamento

import br.com.letparque.indicaai.dominio.apartamento.Apartamento
import br.com.letparque.indicaai.dominio.apartamento.IApartamentoDms
import org.springframework.stereotype.Service

@Service
class ApartamentoDms(
    private val repository: ApartamentoRepository,
) : IApartamentoDms {
    override fun lista(): Set<Apartamento> = repository.findAll().toSet()

    override fun lista(numero: Long): Apartamento =
        repository
            .findById(numero)
            .orElseThrow { IllegalArgumentException("Apartamento informado não encontraado") }
}
