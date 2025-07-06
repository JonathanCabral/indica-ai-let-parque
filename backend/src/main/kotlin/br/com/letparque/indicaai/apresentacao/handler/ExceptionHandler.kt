package br.com.letparque.indicaai.apresentacao.handler

import br.com.letparque.indicaai.apresentacao.exception.RecursoNaoEncontradoException
import jakarta.validation.ConstraintViolationException
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler


@ControllerAdvice
class ExceptionHandler {

    data class ErroResposta(
        val status: Int,
        val erro: String,
        val mensagem: String,
        val detalhes: List<DetalheErro>? = null
    )

    data class DetalheErro(
        val campo: String,
        val mensagem: String
    )

    @ExceptionHandler(RecursoNaoEncontradoException::class)
    fun handleRecursoNaoEncontradoException(ex: RecursoNaoEncontradoException): ResponseEntity<ErroResposta> {
        val resposta = ErroResposta(
            status = HttpStatus.NOT_FOUND.value(),
            erro = "Recurso não encontrado",
            mensagem = ex.message ?: "O recurso solicitado não foi encontrado"
        )
        return ResponseEntity(resposta, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidacaoException(ex: MethodArgumentNotValidException): ResponseEntity<ErroResposta> {
        val detalhes = ex.bindingResult.allErrors.map { erro ->
            val campo = (erro as? FieldError)?.field ?: erro.objectName
            DetalheErro(campo, erro.defaultMessage ?: "Erro de validação")
        }
        val resposta = ErroResposta(
            status = HttpStatus.BAD_REQUEST.value(),
            erro = "Erro de validação",
            mensagem = "Um ou mais campos estão inválidos",
            detalhes = detalhes
        )
        return ResponseEntity(resposta, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(ex: IllegalArgumentException): ResponseEntity<ErroResposta> {
        val resposta = ErroResposta(
            status = HttpStatus.BAD_REQUEST.value(),
            erro = "Argumento inválido",
            mensagem = ex.message ?: "O argumento fornecido é inválido"
        )
        return ResponseEntity(resposta, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolationException(ex: ConstraintViolationException): ResponseEntity<ErroResposta> {
        val detalhes = ex.constraintViolations.map {
            DetalheErro(it.propertyPath.toString(), it.message)
        }

        val resposta = ErroResposta(
            status = HttpStatus.BAD_REQUEST.value(),
            erro = "Parâmetros inválidos",
            mensagem = "Um ou mais parâmetros estão inválidos",
            detalhes = detalhes
        )
        return ResponseEntity(resposta, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleJsonInvalido(ex: HttpMessageNotReadableException): ResponseEntity<ErroResposta> {
        val resposta = ErroResposta(
            status = HttpStatus.BAD_REQUEST.value(),
            erro = "Requisição malformada",
            mensagem = "O corpo da requisição está malformado ou inválido",
            detalhes = listOf(
                DetalheErro("JSON", ex.cause?.message ?: "Erro ao ler o JSON")
            )
        )
        return ResponseEntity(resposta, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(DataIntegrityViolationException::class)
    fun handleDataIntegrityViolationException(ex: DataIntegrityViolationException): ResponseEntity<ErroResposta> {
        val resposta = ErroResposta(
            status = HttpStatus.CONFLICT.value(),
            erro = "Violação de integridade de dados",
            mensagem = "Operação violou restrições de integridade do banco de dados"
        )
        return ResponseEntity(resposta, HttpStatus.UNPROCESSABLE_ENTITY)
    }


    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception): ResponseEntity<ErroResposta> {
        val resposta = ErroResposta(
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            erro = "Erro interno",
            mensagem = ex.message ?: "Ocorreu um erro inesperado"
        )
        return ResponseEntity(resposta, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(AccessDeniedException::class)
    fun handleAcessoNegado(ex: AccessDeniedException): ResponseEntity<ErroResposta> {
        val resposta = ErroResposta(
            status = HttpStatus.FORBIDDEN.value(),
            erro = "Acesso negado",
            mensagem = "Você não tem permissão para acessar este recurso."
        )
        return ResponseEntity(resposta, HttpStatus.FORBIDDEN)
    }
}