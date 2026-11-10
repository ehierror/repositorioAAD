package com.proyecto.airTortilla.repositorios;
import com.proyecto.airTortilla.modelos.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

//esta instruccion le dice a SB que encuentre el usuario por nombre, SB usa el nombre findUsuario...como consulta sql (find es el get, By es el filtro...)
Optional<Usuario> findUsuarioByNombreUsuarioAndPassword(String nombre, String pass);
//usamos el optional pq puede ser nulo o no
    //explicar a mayor profundidad el pq de optional en el readme
}