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
        // se crea la tabla simplex
        int columnas = numVariables + numRestricciones + 1;
        int filas = numRestricciones + 1;
        double[][] tablaSimplex = new double[filas][columnas];
        //crear un for par el llenado de la tabla simplex
        for (int i = 0; i < numRestricciones; i++) {
            for (int j = 0; j < numVariables; j++) {
                tablaSimplex[i][j] = matrizA[i][j];
                tablaSimplex[i][numVariables + i] = 1; //agregar las variables de holgura
                tablaSimplex[i][columnas - 1] = matrizB[i]; //agregar los valores b
    
            }
        }
        //fila negativa para el punto de maximizar 
        for (int j = 0; j < numVariables; j++) {
            tablaSimplex[numRestricciones][j] = -funcionC[j];
        }
        //procedimiento para el paso del calculo simplex
        while(true){
            //buscar primero la columna pivote 
            int columnaPivote = encontrarColumnaPivote(tablaSimplex, numRestricciones, columnas);
            if (columnaPivote == -1) {
                break; //si no hay columna pivote, se termina el proceso

            int filaPivote = encontrarFilaPivote(tablaSimplex, numRestricciones, columnas, columnaPivote);
            if (filaPivote == -1) {
                System.out.println("El problema no tiene solucion factible.");
                return;
            }
            //realizar el pivoteo
            pivotear(tablaSimplex, filaPivote, columnaPivote);
        }
        //mostrar los resultados
        




    }
    //funcion para encontrar la columna pivote
    public static int encontrarColumnaPivote(double[][] tablaSimplex, int numRestricciones, int columnas){
        int columnaPivote = -1;
        double valorMinimo = 0;
        for (int j = 0; j < columnas - 1; j++) {
            if (tablaSimplex[numRestricciones][j] < valorMinimo) {
                valorMinimo = tablaSimplex[numRestricciones][j];
                columnaPivote = j;
            }
        }
        return columnaPivote;
    }
    //funcion para encontrar la fila pivote
    public static int encontrarFilaPivote(double[][] tablaSimplex, int columnaPivote, int numRestricciones, int columnas){
        int filaPivote = -1;
        double valorMejor = 0;
        for (int i=0; i < numRestricciones; i++){
            if (tablaSimplex[i][columnaPivote]>0){
                double razon = tablaSimplex[i][columnas -1] / tablaSimplex[i][columnaPivote];
                if (razon < valorMejor){
                    valorMejor = razon;
                    filaPivote = i;
                }
            }
        }
        return filaPivote;
    }
    public static void pivotear(double[][] tablaSimplex, int filaPivote, int columnaPivote){
        double pivote = tablaSimplex[filaPivote][columnaPivote];
        for (int j=0; j < tablaSimplex[0].length; j++){
            tablaSimplex[filaPivote][j] /= pivote;
        }
        for (int i=0; i< tablaSimplex.length; i++){
            if (i != filaPivote){
                double factor = tablaSimplex[i][columnaPivote];
                for (int j=0; j < tablaSimplex[0].length; j++){
                    tablaSimplex[i][j] -= factor * tablaSimplex[filaPivote][j];
                }
            }
        }
    }
    



 
}

