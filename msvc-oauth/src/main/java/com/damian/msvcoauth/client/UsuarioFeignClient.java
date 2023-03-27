package com.damian.msvcoauth.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import com.damian.comun.msvcusuarioscomun.entidad.Usuario;

@FeignClient("msvc-usuarios")
public interface UsuarioFeignClient {
    @GetMapping("usuarios/search/buscar-username")
    public Usuario findByUsername(@RequestParam("nombre")String username);
    @PutMapping("usuarios/{id}")
    public Usuario update(@RequestBody Usuario usuario, @PathVariable Long id);
}
