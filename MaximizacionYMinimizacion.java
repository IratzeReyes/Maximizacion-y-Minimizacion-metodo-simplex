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
        //se pide el dato de los coeficientes de la funcion
        System.out.println("Ingrese los coeficientes de la funcion objetivo: "); //se ingresan los coeficientes primero
        for (int i = 0; i < numVariables; i++) {
            System.out.print("Coeficiente " + (i + 1) + " = ");
            funcionC[i] = lector.nextDouble();
        }
        System.out.println("Ingrese las restricciones del sistema AX <= b: "); //luego se ingresan las restricciones
        for (int i = 0; i < numRestricciones; i++) {
            System.out.println("Restriccion R" + (i + 1) + ": ");
            for (int j = 0; j < numVariables; j++) {
                System.out.print("Ingrese el Coeficiente X" + (j + 1) +  ":");
                matrizA[i][j] = lector.nextDouble();
            }
            System.out.print("Ingrese el valor b" + (i + 1) + ": ");
            matrizB[i] = lector.nextDouble();
        }
        //en esta parte  si es minimizacion, se convierte minimizacion z en maximizacion -z
        if (opcion == 2) {
            for (int i = 0; i < funcionC.length; i++) {
                funcionC[i] = -funcionC[i];
            }
        }



    }
}
