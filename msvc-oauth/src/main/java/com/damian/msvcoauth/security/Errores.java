package com.damian.msvcoauth.security;

import com.damian.msvcoauth.service.UsuarioServiceInfo;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import com.damian.comun.msvcusuarioscomun.entidad.Usuario;

@Component
public class Errores implements AuthenticationEventPublisher {
    Logger logger = LoggerFactory.getLogger(Errores.class);
    @Autowired
    private UsuarioServiceInfo serviceInfo;
    @Override
    public void publishAuthenticationSuccess(Authentication authentication) {
        if(authentication.getDetails() instanceof WebAuthenticationDetails){
            return;
        }

        UserDetails user= (UserDetails) authentication.getPrincipal();
        Usuario usuario = serviceInfo.findByUsername(authentication.getName());
        System.out.println("success Login: " + user.getUsername());
        logger.info("success Login: " + user.getUsername());
        if (usuario.getIntentos() != null && usuario.getIntentos()>0){
            usuario.setIntentos(0);
            serviceInfo.update(usuario, usuario.getId());
        }
    }

    @Override
    public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
    logger.error("Error en loggin"+exception.getMessage() );
    try {
        Usuario usuario = serviceInfo.findByUsername(authentication.getName());
        if (usuario.getIntentos()== null){
            usuario.setIntentos(0);
        }

        usuario.setIntentos(usuario.getIntentos()+1);
        logger.info("usuario intentos " + usuario.getIntentos());
        if (usuario.getIntentos()>=3){
            logger.error("usuario deshabilitado por tener 3 intentos");
            usuario.setEnabled(false);
        }
        serviceInfo.update(usuario, usuario.getId());
    }catch (FeignException e){
        logger.error("el usuario no exite en la base de datos");

    }

    }
}
