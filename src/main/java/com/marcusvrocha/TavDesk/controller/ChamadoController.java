package com.marcusvrocha.TavDesk.controller;

import com.marcusvrocha.TavDesk.dto.AtualizarStatusChamadoDTO;
import com.marcusvrocha.TavDesk.dto.ChamadoRequestDTO;
import com.marcusvrocha.TavDesk.dto.ChamadoResponseDTO;
import com.marcusvrocha.TavDesk.service.ChamadoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chamados")
public class ChamadoController {

    private final ChamadoService chamadoService;

    public ChamadoController(ChamadoService chamadoService) {
        this.chamadoService = chamadoService;
    }

    @PostMapping
    public ChamadoResponseDTO criarChamado(@RequestBody @Valid ChamadoRequestDTO dto) {
        return chamadoService.criarChamado(dto);
    }

    @GetMapping
    public List<ChamadoResponseDTO> listarChamados() {
        return chamadoService.listarChamados();
    }

    @GetMapping("/{id}")
    public ChamadoResponseDTO listarPorId(@PathVariable Long id) {
        return chamadoService.listarPorId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarChamado(@PathVariable Long id) {
        chamadoService.deletarChamado(id);
    }

    @PatchMapping("/{id}/status")
    public ChamadoResponseDTO atualizarStatus(
            @PathVariable Long id,
            @RequestBody @Valid AtualizarStatusChamadoDTO dto) {

        return chamadoService.atualizarStatus(id, dto.getStatus());
    }

}
