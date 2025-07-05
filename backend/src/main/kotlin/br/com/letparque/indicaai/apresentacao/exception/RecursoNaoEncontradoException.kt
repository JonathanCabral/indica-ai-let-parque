package br.com.letparque.indicaai.apresentacao.exception

import java.lang.Exception

class RecursoNaoEncontradoException(message: String, exception: Exception? = null) : RuntimeException(message, exception)
