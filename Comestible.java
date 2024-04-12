import java.util.InputMismatchException;
import java.util.Scanner;

public class Comestible extends Producto{
    private float peso;

    public Comestible(){
        super();
        this.setTipo("Comestible");
    }

    public float getPeso() {
        return peso;
    }
    public void setPeso(float peso) {
        this.peso = peso;
    }

public void ingresarDescripcion(){
    Scanner scanner = new Scanner(System.in);
    boolean bandera = false;
    do {
        try {
            System.out.println("Ingrese el peso del producto en gr");
            setPeso(scanner.nextFloat());
            bandera = true;
        } catch (InputMismatchException e) {
            System.out.println("Ingrese un número válido.");
            scanner.nextLine();
        }
    } while (!bandera || peso <= 0);
}

    public void mostrarDetalles() {
        System.out.println("Peso: " + getPeso() + " gramos");
    }
}
