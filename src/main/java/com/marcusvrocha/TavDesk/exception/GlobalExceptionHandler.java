package com.marcusvrocha.TavDesk.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ChamadoNaoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> handleChamadoNaoEncontradoException(ChamadoNaoEncontradoException ex){

        Map<String, Object> erro = new HashMap<>();
        erro.put("Data/Hora", LocalDateTime.now());
        erro.put("Status:", 404);
        erro.put("Erro!", "Não encontrado.");
        erro.put("Mensagem:", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){

        Map<String, String> erros = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                erros.put(error.getField(), error.getDefaultMessage())
        );

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("Data/Hora", LocalDateTime.now());
        resposta.put("Status:", 400);
        resposta.put("Erro!", "Bad Request.");
        resposta.put("Erros:", erros);

        return ResponseEntity.badRequest().body(resposta);
    }

    @ExceptionHandler(TransicaoStatusInvalidaException.class)
    public ResponseEntity<Map<String, Object>> handleTransicaoStatusInvalidaException(
            TransicaoStatusInvalidaException ex) {

        Map<String, Object> erro = new HashMap<>();
        erro.put("Data/Hora", LocalDateTime.now());
        erro.put("Status:", 400);
        erro.put("Erro!", "Bad Request.");
        erro.put("Mensagem:", ex.getMessage());

        return ResponseEntity.badRequest().body(erro);
    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> handleUsuarioNaoEncontradoException(UsuarioNaoEncontradoException ex){

        Map<String, Object> erro = new HashMap<>();
        erro.put("Data/Hora", LocalDateTime.now());
        erro.put("Status:", 404);
        erro.put("Erro!", "Não encontrado.");
        erro.put("Mensagem:", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(EmailJaCadastradoException.class)
    public ResponseEntity<Map<String, Object>> handleEmailJaCadastradoException(EmailJaCadastradoException ex){

        Map<String, Object> erro = new HashMap<>();
        erro.put("Data/Hora", LocalDateTime.now());
        erro.put("Status:", 400);
        erro.put("Erro!", "Bad Request.");
        erro.put("Mensagem:", ex.getMessage());

        return ResponseEntity.badRequest().body(erro);
    }
}
