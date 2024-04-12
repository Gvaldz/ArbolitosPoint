import java.util.InputMismatchException;
import java.util.Scanner;

public class Egreso {
    private float total;
    private String descripcion;

    public Egreso (){
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void ingresarTotal(float totalCaja) throws InputMismatchException{
        Scanner scanner = new Scanner(System.in);
        do{ 
            try {
                System.out.println("Ingrese la cantidad que va a retirar:");
                System.out.println("Disponible en caja: " + totalCaja);
                setTotal(scanner.nextFloat());
                if (total <= 0) {
                    System.out.println("Ingrese una cantidad válida");
                }
                if (total > totalCaja){
                    System.out.println("Ingrese una cantidad menor a lo que hay en caja");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ingrese un número por favor");
                scanner.nextLine(); 
            }
        }while(total <= 0 || total > totalCaja);
        ingresarDescripcion();
    }

    public void ingresarDescripcion(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el motivo del retiro:");
        setDescripcion(scanner.nextLine());
        System.out.println("------------------------------");
        System.out.println("Ficha de egreso:");
        System.out.println("Descripcion:" + getDescripcion());
        System.out.println("Egreso:" + getTotal());
    }
}
