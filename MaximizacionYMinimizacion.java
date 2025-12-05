import java.util.Scanner;
public class MaximizacionYMinimizacion {
    public static void main (String[]args){
        Scanner lector = new Scanner(System.in);
        //Presentar el menu de opciones
        System.out.println("\n \n \t \tMENU");
        System.out.println("1. Maximizacion ");
        System.out.println("2. Minimizacion ");
        System.out.print("Seleccione una opcion: ");
        int opcion = lector.nextInt();
        //pedir datos basicos como el numero de variables y restricciones
        System.out.print("Ingrese el numero de variables: ");
        int numVariables = lector.nextInt();
        System.out.print("Ingrese el numero de restricciones: ");
        int numRestricciones = lector.nextInt();
        //creacion del matriz para guardar datos
        double[][] matrizA = new double[numRestricciones][numVariables];
        double[] matrizB = new double[numRestricciones];
        double[] funcionC = new double[numVariables];
        

    }
}
