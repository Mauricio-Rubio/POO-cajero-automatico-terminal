package miProyecto;
public class Usuario {
    private String nombre;
    private String contraseña;
    private String id;
    private Cuenta cuenta;
    
    public String getNombre(){
        return nombre;
    }
    public String getId(){
        return id;
    }
    public boolean validarContraseña(String claveTeclado){
        return claveTeclado.equals(contraseña); //regresa validacion metodo equals
    }
    public void restableceContraseña(String contraseña){
        this.contraseña = contraseña;
    }
    public Cuenta getCuenta(){
        return cuenta;
    }
    public void agregarCuenta(Cuenta cuenta){
        this.cuenta=cuenta;
    }
    public Usuario(String nombre, String contraseña, String id){
        this.id=id;
        this.contraseña=contraseña;
        this.nombre=nombre;
    }

    public Usuario(String nombre) {
        this.nombre = nombre;
        this.id= "0";
        this.contraseña="Nan";
    }
    public Usuario(String nombre, String id) {
        this.nombre = nombre;
        this.id= id;
        this.contraseña="Nan";
    }
    @Override
    public String toString(){
        return nombre+" "+cuenta+" "+id;
    }
    
}
