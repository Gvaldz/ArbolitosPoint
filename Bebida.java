import java.util.InputMismatchException;
import java.util.Scanner;

public class Bebida extends Producto {
    private float volumen;

    public Bebida(){
        super();
        this.setTipo("Bebida");
    }

    public float getVolumen() {
        return volumen;
    }
    public void setVolumen(float volumen) {
        this.volumen = volumen;
    }
    public void ingresarDescripcion(){
        int opcion = 0;
        Scanner scanner = new Scanner(System.in);
    
        do {
            try {
                System.out.println("Seleccione si el producto está en litros o mililitros");
                System.out.println("1) Litros");
                System.out.println("2) Mililitros");
                opcion = scanner.nextInt();
                scanner.nextLine();
                if (opcion < 1 || opcion > 2) {
                    System.out.println("Ingrese una opción válida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ingrese un número entero válido.");
                scanner.nextLine(); 
            }
        } while (opcion < 1 || opcion > 2);
    
        float volumen = 0;
        do {
            try {
                if (opcion == 1) {
                    System.out.println("Ingrese los litros del producto");
                } else {
                    System.out.println("Ingrese los mililitros del producto");
                }
                volumen = scanner.nextFloat();
                if (volumen <= 0) {
                    System.out.println("Ingrese una cantidad válida");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ingrese un número válido.");
                scanner.nextLine();
            }
        } while (volumen <= 0);
        setVolumen(volumen);
    }
    
    public void mostrarDetalles() {
        if (volumen >= 100) {
            System.out.println("Volumen: " + getVolumen() + " militros"); 
        } else {
            System.out.println("Volumen: " + getVolumen() + " litros"); 
        }
    }
}