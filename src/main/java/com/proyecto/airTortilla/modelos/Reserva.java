package com.proyecto.airTortilla.modelos;

import jakarta.persistence.*;

@Entity
@Table(name = "Reservas")
public class Reserva {
    //ponemos en private para que solo se puedan acceder por gets
    @Id //este código asigna un primary key
    private Long numVuelo; //se usa long pq los id en sql han de ser LONG
    @Column(nullable = false)
    private String origen;
    @Column(nullable = false)
    private String destino;
    @Column(nullable = false)
    private String nombrePasajero;

    public Reserva(){}

    public Reserva(String origen, String destino, Long numVuelo, String nombrePasajero) {
        this.origen = origen;
        this.destino = destino;
        this.numVuelo = numVuelo;
        this.nombrePasajero = nombrePasajero;

    }

    public Long getNumVuelo() {
        return numVuelo;
    }
    public void setNumVuelo(Long numVuelo){this.numVuelo=numVuelo;}
    public String getOrigen() {
        return origen;
    }
    public void setOrigen(String origen){this.origen=origen;}
    public String getDestino() {
        return destino;
    }
    public void setDestino(String destino){this.destino=destino;}
    public String getNombrePasajero() {
        return nombrePasajero;
    }
    public void setNombrePasajero(String nombre){this.nombrePasajero=nombre;}

    @Override
    public String toString(){
        return this.numVuelo+","+this.destino+","+this.origen+","+this.nombrePasajero;
    }
}
