package miProyecto;

import java.util.ArrayList;
import java.util.Scanner;

public class Sistema {

    Scanner teclado;
    ArrayList<Usuario> Bdatos;

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
            System.out.println("Ingresa tu usuario ID");
            Scanner sc = new Scanner(System.in);
            Usuario usuarioLogin;
            String userId = sc.nextLine();
            System.out.println("Ingresa tu contraseña");
            usuarioLogin = buscarUser(userId, sc.nextLine());
            if (usuarioLogin != null) {
                System.out.println("Bienvenido " + usuarioLogin.getNombre());
                opcionesCuenta(usuarioLogin);
            }else{
                System.out.println("Error en login, revisa tus datos");
            }

            //userLogin no necesita un constructor
            /*if(usuarioLogin != null){
                System.out.println("Si existe");
                ImprimirMenu(usuarioLogin);
            }else{
                System.out.println("No existes");
            }*/
            //teclado.hasNextLine();
            //System.out.println("Ingresa tu contraseña");
        }
    }

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
    
    public int opcionesCuenta(Usuario usuarioLogin){
        int operacionUsuario ;
        float monto;
        System.out.println("Operaciones disponibles");
        System.out.println("1 retiro, 2 deposito, 3 consulta de saldo, 4 pago de servicios, 5 compra aire, 6 salir ");
        operacionUsuario = teclado.nextInt();
        switch(operacionUsuario){
            case 1:
                    System.out.println("Ingrese el monto a retirar");
                    monto = teclado.nextFloat();
                    usuarioLogin.getCuenta().retirar(monto);
                    opcionesCuenta(usuarioLogin);
                    break;
            case 2:
                    System.out.println("Ingrese el monto a depositar, depósito mínimo de 100.00 MXN");
                    monto = teclado.nextFloat();
                    usuarioLogin.getCuenta().abonar(monto);
                    opcionesCuenta(usuarioLogin);
                    break;
            case 3: 
                    float fondos;
                    fondos = usuarioLogin.getCuenta().consultar();
                    System.out.println("Tu saldo es: "+fondos);
                    opcionesCuenta(usuarioLogin);
                    break;
            case 6:
                    return operacionUsuario;
        }
        return operacionUsuario;
    }

}
