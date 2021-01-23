package miProyecto;

import java.util.Scanner;

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

    public void abono() {
        float monto;
        System.out.println("Ingresa el monto a ingresar");
        monto = teclado.nextFloat();
        if (monto >= 50) {
            cuenta.abonar(monto);
            System.out.println("Se abonó: " + monto + " saldo restante: " + cuenta.consultar());
        } else {
            System.out.println("Depostito mínimo de 50.00 MXN");
        }
    }

    public void reitro() {
        float monto;
        System.out.println("Ingresa el monto a retirar");
        monto = teclado.nextFloat();
        if (monto >= 50) {
            if (monto <= cuenta.consultar()) {
                cuenta.retirar(monto);
                System.out.println("Se retiró: " + monto + "MXN saldo restante: " + cuenta.consultar() + "MXN");
            } else {
                System.out.println("No puedes retirar esa cantidad");
            }
        } else {
            System.out.println("Retiro minimo: 50.00 MXN");

        }

    }
    public void pagoServicios(){
        String servicio;
        float monto;
        System.out.println("Servicio a pagar");
        servicio = teclado.nextLine();
        System.out.println("Monto a cobrar");
        monto = teclado.nextFloat();
        if(monto <= cuenta.consultar()){
            cuenta.retirar(monto);
            System.out.println("Servicio: "+servicio);
            System.out.println("Importe: "+monto);
            System.out.println("Saldo restante: "+cuenta.consultar());
        }else{
            System.out.println("Saldo insuficiente");
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
