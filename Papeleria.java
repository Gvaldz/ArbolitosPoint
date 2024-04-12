import java.util.Scanner;

public class Papeleria extends Producto {
    private String descripcion;

    public Papeleria(){
        super();
        this.setTipo("Papeleria");
    }

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void ingresarDescripcion(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese la descripción del producto");
        setDescripcion(scanner.nextLine());
    }

    public void mostrarDetalles() {
        System.out.println("Descripción: " + getDescripcion());
    }
}