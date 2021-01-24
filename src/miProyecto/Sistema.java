package miProyecto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Sistema {

    Scanner teclado;
    private Usuario usuarioActivo;

    public Sistema() {

        ImprimirNombre();
        teclado = new Scanner(System.in);
        login();
    }

    public void ImprimirNombre() {
        System.out.println("BANCO NACIONAL BANANA REPUBLIC");
    }

    public void login() {
        int eleccion;
        System.out.println("Ingresa 1 si ya cuentas con usuario, 2 para crear tu usuario");
        eleccion = teclado.nextInt();
        if (eleccion == 1) {
            int i = 0;
            do {
                Usuario usuarioLogin;
                usuarioLogin = datosLogin();
                if (usuarioLogin != null) {
                    System.out.println("Bienvenido " + usuarioLogin.getNombre());
                    opcionesCuenta(usuarioLogin);
                    //break;
                } else {
                    System.out.println("Error en login, revisa tus datos");
                }
                i++;
            } while (i < 3);
        }
        if (eleccion == 2) {
            crearUsuario();
            login();
        }else{
            System.out.println("Adios3");;
        }
    }

    /*public void validar(Usuario usuarioLogin) {
        int i;
        if (usuarioLogin != null) {
            System.out.println("Bienvenido " + usuarioLogin.getNombre());
            opcionesCuenta(usuarioLogin);
        } else {
            do {
                login();
                i = 0;
                i++;
            } while (i < 3);
        }/*public void validar(Usuario usuarioLogin) {
        int i;
        if (usuarioLogin != null) {
            System.out.println("Bienvenido " + usuarioLogin.getNombre());
            opcionesCuenta(usuarioLogin);
        } else {
            do {
                login();
                i = 0;
                i++;
            } while (i < 3);
        }
    }*/ //Se convierte en un bucle infinito
    public boolean buscarUser(String idUser, String contraseñaUser) {

        File archivo;  //manipular un archivo
        FileReader leer; //lector
        String cadena, idUsuario = "", contraseña = "", usuario = "", idCuenta = "", fondos = "";
        BufferedReader almacenamiento;
        archivo = new File("cajeroUsuario.txt");
        try {
            leer = new FileReader(archivo);
            almacenamiento = new BufferedReader(leer);
            cadena = "";
            do {
                try {
                    cadena = almacenamiento.readLine();
                    idUsuario = cadena;
                    cadena = almacenamiento.readLine();
                    usuario = cadena;
                    cadena = almacenamiento.readLine();
                    contraseña = cadena;
                    cadena = almacenamiento.readLine();
                    idCuenta = cadena;
                    cadena = almacenamiento.readLine();
                    fondos = cadena;
                    if (cadena != null && contraseña.equals(contraseñaUser) && idUser.equals(idUsuario)) {
                        Usuario userBusqueda = new Usuario(usuario, contraseña, idUsuario);
                        float fondosCuenta = Float.valueOf(fondos);
                        userBusqueda.agregarCuenta(new Cuenta(idCuenta, fondosCuenta));
                        usuarioActivo = userBusqueda;
                        leer.close();
                        return true;
                    } else {
                        System.out.println("Contraseña no encontrada" + "Usuario: " + usuario + "\nid: " + idUsuario + " \ncontraseña: " + contraseña);
                    }
                } catch (IOException ex) {
                    System.out.println("error encontrar" + ex);

                    Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
                }
            } while (cadena != null || (idUser.equals(usuario) && contraseña.equals(contraseñaUser)));
            try {
                almacenamiento.close();
                leer.close();
            } catch (IOException ex) {
                Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
            }
            /*if (usuario.equals(idUser) && contraseña.equals(contraseña)) {
                Usuario user = new Usuario(usuario, contraseña, idUsuario);
                float fondosCuenta = Float.valueOf(fondos);
                user.agregarCuenta(new Cuenta(idCuenta, fondosCuenta));
                Bdatos.add(user);
                System.out.println("Buscar user sí sirve" + user);
                return user;
            }*/
            return true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Usuario no existe");
        return false;
    }

    public Usuario datosLogin() {
        String contraseñaUser, idUser;
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresa tu id");
        idUser = sc.nextLine();
        System.out.println("Ingresa tu contraseña");
        contraseñaUser = sc.nextLine();
        if (buscarUser(idUser, contraseñaUser)) {
            return usuarioActivo;
        }
        return null;
    }

    public int opcionesCuenta(Usuario usuarioLogin) {
        int operacionUsuario;
        System.out.println("Operaciones disponibles");
        System.out.println("1 retiro, 2 deposito, 3 consulta de saldo, 4 pago de servicios, 5 compra aire, 6 salir ");
        operacionUsuario = teclado.nextInt();
        InterfazUsuario interfaz = new InterfazUsuario(usuarioLogin, usuarioLogin.getCuenta());
        switch (operacionUsuario) {
            case 1:

                if (interfaz.reitro()) {
                    System.out.println(usuarioLogin);
                    actualizarUsuario(usuarioLogin);
                }
                opcionesCuenta(usuarioLogin);
                break;
            case 2:
                interfaz.abono();
                opcionesCuenta(usuarioLogin);
                break;
            case 3:
                interfaz.consulta();
                opcionesCuenta(usuarioLogin);
                break;
            case 4:
                interfaz.pagoServicios();
                opcionesCuenta(usuarioLogin);
                break;
            case 6:
                return operacionUsuario;
        }
        return operacionUsuario;
    }

    public Usuario actualizarUsuario(Usuario user) {
        File archivoTemporal;
        archivoTemporal = new File("temp.txt");
        File archivoLectura;
        archivoLectura = new File("cajeroUsuario.txt");
        try {
            BufferedWriter escribir = new BufferedWriter(new FileWriter(archivoTemporal));
            BufferedReader lectura = new BufferedReader(new FileReader(archivoLectura));
            String cadena;
            while ((cadena = lectura.readLine()) != null) { //comparamos cadena, que alberga lectura de linea, con null
                String borrarEspacios = cadena.trim();
                if (borrarEspacios.equals(user.getId())) {
                    escribir.write(user.getId() + System.getProperty("line.separator"));
                    escribir.write(user.getNombre() + System.getProperty("line.separator"));
                    escribir.write(user.getContraseña() + System.getProperty("line.separator"));
                    escribir.write(user.getCuenta().getId() + System.getProperty("line.separator"));
                    escribir.write(user.getCuenta().consultar() + System.getProperty("line.separator"));
                    for (int i = 0; i < 4; i++) {
                        cadena = lectura.readLine();
                    }
                    continue; //sale de la iteracion. No ejecuta nada continuo
                }
                escribir.write(cadena + System.getProperty("line.separator"));
            }

            lectura.close();
            escribir.close();

            System.out.println("Buffer cerrado");

            if (archivoLectura.exists()) {
                //Boolean resultados = archivoTemporal.renameTo(new File());
                System.out.println("El archivo si existe");

                Files.move(Paths.get(archivoTemporal.getAbsolutePath()), Paths.get(archivoLectura.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);
                //Files.move(archivoTemporal.toPath(), archivoLectura.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("ok");
            } else {
                System.out.println("No archivo lectura");
            }

        } catch (IOException x) {
            System.out.println("Error: " + x);
        }

        return null;
    }

    public void crearUsuario() {
        Scanner sc = new Scanner(System.in);
        String nombre;
        Random rnd = new Random();
        int idAleatorio = (int) (rnd.nextDouble() * 1000 + 100);
        System.out.println("Ingresa tu nombre");
        nombre = sc.nextLine();
        if ((!nombre.isEmpty()) && nombre.length() >= 4) {

            String contraseña1;
            String contraseña2;
            System.out.println("Ingresa nueva contraseña");
            contraseña1 = sc.nextLine();
            System.out.println("Confirma tu contraseña");
            contraseña2 = sc.nextLine();

            System.out.println("Deposita a tu nueva cuenta. Depósito mínimo de 50.00 MXN");
            float montoApertura = sc.nextInt();
            System.out.println(montoApertura);
            if (contraseña1.equals(contraseña2) && contraseña1.length() >= 4 && montoApertura >= 50) {
                System.out.println("Proceso completado");
                Usuario usuario = new Usuario(nombre, String.valueOf(idAleatorio));
                usuario.agregarCuenta(new Cuenta("333", montoApertura));
                usuario.restableceContraseña(contraseña2);
                BaseDatos(usuario, contraseña2);
                //System.out.println(usuario);
                usuarioActivo = usuario;
                System.out.println("Tu id es: " + usuario.getId());

            } else {
                System.out.println("Error revisa contraseña y deposito");
            }
        } else {
            System.out.println("Error al ingresar usuario, mínimo 4 caracteres");
            login();
        }

    }

    public void BaseDatos(Usuario usuario, String contraseña2) {
        File archivo; //manipula el archivo
        FileWriter escribir; // escribir en el archivo
        PrintWriter linea; // 
        archivo = new File("cajeroUsuario.txt");
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
                escribir = new FileWriter(archivo, true);
                linea = new PrintWriter(escribir);
                //escribir en el archivo
                linea.println(usuario.getId());
                linea.println(usuario.getNombre());
                linea.println(contraseña2);
                linea.println(usuario.getCuenta().getId());
                linea.println(usuario.getCuenta().consultar());
                //System.out.println(usuario.getId());
                //System.out.println(contraseña2);
                //linea.println();
                linea.close();
                escribir.close();
            } catch (Exception e) {
            }
        } else {
            try {
                escribir = new FileWriter(archivo, true);
                linea = new PrintWriter(escribir);
                //escribir en el archivo
                linea.println(usuario.getId());
                linea.println(usuario.getNombre());
                linea.println(contraseña2);
                linea.println(usuario.getCuenta().getId());
                linea.println(usuario.getCuenta().consultar());
                //linea.println(email);
                linea.close();
                escribir.close();
            } catch (Exception e) {
            }
        }
    }

}
