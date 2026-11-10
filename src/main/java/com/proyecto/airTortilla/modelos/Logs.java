package com.proyecto.airTortilla.modelos;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="Logs")
public class Logs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLog;
    @ManyToOne
    @JoinColumn(name = "idUsuario") //foreign key
    private Usuario user;
    @ManyToOne
    @JoinColumn(name = "idUsuarioAfectado")
    private Usuario userAfectado; //aun que las claves foraneas sean tipo objeto, sql las traduce por sus primary keys
    @Column(nullable = false)
    private String accion;
    @Column(nullable = false)
    LocalDateTime fecha = LocalDateTime.now();
public Logs(){

}
public Logs(Usuario user, String accion,Usuario afectado){
    this.user=user;
    this.accion=accion;
    this.userAfectado=afectado;
    }

    public void setAfectado(Usuario afectado) {
        this.userAfectado = afectado;
    }

    public Usuario getAfectado() {
        return userAfectado;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getAccion() {
        return accion;
    }

    public Long getIdLog() {
        return idLog;
    }

    public void setIdLog(Long idLog) {
        this.idLog = idLog;
    }

    public Usuario getUser() {
        return this.user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha=fecha;}
}
