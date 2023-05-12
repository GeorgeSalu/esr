package com.algaworks.algafood.api.exceptionhandler;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
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
import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		Throwable rootCause = ExceptionUtils.getRootCause(ex);
		
		if(rootCause instanceof InvalidFormatException) {
			return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
		} else if (rootCause instanceof PropertyBindingException) {
			return handlePropertyBindigException((PropertyBindingException) rootCause, headers, status, request);
		}

		ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
		String details = "O corpo da requisição está invalido.verifique erro de sintaxe";

		Problem problem = createProblem(status, problemType, details);

		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	private ResponseEntity<Object> handlePropertyBindigException(PropertyBindingException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		String path = joinPath(ex.getPath());
		
		ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
		String detail = String.format("A propriedade '%s' não existe. Corrija ou remova essa propriedade e tente novamente.", path);
		
		Problem problem = createProblem(status, problemType, detail);
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}

	private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String path = joinPath(ex.getPath());
		
		ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
		String details = String.format("A propriedade '%s' recebeu o valor '%s',  "
				+ "que é de um tipo invalido. Corrija e informe um valor compativel com o tipo %s.", 
				path,ex.getValue(),ex.getTargetType().getSimpleName());

		Problem problem = createProblem(status, problemType, details);
		
		return handleExceptionInternal(ex, problem, headers, status, request);
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
	
	private String joinPath(List<Reference> references) {
		return references.stream()
					.map(ref -> ref.getFieldName())
					.collect(Collectors.joining("."));
	}

}
