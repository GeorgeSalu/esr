package com.algaworks.algafood.api.exceptionhandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
		String details = "O corpo da requisição está invalido.verifique erro de sintaxe";

		Problem problem = createProblem(status, problemType, details);

		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex,
			WebRequest request) {

		HttpStatus status = HttpStatus.NOT_FOUND;
		ProblemType problemType = ProblemType.ENTIDADE_NAO_ENCONTRADA;
		String details = ex.getMessage();

		Problem problem = createProblem(status, problemType, details);

		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<?> handleEntidadeEmUsoException(EntidadeEmUsoException ex, WebRequest request) {

		HttpStatus status = HttpStatus.CONFLICT;
		ProblemType problemType = ProblemType.ENTIDADE_EM_USO;
		String details = ex.getMessage();

		Problem problem = createProblem(status, problemType, details);

		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> handleNegocioException(NegocioException ex, WebRequest request) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		ProblemType problemType = ProblemType.ERRO_NEGOCIO;
		String details = ex.getMessage();

		Problem problem = createProblem(status, problemType, details);

		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		if (body == null) {
			Problem problema = new Problem();
			problema.setTitle(status.getReasonPhrase());
			problema.setStatus(status.value());

			body = problema;
		} else if (body instanceof String) {
			Problem problema = new Problem();
			problema.setTitle((String) body);
			problema.setStatus(status.value());

			body = problema;
		}

		return super.handleExceptionInternal(ex, body, headers, status, request);
	}

	private Problem createProblem(HttpStatus status, ProblemType problemType, String details) {
		Problem problema = new Problem();
		problema.setType(problemType.getUri());
		problema.setTitle(problemType.getTitle());
		problema.setDetail(details);
		problema.setStatus(status.value());
		return problema;
	}

}
