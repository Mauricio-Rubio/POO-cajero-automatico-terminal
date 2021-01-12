package miProyecto;

public class Cuenta {
    private String id;
    private float fondos;
    public void abonar (float x){
        fondos = fondos + x;
    }
    public void retirar (float x){
        fondos = fondos - x;
    }
    public float consultar (){
       return fondos;
    }
    public String getId(){
        return id;
    }
    public Cuenta(String id, float fondos){
    this.id=id;
    this.fondos=fondos;
}
}
