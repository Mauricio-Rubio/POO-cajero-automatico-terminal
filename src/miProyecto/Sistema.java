/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miProyecto;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author maurh
 */
public class Sistema {
    Scanner teclado;
    ArrayList <Usuario>Bdatos;
    public Sistema(){
        
        //ImprimirMenu();
        Usuario user = new Usuario("Mauricio", "QTR34", "31925679");
        Cuenta cuentica = new Cuenta("31256", 10000);
        user.agregarCuenta(cuentica);
        Bdatos = new ArrayList();
        Bdatos.add(user);
        teclado = new Scanner (System.in);
        login();
    }
    public void ImprimirMenu (Usuario user){
        System.out.println("Banco Nacinal Banana República");
        
        //System.out.println("");
    }
     public void login(){
        int eleccion;
         System.out.println("Ingresa 1 si ya cuentas con usuario, 2 para crear tu usuario");
           eleccion = teclado.nextInt();
        if (eleccion == 1){
            System.out.println("Ingresa tu usuario");
            Scanner sc = new Scanner(System.in);
            Usuario usuarioLogin;
            usuarioLogin = buscarUser(sc.nextLine()); //userLogin no necesita un constructor
            if(usuarioLogin != null){
                System.out.println("Si existe");
                ImprimirMenu(usuarioLogin);
            }else{
                System.out.println("No existes");
            }
            //teclado.hasNextLine();
            //System.out.println("Ingresa tu contraseña");
        
        }   
    }
     public Usuario buscarUser (String id){
         //Trae un usuario de la BDD, lo busca con el Id y lo regresa a quien lo llame
         Usuario userBaseDatos;
         for(int i=0; i<Bdatos.size(); i++){
             userBaseDatos = Bdatos.get(i);
             if(userBaseDatos.getId().equals(id)){
                 
                 return userBaseDatos;
             }else{
                 System.out.println("No encontrado");
                 return null;
             }
         }
       return userBaseDatos;
     }
}
