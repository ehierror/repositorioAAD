import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        inicio();
    }

    public static void inicio() {
        System.out.println("1: Cargar los datos");
        System.out.println("2: Consultar los datos mediante pais (codigo ISO3) de forma lineal");
        System.out.println("3: Consultar los datos mediante pais (codigo ISO3) de forma total");
        System.out.println();
        System.out.println("Introduzca la opcion deseada:");
        Scanner lector = new Scanner(System.in);
        int opcion = lector.nextInt();
        switch (opcion) {
            case 1:
                System.out.println("Introduzca el nombre del archivo (debe de estar en la carpeta data)");
                String archivo = lector.next();
                lectura(archivo);
                break;
            case 2:
                System.out.println("Introduzca el codigo ISO del pais que desea leer");
                String codigo = lector.next();
                String seleccion = archivoLeer(codigo);
                String linea;
                try (BufferedReader rd = new BufferedReader(new FileReader("data/exports/"+seleccion))) {
                    while ((linea = rd.readLine()) != null) {
                        System.out.println(linea);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                System.out.println("Introduzca el codigo ISO del pais que desea leer");
                String code = lector.next();
                String select = archivoLeer(code);
                try (BufferedReader rd = new BufferedReader(new FileReader("data/exports/"+select))) {
                    System.out.println(rd.lines().collect(Collectors.joining("\n")));
                    //se liminan los saltos de linea y se van uniendo cada string
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
public static String archivoLeer(String codigo){
    Path directorioDatos = Paths.get("data/exports");
    String[] datitos = directorioDatos.toFile().list();
    String seleccion = null;
    for (int i = 0; i < datitos.length; i++) {
        if (directorioDatos.toFile().list()[i].toString().contains(codigo)) {
            seleccion = directorioDatos.toFile().list()[i].toString();
            break;
        }
    }
    return seleccion;
}
    public static void lectura(String archivo) {
        try (BufferedReader br = new BufferedReader(new FileReader("data/"+archivo))) {
            String cabecera = br.readLine(); //saltamos la primera linea (la cabecera)
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                String nombre = datos[3];
                String destino = datos[1];
                int numVuelo = Integer.parseInt(datos[0]);
                String origen = datos[2];
                Reserva res = new Reserva(origen, destino, numVuelo, nombre);
                //se acaba la creacion de clase y extraccion de datos
                //aqui empieza los ajustes para el nombre del archivo
                LocalDate fecha = LocalDate.now();
                int dia = fecha.getDayOfMonth();
                int mes = fecha.getMonthValue();
                int anio = fecha.getYear();
                File archivoPorPais = new File("data/exports/AirTortilla_" + pais_ISO(res.getDestino()) + "_" + anio + "_" + mes + "_" + dia + ".csv");
                boolean archivoNuevo = !archivoPorPais.exists();
                try (FileWriter escritor = new FileWriter(archivoPorPais, true)) {
                    if (archivoNuevo) {
                        escritor.write(cabecera);
                        escritor.write("\n");
                    }
                    escritor.write(res.toString());
                    escritor.write("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String pais_ISO(String pais) {

        Locale[] locales = Locale.getAvailableLocales();
        //Se crea un array con todos los paises en la libreria Locale
        for (int i = 0; i < locales.length; i++) {
            Locale locale = locales[i];
            if (locale.getDisplayCountry(Locale.ENGLISH).equalsIgnoreCase(pais)) {
                return locale.getCountry();
            }
        }
        return null;
    }
}