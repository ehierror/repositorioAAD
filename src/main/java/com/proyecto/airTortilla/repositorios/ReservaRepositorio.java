package com.proyecto.airTortilla.repositorios;
import com.proyecto.airTortilla.modelos.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepositorio extends JpaRepository<Reserva, Long> {

    // Al extender de JpaRepository<Usuario, Long>, ya tienes gratis dentro de este objeto:
    // - .save(usuario) -> Guarda o actualiza
    // - .findAll() -> Devuelve una lista con todos los registros
    // - .findById(id) -> Busca por ID
    // - .deleteById(id) -> Borra por ID
    // - .count() -> Cuenta cuántos registros hay

    // ¿Qué puedes poner aquí dentro si lo necesitas?
    // Solo métodos personalizados de búsqueda que inventes tú:

}