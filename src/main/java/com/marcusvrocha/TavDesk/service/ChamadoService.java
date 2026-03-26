package com.marcusvrocha.TavDesk.service;

import com.marcusvrocha.TavDesk.dto.ChamadoRequestDTO;
import com.marcusvrocha.TavDesk.dto.ChamadoResponseDTO;
import com.marcusvrocha.TavDesk.enums.StatusChamado;
import com.marcusvrocha.TavDesk.exception.ChamadoNaoEncontradoException;
import com.marcusvrocha.TavDesk.model.Chamado;
import com.marcusvrocha.TavDesk.repository.ChamadoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChamadoService {

    private final ChamadoRepository chamadoRepository;

    public ChamadoService(ChamadoRepository chamadoRepository) {
        this.chamadoRepository = chamadoRepository;
    }

    public ChamadoResponseDTO criarChamado(ChamadoRequestDTO dto) {
        Chamado chamado = new Chamado();

        chamado.setTitulo(dto.getTitulo());
        chamado.setDescricao(dto.getDescricao());
        chamado.setPrioridade(dto.getPrioridade());
        chamado.setStatus(StatusChamado.ABERTO);
        chamado.setDataCriacao(LocalDateTime.now());

        Chamado salvo = chamadoRepository.save(chamado);

        return toResponseDTO(salvo);
    }

    public List<ChamadoResponseDTO> listarChamados() {
        return chamadoRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    private Chamado buscarEntidadePorId(Long id) {
        return chamadoRepository.findById(id)
                .orElseThrow(() -> new ChamadoNaoEncontradoException(id));
    }

    public ChamadoResponseDTO listarPorId(Long id) {
        Chamado chamado = buscarEntidadePorId(id);
        return toResponseDTO(chamado);
    }

    public void deletarChamado(Long id) {
        Chamado chamado = buscarEntidadePorId(id);
        chamadoRepository.delete(chamado);
    }

    public ChamadoResponseDTO atualizarStatus(Long id, StatusChamado novoStatus) {
        Chamado chamado = buscarEntidadePorId(id);
        chamado.setStatus(novoStatus);

        Chamado atualizado = chamadoRepository.save(chamado);
        return toResponseDTO(atualizado);
    }

    private ChamadoResponseDTO toResponseDTO(Chamado chamado) {
        ChamadoResponseDTO dto = new ChamadoResponseDTO();

        dto.setId(chamado.getId());
        dto.setTitulo(chamado.getTitulo());
        dto.setDescricao(chamado.getDescricao());
        dto.setStatus(chamado.getStatus());
        dto.setPrioridade(chamado.getPrioridade());
        dto.setDataCriacao(chamado.getDataCriacao());

        return dto;
    }
}
