package com.marcusvrocha.TavDesk.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}
