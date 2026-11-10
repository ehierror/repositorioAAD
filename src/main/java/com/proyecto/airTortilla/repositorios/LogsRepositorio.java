package com.proyecto.airTortilla.repositorios;
import com.proyecto.airTortilla.modelos.Logs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogsRepositorio extends JpaRepository<Logs, Long> {



}