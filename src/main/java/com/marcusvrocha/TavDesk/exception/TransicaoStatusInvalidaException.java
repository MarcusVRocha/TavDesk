package com.marcusvrocha.TavDesk.exception;

import com.marcusvrocha.TavDesk.enums.StatusChamado;

public class TransicaoStatusInvalidaException extends RuntimeException {

    public TransicaoStatusInvalidaException(StatusChamado atual, StatusChamado novo) {
        super("Transição de status inválida: " + atual + " -> " + novo);
    }

}
