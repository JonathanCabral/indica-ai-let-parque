package br.com.letparque.indicaai.dominio.apartamento

interface IApartamentoDms {
    fun lista(): Set<Apartamento>

    fun lista(numero: Long): Apartamento
}
