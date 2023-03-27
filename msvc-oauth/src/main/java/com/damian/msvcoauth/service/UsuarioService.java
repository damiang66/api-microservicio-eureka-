package com.damian.msvcoauth.service;

import com.damian.msvcoauth.client.UsuarioFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import   com.damian.comun.msvcusuarioscomun.entidad.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class UsuarioService implements UserDetailsService,UsuarioServiceInfo {
    private Logger logger = LoggerFactory.getLogger(UsuarioService.class);
    @Autowired
    private UsuarioFeignClient client;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {


      Usuario usuario = client.findByUsername(username);
      if (usuario==null){
          logger.error("Error en el login no existe es usuario \"+ username+ \" en el sistema");
          throw new UsernameNotFoundException("Error en el login no existe es usuario "+ username+ " en el sistema");
      }
        List<GrantedAuthority>authorities = usuario.getRoles().stream()
                .map(role-> new SimpleGrantedAuthority(role.getNombre()))
                .peek(auth-> logger.info("Role: "+ auth.getAuthority()))
                .collect(Collectors.toList());
      logger.error("usuario autenticado: "+ username);
        return new User(usuario.getUsername(),usuario.getPassword(),usuario.getEnabled(),true,
               true,true,authorities );
        }catch (Exception e){
            logger.error("Error en el login no existe es usuario \"+ username+ \" en el sistema");
            throw new UsernameNotFoundException("Error en el login no existe es usuario "+ username+ " en el sistema");

        }
    }

    @Override
    public Usuario findByUsername(String username) {
        return client.findByUsername(username);
    }

    @Override
    public Usuario update(Usuario usuario, Long id) {
        return client.update(usuario,id);
    }
}
