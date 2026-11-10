package com.proyecto.airTortilla;

import com.proyecto.airTortilla.modelos.Logs;
import com.proyecto.airTortilla.modelos.Reserva;
import com.proyecto.airTortilla.modelos.Usuario; // (Ajusta esto según el nombre de tu paquete de modelos)
import com.proyecto.airTortilla.repositorios.LogsRepositorio;
import com.proyecto.airTortilla.repositorios.ReservaRepositorio;
import com.proyecto.airTortilla.repositorios.UsuarioRepositorio;

import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class cargadorDatos implements CommandLineRunner {

    private Usuario usuarioActual;

    private final UsuarioRepositorio escritorUsuario;
    private final ReservaRepositorio escritorReserva;
    private final LogsRepositorio escritorLogs;
    private Scanner lector = new Scanner(System.in);


    public cargadorDatos(UsuarioRepositorio escritorUsuario, ReservaRepositorio escritorReserva, LogsRepositorio escritorlogs) {
        //los escritores (los repositorios) son los encargados de escribir en las tablas (modelos)
        this.escritorUsuario = escritorUsuario;
        this.escritorLogs = escritorlogs;
        this.escritorReserva = escritorReserva;

    }

    @Override
    public void run(String... args) throws Exception {
        Optional <Usuario> rootPotencial = escritorUsuario.findById(1L);
        if(rootPotencial.isEmpty()){
            //si no hay usuario se crea el root (siempre es id 1)
            Usuario root = new Usuario("root","root","admin");
            escritorUsuario.save(root);
        }
        inicio();

    }

    public void vaciado() {
        //despeja la BDD de datos basura para evitar duplicados en las pruebas
        escritorLogs.deleteAll();
        escritorUsuario.deleteAll();
        escritorReserva.deleteAll();
        Usuario root = new Usuario("root","root","admin");
        escritorUsuario.save(root); //creamos siempre un primer usuario
    }

    public void inicio() {
        boolean continuar = true;
        boolean sesionIniciada = false;
        int opcion;


        while (continuar) {
            if (!sesionIniciada) {
                System.out.println("1.Iniciar sesión");
                System.out.println("0.Salir de la aplicación");
                opcion = lector.nextInt();
                switch (opcion) {
                    case 1:
                        sesionIniciada = inicioSesion();
                        break;
                    default: //caso 0 y default
                        continuar = false;
                        break;
                }
            }
            if (sesionIniciada) {
                System.out.println("1: Cargar los datos");
                System.out.println("2: Crear usuario");
                System.out.println("3: Cerrar sesión");
                System.out.println();
                System.out.println("Introduzca la opcion deseada:");
                opcion = lector.nextInt();
                switch (opcion) {
                    case 1:
                        System.out.println("Introduzca el nombre del archivo (debe de estar en la carpeta data)");
                        String archivo = lector.next();
                        lectura(archivo);
                        break;
                    case 2:
                        crearUsuario();
                        break;
                    case 3:
                        sesionIniciada = false;
                        break;

                }
            }
        }
    }


    public boolean inicioSesion() {
        System.out.println("Introduzca el usuario");
        String user = lector.next();
        System.out.println("Introduzca la contraseña");
        String pass = lector.next();
        Optional<Usuario> usuarioPotencial = escritorUsuario.findUsuarioByNombreUsuarioAndPassword(user, pass);
        //verificamos si optional tiene un objeto o realmente es nulo
        if (usuarioPotencial.isPresent()) {
            usuarioActual = usuarioPotencial.get(); //recuperamos la clase de la caja optional
            System.out.println("Bienvenido " + usuarioActual.getNombreUsuario());
            return true;
        } else {
            System.out.println("Usuario no existente");
            return false;
        }

    }

    public void crearUsuario() {
        System.out.println("Introduzca el nombre del usuario");
        String nombre = lector.next();
        System.out.println("Introduzca la contraseña");
        String pass = lector.next();
        System.out.println("Especifique el tipo de usuario (usuario/admin)");
        String rol = lector.next();
        Usuario user = new Usuario(nombre, pass, rol);
        escritorUsuario.save(user);
        System.out.println("Usuario creado correctamente!");
        registroAccion(usuarioActual,"crear usuario",user);
    }

    public void registroAccion(Usuario user, String accion,Usuario afectado){
        Logs registro = new Logs(user,accion,afectado);
        escritorLogs.save(registro);
        System.out.println("Id usuario creador: "+user.getIdUsuario());
        System.out.println("Nombre: "+user.getNombreUsuario());

    }

    public void lectura(String archivo) {
        try (BufferedReader br = new BufferedReader(new FileReader("data/" + archivo))) {
            String cabecera = br.readLine(); //saltamos la primera linea (la cabecera)
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                String nombre = datos[3];
                String destino = datos[1];
                int numVuelo = Integer.parseInt(datos[0]);
                String origen = datos[2];
                Reserva res = new Reserva(origen, destino, (long) numVuelo, nombre);
                escritorReserva.save(res);
                //se acaba la creacion de clase y extraccion de datos
                //aqui empieza los ajustes para el nombre del archivo
                LocalDate fecha = LocalDate.now();
                int dia = fecha.getDayOfMonth();
                int mes = fecha.getMonthValue();
                int anio = fecha.getYear();


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}