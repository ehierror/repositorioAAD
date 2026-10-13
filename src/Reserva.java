public class Reserva {
    //ponemos en private para que solo se puedan acceder por gets
    private int numVuelo;
    private String origen;
    private String destino;
    private String nombrePasajero;
    public Reserva(String origen, String destino, int numVuelo, String nombrePasajero) {
        this.origen = origen;
        this.destino = destino;
        this.numVuelo = numVuelo;
        this.nombrePasajero = nombrePasajero;

    }

    public int getNumVuelo() {
        return numVuelo;
    }
    public String getOrigen() {
        return origen;
    }
    public String getDestino() {
        return destino;
    }
    public String getNombrePasajero() {
        return nombrePasajero;
    }

    @Override
    public String toString(){
        return this.numVuelo+","+this.destino+","+this.origen+","+this.nombrePasajero;
    }
}
