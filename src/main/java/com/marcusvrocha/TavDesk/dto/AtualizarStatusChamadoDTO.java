package com.marcusvrocha.TavDesk.dto;

import com.marcusvrocha.TavDesk.enums.StatusChamado;
import jakarta.validation.constraints.NotNull;

public class AtualizarStatusChamadoDTO {
    @NotNull(message = "O status é obrigatório")
    private StatusChamado status;

    public StatusChamado getStatus() {
        return status;
    }

    public void setStatus(StatusChamado status) {
        this.status = status;
    }
}
