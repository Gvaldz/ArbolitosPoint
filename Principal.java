import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Reporte reporte = new Reporte();
        Herramienta herramienta = new Herramienta(reporte);
        Scanner scanner = new Scanner(System.in);
        char opcion;

        boolean continuar = true;
        while (continuar) {
            if (herramienta.getEmpleados().getEmpleados().size() == 0) {
                System.out.println("Bienvenido al sistema, agregaremos un perfil de administrador");
                herramienta.getEmpleados().agregarEmpleado();
                reporte.modificarTotalCaja();
                System.out.println("Para usar su punto de venta comenzaremos por agregar productos");
                herramienta.verMenuInventario();
                herramienta.mostrarMenu();
            } else {
                boolean reiniciarSesion = true;
                while (reiniciarSesion) {
                    System.out.println("Para iniciar sesion nuevamente ingrese 1");
                    opcion = scanner.next().charAt(0);
                    if (opcion != '1') {
                        System.out.println("Hasta pronto");
                        continuar = false;
                        reiniciarSesion = false;
                    } else {
                        herramienta.iniciarSesion();
                    }
                }
            }
        }
    }
}