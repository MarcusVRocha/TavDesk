package com.marcusvrocha.TavDesk.service;

import com.marcusvrocha.TavDesk.dto.UsuariosRequestDTO;
import com.marcusvrocha.TavDesk.dto.UsuariosResponseDTO;
import com.marcusvrocha.TavDesk.exception.EmailJaCadastradoException;
import com.marcusvrocha.TavDesk.exception.UsuarioNaoEncontradoException;
import com.marcusvrocha.TavDesk.model.Usuarios;
import com.marcusvrocha.TavDesk.repository.UsuariosRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuariosService {

    private final UsuariosRepository usuariosRepository;

    public UsuariosService(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    public UsuariosResponseDTO criarUsuario(UsuariosRequestDTO dto) {
        if (usuariosRepository.existsByEmail(dto.getEmail())) {
            throw new EmailJaCadastradoException(dto.getEmail());
        }

        Usuarios usuario = new Usuarios();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());

        Usuarios salvo = usuariosRepository.save(usuario);
        return toResponseDTO(salvo);
    }

    public List<UsuariosResponseDTO> listarUsuarios() {
        return usuariosRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public UsuariosResponseDTO listarPorId(Long id) {
        Usuarios usuario = buscarEntidadePorId(id);
        return toResponseDTO(usuario);
    }

    public void deletarUsuario(Long id) {
        Usuarios usuario = buscarEntidadePorId(id);
        usuariosRepository.delete(usuario);
    }

    private Usuarios buscarEntidadePorId(Long id) {
        return usuariosRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(id));
    }

    private UsuariosResponseDTO toResponseDTO(Usuarios usuario) {
        UsuariosResponseDTO dto = new UsuariosResponseDTO();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        return dto;
    }
}
