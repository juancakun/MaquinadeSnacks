package maquina_snacks;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class MaquinaSnacks {
    public static void main(String[] args) {
        maquinaSnacks();
    }

    public static void maquinaSnacks(){
        var salir = false;
        var consola = new Scanner(System.in);
        //Creamos la lista de productos de tipo snack
        List<Snack> productos = new ArrayList<>();
        System.out.println("*** Maquina de Snacks ***");
        Snacks.mostrarSnacks();
        while(!salir){
            try{
                var opcion = mostrarMenu(consola);
                salir = ejecutarOpciones(opcion, consola, productos);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            } finally {
                System.out.println();
            }
        }
    }

    private static int mostrarMenu(Scanner sc){
        System.out.print("""
                Menu:
                1. Comprar snack
                2. Mostrar ticket
                3. Agregar nuevo Snack
                4. Salir
                Elige una opción:\s""");
        return Integer.parseInt(sc.nextLine());
    }

    private static boolean ejecutarOpciones(int opcion, Scanner sc, List<Snack> productos){
        var salir = false;
        switch (opcion){
            case 1 -> comprarSnack(sc, productos);
            case 2 -> mostrarTicket(productos);
            case 3 -> agregarSnack(sc);
            case 4 -> salir = true;
            default -> System.out.println("Opción invalida");
        }
        return salir;
    }

    private static void comprarSnack(Scanner sc, List<Snack> productos){
        System.out.print("Qué snack quieres comprar (id)?");
        var idSnack = Integer.parseInt(sc.nextLine());
        //Validar que el snack exista en la lista de snacks
        var snackEncontrado = false;
        for(var snack : Snacks.getSnacks()){
            if(snack.getIdSnack() == idSnack){
                productos.add(snack);
                System.out.println("Okay, Snack agregado: " + snack);
                snackEncontrado = true;
                break;
            }
        }
        if(!snackEncontrado){
            System.out.println("Error, Snack no encontrado: "+idSnack);
        }

    }

    private static void mostrarTicket(List<Snack> productos){
        System.out.println("*** Ticket de venta ***");
        var ticket = "";
        var total = 0.0;
        for(var snack : productos){
            ticket += "\n\t- " + snack.getNombre() + " - $" + snack.getPrecio();
            total += snack.getPrecio();
        }
        ticket += "\n\n\t Total -> $" + total + "\n";
        System.out.print(ticket);
    }

    private static void agregarSnack(Scanner sc){
        System.out.print("Cuál es el nombre del snack que quieres agregar? ");
        var nombre = sc.nextLine();
        System.out.print("Cuál es el precio del snack? ");
        var  precio = Double.parseDouble(sc.nextLine());
        sc.nextLine();
        Snacks.agregarSnack(new Snack(nombre, precio));
        Snacks.mostrarSnacks();
    }

}
