package br.com.letparque.indicaai.dominio.morador

import org.springframework.data.jpa.repository.JpaRepository

interface MoradorRepository: JpaRepository<Morador, Long>