package com.damian.msvcoauth.service;
import com.damian.comun.msvcusuarioscomun.entidad.Usuario;


public interface UsuarioServiceInfo {
    public Usuario findByUsername(String username);
    public Usuario update( Usuario usuario,  Long id);
}
