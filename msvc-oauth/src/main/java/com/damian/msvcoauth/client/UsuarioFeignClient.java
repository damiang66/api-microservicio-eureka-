package com.damian.msvcoauth.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import com.damian.comun.msvcusuarioscomun.entidad.Usuario;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("msvc-usuarios")
public interface UsuarioFeignClient {
    @GetMapping("usuarios/search/buscar-username")
    public Usuario findByUsername(@RequestParam("nombre")String username);
}
