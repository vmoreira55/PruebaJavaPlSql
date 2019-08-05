package testCalculatorWs;

import java.util.Scanner;
import services.CalculatorOperations;

public class ServicioCalculatorWsTest {

    public static void main(String[] args) {

        String operation;
        boolean flag = false;
        int num1;
        int num2;
        Scanner scanner = new Scanner(System.in);

        CalculatorOperations calculator = new CalculatorOperations();
        do {
            System.out.println("Ingrese la operacion que desea realizar");
            System.out.println("1. Sumar");
            System.out.println("2. Restar");
            System.out.println("3. Multiplicar");
            System.out.println("4. Dividir");
            System.out.println("0. Salir");
            operation = scanner.nextLine().toUpperCase();
 
            if (operation.equals("SUMAR")) {
              System.out.println("Ingrese numero 1:");
                num1 = Integer.parseInt(scanner.nextLine());
                System.out.println("Ingrese numero 2:");
                num2 = Integer.parseInt(scanner.nextLine());
                calculator.saveAdd(num1, num2, operation);

            } else if (operation.equals("RESTAR")) {
                System.out.println("Ingrese numero 1:");
                num1 = Integer.parseInt(scanner.nextLine());
                System.out.println("Ingrese numero 2:");
                num2 = Integer.parseInt(scanner.nextLine());
                calculator.safeSubtract(num1, num2, operation);  
            } else if (operation.equals("MULTIPLICAR")) {
                System.out.println("Ingrese numero 1:");
                num1 = Integer.parseInt(scanner.nextLine());
                System.out.println("Ingrese numero 2:");
                num2 = Integer.parseInt(scanner.nextLine());
                calculator.safeMultiply(num1, num2, operation);  
            } else if (operation.equals("DIVIDIR")) {
                System.out.println("Ingrese numero 1:");
                num1 = Integer.parseInt(scanner.nextLine());
                System.out.println("Ingrese numero 2:");
                num2 = Integer.parseInt(scanner.nextLine());
                calculator.safeDivide(num1, num2, operation);  
            } else if (operation.equals("SALIR")) {
                System.out.println("FIN");
                flag = true;
            } else {
                System.out.println("Opcion invalida");
            }
        } while (flag == false);
    }
}
