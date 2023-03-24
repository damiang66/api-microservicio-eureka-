package com.damian.comun.msvcusuarios.repositorio;



import com.damian.comun.msvcusuarioscomun.entidad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path ="usuarios" )
public interface usuarioRepositoy extends JpaRepository<Usuario,Long> {
    @RestResource(path = "buscar-username")
    public Usuario findByUsername(@Param("nombre") String username);
}
