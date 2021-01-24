package miProyecto;
public class Main {
    public static void main(String[] args) {
        Usuario test = new Usuario("Mauricio", "MR502", "302");
        Cuenta cuentica = new Cuenta("333", 500);
        test.agregarCuenta(cuentica);
        new Sistema ().actualizarUsuario(test);
    }
}
