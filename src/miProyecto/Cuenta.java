package miProyecto;
public class Cuenta {
    private String id;
    private float fondos;

    Cuenta(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void abonar(float monto) {
            fondos = fondos + monto;
    }
    public void retirar(float monto) {
        fondos = fondos - monto;
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
    @Override
    public String toString(){
        return " fondos: "+fondos+" MXN";
    }
}
