package br.com.letparque.indicaai.apresentacao.handler

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class ExceptionHandler {

    data class ErrorResponse(
        val status: Int,
        val message: String,
        val error: String,
        val details: List<DetailErrorResponse>? = null
    )

    data class DetailErrorResponse(
        val field: String,
        val message: String
    )

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidacaoException(
        ex: MethodArgumentNotValidException,
        request: WebRequest
    ): ResponseEntity<ErrorResponse> {
        val erros = ex.bindingResult
            .fieldErrors
            .map { fieldError ->
                DetailErrorResponse(
                    field = fieldError.field,
                    message = fieldError.defaultMessage ?: "Campo inválido"
                )
            }

        val erroResposta = ErrorResponse(
            status = HttpStatus.BAD_REQUEST.value(),
            error = "Erro de validação",
            message = "Um ou mais campos estão inválidos.",
            details = erros
        )

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroResposta)
    }


}