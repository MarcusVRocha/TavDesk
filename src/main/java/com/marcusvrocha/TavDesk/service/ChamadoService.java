package com.marcusvrocha.TavDesk.service;

import com.marcusvrocha.TavDesk.dto.ChamadoRequestDTO;
import com.marcusvrocha.TavDesk.dto.ChamadoResponseDTO;
import com.marcusvrocha.TavDesk.enums.StatusChamado;
import com.marcusvrocha.TavDesk.exception.ChamadoNaoEncontradoException;
import com.marcusvrocha.TavDesk.exception.TransicaoStatusInvalidaException;
import com.marcusvrocha.TavDesk.exception.UsuarioNaoEncontradoException;
import com.marcusvrocha.TavDesk.model.Chamado;
import com.marcusvrocha.TavDesk.model.Usuarios;
import com.marcusvrocha.TavDesk.repository.ChamadoRepository;
import com.marcusvrocha.TavDesk.repository.UsuariosRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChamadoService {

    private final ChamadoRepository chamadoRepository;
    private final UsuariosRepository usuariosRepository;

    public ChamadoService(ChamadoRepository chamadoRepository, UsuariosRepository usuariosRepository) {
        this.chamadoRepository = chamadoRepository;
        this.usuariosRepository = usuariosRepository;
    }

    public ChamadoResponseDTO criarChamado(ChamadoRequestDTO dto) {
        Chamado chamado = new Chamado();

        chamado.setTitulo(dto.getTitulo());
        chamado.setDescricao(dto.getDescricao());
        chamado.setPrioridade(dto.getPrioridade());
        chamado.setStatus(StatusChamado.ABERTO);
        chamado.setDataCriacao(LocalDateTime.now());

        if (dto.getUsuarioId() != null) {
            Usuarios usuario = usuariosRepository.findById(dto.getUsuarioId())
                    .orElseThrow(() -> new UsuarioNaoEncontradoException(dto.getUsuarioId()));

            chamado.setUsuario(usuario);
        }

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
        validarTransicaoStatus(chamado.getStatus(), novoStatus);

        chamado.setStatus(novoStatus);
        Chamado atualizado = chamadoRepository.save(chamado);
        return toResponseDTO(atualizado);
    }

    private void validarTransicaoStatus(StatusChamado statusAtual, StatusChamado novoStatus) {
        if (statusAtual == novoStatus) {
            throw new TransicaoStatusInvalidaException(statusAtual, novoStatus);
        }

        boolean transicaoValida =
                (statusAtual == StatusChamado.ABERTO && novoStatus == StatusChamado.EM_ANDAMENTO) ||
                        (statusAtual == StatusChamado.ABERTO && novoStatus == StatusChamado.FECHADO) ||
                        (statusAtual == StatusChamado.EM_ANDAMENTO && novoStatus == StatusChamado.FECHADO);

        if (!transicaoValida) {
            throw new TransicaoStatusInvalidaException(statusAtual, novoStatus);
        }
    }

    private ChamadoResponseDTO toResponseDTO(Chamado chamado) {
        ChamadoResponseDTO dto = new ChamadoResponseDTO();

        dto.setId(chamado.getId());
        dto.setTitulo(chamado.getTitulo());
        dto.setDescricao(chamado.getDescricao());
        dto.setStatus(chamado.getStatus());
        dto.setPrioridade(chamado.getPrioridade());
        dto.setDataCriacao(chamado.getDataCriacao());

        if (chamado.getUsuario() != null) {
            dto.setUsuarioId(chamado.getUsuario().getId());
        }

        return dto;
    }
}
