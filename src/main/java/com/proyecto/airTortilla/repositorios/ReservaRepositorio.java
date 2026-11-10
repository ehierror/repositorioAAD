package com.proyecto.airTortilla.repositorios;
import com.proyecto.airTortilla.modelos.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepositorio extends JpaRepository<Reserva, Long> {

    

}
