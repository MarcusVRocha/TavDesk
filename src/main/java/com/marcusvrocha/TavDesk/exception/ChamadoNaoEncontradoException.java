package com.marcusvrocha.TavDesk.exception;

public class ChamadoNaoEncontradoException extends RuntimeException{
    public ChamadoNaoEncontradoException(Long id){
        super("Não foi encontrado o chamado com id: " + id + ".");
    }
}
