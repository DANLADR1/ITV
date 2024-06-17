import itv.vehiculos.Vehiculo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner entrada = new Scanner(System.in);

    // static List<Vehiculo> cola=new ArrayList<>();
    static ArrayList<Vehiculo> cola=new ArrayList<>();

    public static void main(String[] args) {
        char opcion;
        do{
            opcion=menu();
            switch(opcion){
                case '1':
                    alta();
                    break;
                case '2':
                    datos();
                    break;
                case '3':
                    siguiente();
                    break;
                case '4':
                    delante();
                    break;
                case '5':
                    baja();
                    break;
                case '6':
                    resto();
                    break;
                case '7':
                    nueva();
                    break;
            }
        }while(opcion!='X');
    }

    public static char menu()  {
        // Borra Pantalla
        BorrarPantalla();

        System.out.println("1.Llega del vehículo.");
        System.out.println("2.Datos del Vehículo.");
        System.out.println("3.Que pase el siguiente.");
        System.out.println("4.Delante de usted.");
        System.out.println("5.Baja del vehículo.");
        System.out.println("6.Cuántos quedan hasta el cierre.");
        System.out.println("7.Nueva lista de espera.");
        System.out.println("X.Salir.");
        return Character.toUpperCase(entrada.next().charAt(0));
    }

    private static void BorrarPantalla() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {}
    }

    public static void alta(){
        // Borra Pantalla
        BorrarPantalla();

        System.out.println("Añade un nuevo vehículo a la lista de espera.");
        System.out.println("---------------------------------------------");
        try{
            System.out.print("\nMatrícula: ");
            Vehiculo v=new Vehiculo(entrada.next());
            if(!cola.contains(v)){
                System.out.print("Tipo de vehículo "+Vehiculo.TIPOS.cadenaItems());
                v.setTipo(entrada.nextInt());
                System.out.print("Inspección periódica (P) o revisión (R):");
                v.setInspeccion(entrada.next().charAt(0));
                cola.add(v);
            }
            else {
                System.out.println("\n¡Aviso!:El vehículo ya está en la lista de espera.");
            }
        }
        catch (IllegalArgumentException ex) {
            System.out.println("\n"+ex.getMessage());
        }
        catch (InputMismatchException ex){
            System.out.println("\n¡Error!:Tipo de dato no válido.");
        }

        // Para pausar la ejecución del programa
        Pausa();
    }

    public static void datos(){
        // Borra Pantalla
        BorrarPantalla();

        System.out.println("Datos del vehículo.");
        System.out.println("-------------------");
        try{
            System.out.print("\nMatrícula: ");
            Vehiculo v=new Vehiculo(entrada.next());
            int posicion=cola.indexOf(v);
            if(posicion==-1) {
                System.out.println("\n¡Aviso!:El vehículo no está dado de alta en la lista de espera.");
            }
            else{
                v=cola.get(posicion);
                BorrarPantalla();
                System.out.println("Datos del vehículo.");
                System.out.println("-------------------\n");
                System.out.print(v.toString());
            }
        }
        catch (IllegalArgumentException ex) {
            System.out.println("\n"+ex.getMessage());
        }

        // Para pausar la ejecución del programa
        Pausa();
    }

    private static void Pausa() {
        entrada.nextLine(); // rudimentaria limpieza del buffer de entrada
        System.out.println("\n\nPresione [ENTER] para continuar...");
        entrada.nextLine();
    }

    public static void siguiente(){
        // Borra pantalla
        BorrarPantalla();

        System.out.println("Siguiente vehículo a inspeccionar.");
        System.out.println("----------------------------------");
        if(cola.isEmpty()){
            System.out.println("\n¡Aviso!:No hay vehículos en la lista de espera.");
        }
        else{
            System.out.println(cola.get(0).toString());
            cola.remove(0);
        }

        // Para pausar la ejecución del programa
        Pausa();
    }

    public static void delante(){
        // Borra pantalla
        BorrarPantalla();

        System.out.println("Vehículos delante de usted.");
        System.out.println("---------------------------");
        if(cola.isEmpty()){
            System.out.println("\n¡Aviso!:No hay vehículos en la lista de espera.");
        }
        else{
            try{
                System.out.print("\nMatrícula: ");
                Vehiculo v=new Vehiculo(entrada.next());
                int posicion=cola.indexOf(v);
                if(posicion==-1) {
                    System.out.println("\n¡Aviso!:El vehículo no está dado de alta en la lista de espera.");
                }
                else{
                    System.out.print("\nTiene delante de usted "+posicion+ " vehículos");
                }
            }
            catch (IllegalArgumentException ex) {
                System.out.println("\n"+ex.getMessage());
            }
        }

        // Para pausar la ejecución del programa
        Pausa();
    }

    public static void baja(){
        // Borra pantalla
        BorrarPantalla();

        System.out.println("Vehículo a dar de baja.");
        System.out.println("-----------------------");
        if(cola.isEmpty()){
            System.out.println("\n¡Aviso!:No hay vehículos en la lista de espera.");
        }
        else{
            try{
                System.out.print("\nMatrícula: ");
                Vehiculo v=new Vehiculo(entrada.next());
                int posicion=cola.indexOf(v);
                if(posicion==-1) {
                    System.out.println("\n¡Aviso!:El vehículo no está dado de alta en la lista de espera.");
                }
                else{
                    cola.remove(posicion);
                }
            }
            catch (IllegalArgumentException ex) {
                System.out.println("\n"+ex.getMessage());
            }
        }

        // Para pausar la ejecución del programa
        Pausa();

    }

    public static void resto(){
        // Borra pantalla
        BorrarPantalla();

        System.out.println("Lista de vehículos en la lista de espera.");
        System.out.println("----------------------------------------\n");
        for(Vehiculo item:cola){
            System.out.println(item.toString()+"\n\n");
        }

        // Para pausar la ejecución del programa
        Pausa();
    }

    public static void nueva(){
        // Borra pantalla
        BorrarPantalla();

        // Debería pedirse la conformidad del usuario...
        cola.clear();
        System.out.println("\nSe han borrado todos los vehículos de la lista de espera.");

        // Para pausar la ejecución del programa
        Pausa();
    }

}