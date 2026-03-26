package com.marcusvrocha.TavDesk.dto;

import com.marcusvrocha.TavDesk.enums.Prioridade;

public class ChamadoRequestDTO {

    private String titulo;
    private String descricao;
    private Prioridade prioridade;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }
}
