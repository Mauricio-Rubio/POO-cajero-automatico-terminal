package miProyecto;

public class Cuenta {

    private String id;
    private float fondos;

    public void abonar(float monto) {
        if ( monto >= 100) {
            fondos = fondos + monto;
            System.out.println("Saldo restante: "+fondos);
        } else {
            System.out.println("Deposito mínimo de 100.00 mxn");
        }
    }

    public void retirar(float monto) {
        if (fondos >= monto && monto >= 50) {
            fondos = fondos - monto;
            System.out.println("Saldo restante: "+fondos);
        } else {
            System.out.println("Saldo insuficiente");
        }
    }

    public float consultar() {
        return fondos;
    }

    public String getId() {
        return id;
    }

    public Cuenta(String id, float fondos) {
        this.id = id;
        this.fondos = fondos;
    }
}
