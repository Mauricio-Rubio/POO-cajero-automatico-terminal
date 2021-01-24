package miProyecto;

import java.util.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class InterfazUsuario {

    private Usuario usuario;
    private Cuenta cuenta;
    private Scanner teclado;

    public InterfazUsuario(Usuario usuario, Cuenta cuenta) {
        this.usuario = usuario;
        this.cuenta = cuenta;
        teclado = new Scanner(System.in);
    }

    public void consulta() {
        System.out.println("Tu saldo es: " + cuenta.consultar());
    }

    public float abono() {
        float monto;
        System.out.println("Ingresa el monto a ingresar, depósito mínimo de 50.00 MXN");
        monto = teclado.nextFloat();
        if (monto >= 50) {
            cuenta.abonar(monto);
            System.out.println("Se abonó: " + monto + " saldo restante: " + cuenta.consultar());
            return monto;
        } else {
            System.out.println("Depostito mínimo de 50.00 MXN");
            return 0;
        }
    }

    public boolean retiro() {
        float monto;
        System.out.println("Ingresa el monto a retirar");
        monto = teclado.nextFloat();
        if (monto >= 50 && monto <= cuenta.consultar()) {

            cuenta.retirar(monto);
            System.out.println("Se retiró: " + monto + "MXN saldo restante: " + cuenta.consultar() + "MXN");
            return true;
        } else {
            System.out.println("Retiro minimo: 50.00 MXN");
            return false;
        }
    }

    public boolean pagoServicios() {
        String servicio;
        float monto;
        System.out.println("Servicio a pagar");
        servicio = teclado.nextLine();
        System.out.println("Monto a cobrar");
        monto = teclado.nextFloat();
        if (monto <= cuenta.consultar()) {
            cuenta.retirar(monto);
            System.out.println("Servicio: " + servicio);
            System.out.println("Importe: " + monto);
            System.out.println("Saldo restante: " + cuenta.consultar());
            return true;
        } else {
            System.out.println("Saldo insuficiente");
            return false;
        }
    }

    public boolean tiempoAire() {
        Scanner sc = new Scanner (System.in);
        String compañia;
        int telefono, telefono2;
        int eleccion;
        System.out.println("ingresa la compañia");
        compañia = sc.nextLine();
        System.out.println("Recargas posibles");
        System.out.println("1- $20, 2- $50, 3- $80, 4- $100, 5- $120, 6- $150 ");
        eleccion = sc.nextInt();
        System.out.println("Ingresa tu numero telefonico (solo puedes ingresar 4 digitos)");
        Scanner sc1 = new Scanner(System.in);
        telefono = sc1.nextInt();
        System.out.println("Confirma tu numero telefonico");
        telefono2 = sc1.nextInt();
        if (telefono == telefono2 && String.valueOf(telefono).length()== 4) {
            switch (eleccion) {
                case 1:
                    cuenta.retirar(20);
                    System.out.println(compañia + " tel: " + telefono + " recarga de: 20");
                    break;
                case 2:
                    cuenta.retirar(50);
                    System.out.println(compañia + " tel: " + telefono + " recarga de: 50");
                    break;
                case 3:
                    cuenta.retirar(80);
                    System.out.println(compañia + " tel: " + telefono + " recarga de: 80");
                    break;
                case 4:
                    cuenta.retirar(100);
                    System.out.println(compañia + " tel: " + telefono + " recarga de: 100");
                    break;
                case 5:
                    cuenta.retirar(120);
                    System.out.println(compañia + " tel: " + telefono + " recarga de: 120");
                    break;
                case 6:
                    cuenta.retirar(150);
                    System.out.println(compañia + " tel: " + telefono + " recarga de: 150");
                    break;
                default:
                    System.out.println("Error. Vuelve a intentarlo");
                    return false;
            }
            return true;
        } else {
            System.out.println("Telefonos no coinciden. Vuelve a intentarlo");
            return false;
        }

    }

    public void InterfazUsuario(Usuario usuario, Cuenta cuenta) {
        this.usuario = usuario;
        this.cuenta = cuenta;
    }

    public void InterfazUsuario() {
        //   this.usuario = "";
        // this.cuenta = "";
    }

}
