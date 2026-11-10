package com.proyecto.airTortilla.modelos;

import jakarta.persistence.*;
import static com.proyecto.airTortilla.modelos.tipoUsuario.USER;
import static com.proyecto.airTortilla.modelos.tipoUsuario.ADMIN;


@Entity
@Table(name="Usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //equivale a auto incrementoo
    private Long idUsuario;
    @Column(nullable = false)
    private String nombreUsuario;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING) //para que en la columna enum guarde el nombre y no el id del enum
    @Column(nullable=false)
    private tipoUsuario rol;
public Usuario(){}
    public Usuario(String nombreUsuario, String password,String tipoUsuario){
this.nombreUsuario=nombreUsuario;
this.password=password;
switch(tipoUsuario.toLowerCase()){
    case "usuario":
        this.rol= USER;
        break;
    case "admin":
        this.rol=ADMIN;
        break;
    default:
        this.rol=USER;
}
    }
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
    public Long getIdUsuario() {
        return idUsuario;
    }
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    public String getNombreUsuario() {
        return nombreUsuario;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
    public void setRol(tipoUsuario rol) {
        this.rol = rol;
    }
    public tipoUsuario getRol(){
    return this.rol;
    }
}
