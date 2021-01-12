package miProyecto;

import java.util.Scanner;

public class InterfazUsuario {
    private Usuario usuario;
    private Cuenta cuenta;
    private Scanner teclado;
    
    public InterfazUsuario(Usuario usuario, Cuenta cuenta){
        this.usuario = usuario;
        this.cuenta = cuenta;
        teclado = new Scanner (System.in);
    }

    public void consulta(){
        System.out.println("Tu saldo es: "+cuenta.consultar());
    }
    public void abono(){
        float monto;
        System.out.println("Ingresa el monto a ingresar");
        monto = teclado.nextFloat();
        if(monto > 50){
        cuenta.abonar(monto);
            System.out.println("Se abonó: "+monto+" saldo restante: "+cuenta.consultar());
        }else{
            System.out.println("No puedes ingresar esa cantidad");
        }
    }
    public void reitro (){
         float monto;
        System.out.println("Ingresa el monto a retirar");
        monto = teclado.nextFloat();
        if(monto <= cuenta.consultar()){
            cuenta.retirar(monto);
            System.out.println("Se retiró: "+monto+" saldo restante: "+cuenta.consultar());
        }else{
            System.out.println("No puedes retirar esa cantidad");
        }
    }
}
