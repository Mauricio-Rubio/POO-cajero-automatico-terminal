package miProyecto;

import com.sun.corba.se.impl.naming.cosnaming.InterOperableNamingImpl;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Sistema {

    Scanner teclado;
    private ArrayList<Usuario> Bdatos;

    public Sistema() {

        ImprimirMenu();
        Usuario user = new Usuario("Mauricio", "q1", "321");
        Usuario PUERKI = new Usuario("PUERKI", "Q2", "322");
        Cuenta cuenticPUERKI = new Cuenta("31256", 100);
        Cuenta cuentica = new Cuenta("654321", 100);
        user.agregarCuenta(cuentica);
        PUERKI.agregarCuenta(cuenticPUERKI);
        Bdatos = new ArrayList();
        Bdatos.add(user);
        Bdatos.add(PUERKI);
        teclado = new Scanner(System.in);
        /*for (int i = 0; i < 1; i++) {
            System.out.println(Bdatos.get(i).getId());
        }
        System.out.println(Bdatos); si imprimimos un arreglo que contenga objetos 
        en la posicion [n], el resultado será el espacio en memoria que este ocupa, por eso ocupamos metodos
         */
        login();
    }

    public void ImprimirMenu() {
        System.out.println("BANCO NACIONAL BANANA REPUBLIC");
    }

    public void login() {
        int eleccion;
        System.out.println("Ingresa 1 si ya cuentas con usuario, 2 para crear tu usuario");
        eleccion = teclado.nextInt();
        if (eleccion == 1) {
            int i = 0;
            do {
                System.out.println("Ingresa tu usuario ID");
                Scanner sc = new Scanner(System.in);
                Usuario usuarioLogin;
                String userId = sc.nextLine();
                System.out.println("Ingresa tu contraseña");
                usuarioLogin = buscarUser(userId, sc.nextLine());
                if (usuarioLogin != null) {
                    System.out.println("Bienvenido " + usuarioLogin.getNombre());
                    opcionesCuenta(usuarioLogin);
                    break;
                } else {
                    System.out.println("Error en login, revisa tus datos");
                }
                i++;
            } while (i < 3);
        }
        if (eleccion == 2) {
            crearUsuario();
            login();
        }

        //userLogin no necesita un constructor
        /*if(usuarioLogin != null){
                System.out.println("Si existe");
                ImprimirMenu(usuarioLogin);
            }else{
                System.out.println("No existes");
            }*/ //teclado.hasNextLine();
        //System.out.println("Ingresa tu contraseña");
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
        }
    }*/ //Se convierte en un bucle infinito
    public Usuario buscarUser(String id, String contraseña) {
        //Trae un usuario de la BDD, lo busca con el Id y lo regresa a quien lo llame
        Usuario userBaseDatos;
        for (int i = 0; i < Bdatos.size(); i++) {
            userBaseDatos = Bdatos.get(i);
            if (userBaseDatos.getId().equals(id)) {
                System.out.println("Usuario encontrado");
                if (userBaseDatos.validarContraseña(contraseña)) {
                    System.out.println("Contraseña aceptada");
                    return userBaseDatos;
                }
            }
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
                interfaz.reitro();
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
            Usuario usuario = new Usuario(nombre, String.valueOf(idAleatorio));
            float montoApertura = sc.nextInt();
            usuario.agregarCuenta(new Cuenta("333", montoApertura));
            System.out.println(montoApertura);
            if (contraseña1.equals(contraseña2) && contraseña1.length() >= 4 && montoApertura >= 50) {
                System.out.println("Proceso completado");
                usuario.restableceContraseña(contraseña2);
                BaseDatos(usuario, usuario.getCuenta(), contraseña2);
                System.out.println(usuario);

                Bdatos.add(usuario);

            } else {
                System.out.println("Error revisa contraseña y deposito");
            }
        } else {
            System.out.println("Error al ingresar usuario");
            login();
        }

    }

    public void BaseDatos(Usuario usuario, Cuenta cuenta, String contraseña2) {
        File archivo; //manipula el archivo
        FileWriter escribir; // escribir en el archivo
        PrintWriter linea; // 
        String nombre;
        Scanner sc = new Scanner(System.in);
        archivo = new File("cajeroUsuario.txt");
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
                escribir = new FileWriter(archivo, true);
                linea = new PrintWriter(escribir);
                //escribir en el archivo
                linea.println(usuario.getId());
                linea.println(contraseña2);
                System.out.println(usuario.getId());
                System.out.println(contraseña2);
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
                linea.println(contraseña2);
                //linea.println(email);
                linea.close();
                escribir.close();
            } catch (Exception e) {
            }
        }
    }

}
