package com.marcusvrocha.TavDesk.controller;

import com.marcusvrocha.TavDesk.dto.UsuariosRequestDTO;
import com.marcusvrocha.TavDesk.dto.UsuariosResponseDTO;
import com.marcusvrocha.TavDesk.service.UsuariosService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuariosService usuariosService;

    public UsuarioController(UsuariosService usuariosService) {
        this.usuariosService = usuariosService;
    }

    @PostMapping
    public UsuariosResponseDTO criarUsuario(@RequestBody @Valid UsuariosRequestDTO dto) {
        return usuariosService.criarUsuario(dto);
    }

    @GetMapping
    public List<UsuariosResponseDTO> listarUsuarios() {
        return usuariosService.listarUsuarios();
    }

    @GetMapping("/{id}")
    public UsuariosResponseDTO listarPorId(@PathVariable Long id) {
        return usuariosService.listarPorId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarUsuario(@PathVariable Long id) {
        usuariosService.deletarUsuario(id);
    }

}
