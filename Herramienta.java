import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Herramienta {
    private Venta venta;
    private Inventario inventario;
    private ArrayList<Reporte> reportes;
    private Reporte reporte;
    private GestionEmpleado empleados;

    public Herramienta(Reporte reporteInicial) {
        this.inventario = new Inventario();
        this.reportes = new ArrayList<>();
        this.reporte = reporteInicial; 
        this.empleados = new GestionEmpleado();
    }

    public Reporte getReporte() {
        return reporte;
    }

    public void setReporte(Reporte reporte) {
        this.reporte = reporte;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public ArrayList<Reporte> getReportes() {
        return reportes;
    }

    public void setReportes(ArrayList<Reporte> nuevosReportes) {
        reportes = nuevosReportes;
    }

    public GestionEmpleado getEmpleados() {
        return empleados;
    }

    public void setEmpleados(GestionEmpleado empleados) {
        this.empleados = empleados;
    }

    public void iniciarSesion(){
        Scanner scanner = new Scanner(System.in);
        boolean cerrarSesion = false;
        boolean bandera = false;
        do {
            System.out.println("Bienvenido nuevamente, ingrese sus datos");
            System.out.println("Ingrese su usuario");
            String nombre = scanner.nextLine();
            System.out.println("Ingrese su contraseña");
            String contraseña = scanner.nextLine();

            for (int i = 0; i < getEmpleados().getEmpleados().size(); i++) {
                Empleado empleado = getEmpleados().getEmpleados().get(i);
                if (empleado.getUsuario().equals(nombre) && empleado.getContraseña().equals(contraseña)) {
                    System.out.println("Inicio de sesión exitoso");
                    bandera = true;
                    if (empleado.getRol().equals("Administrador")) {
                        mostrarMenu();
                    } else {
                        char op = '1';
                        while (op == '1') {
                            System.out.println("Para hacer una venta ingrese 1 para salir teclee cualquier otro caracter");
                            op = scanner.next().charAt(0);
                            if (op == '1') {
                                hacerVenta();
                            } else if (op != '1') {
                                cerrarSesion = true;
                                break;
                            }
                        }
                    }
                }
            }
            if (!bandera) {
                System.out.println("Datos incorrectos, intente nuevamente");
            }
        } while (!bandera && !cerrarSesion);
    }

    public void mostrarMenu() throws InputMismatchException{
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        boolean bandera = true;
        
        do {
            try {
                System.out.println("Bienvenido, ¿Qué desea realizar?");
                System.out.println("1) Gestionar inventario");
                System.out.println("2) Registrar un egreso");
                System.out.println("3) Hacer una nueva venta");
                System.out.println("4) Ver ventas del día");
                System.out.println("5) Ver reportes");
                System.out.println("6) Gestionar empleados");
                System.out.println("7) Salir");
    
                opcion = scanner.nextInt();
                scanner.nextLine(); 
    
                switch (opcion) {
                    case 1:
                        verMenuInventario();
                        break;
                    case 2:
                        Egreso egreso = new Egreso();
                        egreso.ingresarTotal(getReporte().getTotalCaja());
                        agregarEgreso(egreso);
                        break;
                    case 3:
                    hacerVenta();
                        break;
                    case 4:
                        reporte.verVentas();
                        mostrarFaltantes();
                        break;
                    case 5:
                        mostrarReportes(reporte);
                        break;
                    case 6:
                        gestionarEmpleados();
                        break;
                    case 7:
                        bandera = false;
                        break;
                    default:
                        System.out.println("Ingrese una opción válida");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Ingrese un número por favor");
                scanner.nextLine();
            }    
        } while (bandera);
    }

    public void verMenuInventario() throws InputMismatchException{
        Scanner scanner = new Scanner(System.in);
        int opcion2 = 0; 
            try {
                do{
                    System.out.println("Ingrese que desea realizar:");
                    System.out.println("1) Agregar producto");
                    System.out.println("2) Ver productos disponibles");
                    System.out.println("3) Eliminar producto");
                    System.out.println("4) Modificar producto");
                    System.out.println("5) Buscar producto");
                    System.out.println("6) Ver productos faltantes");
                    System.out.println("7) Regresar al menú");
                    opcion2 = scanner.nextInt();
                    scanner.nextLine();
                    
                    switch (opcion2) {
                        case 1:
                        int opcion;
                        do {
                            System.out.println("¿Qué tipo de producto desea agregar?");
                            System.out.println("1) Comestible");
                            System.out.println("2) Bebida");
                            System.out.println("3) Papelería");            
                            opcion = scanner.nextInt();
                            scanner.nextLine();
                        } while (opcion < 1 || opcion > 3);
                        getInventario().agregarProducto(opcion); 
                        break;
                        case 2:
                        getInventario().verProductos();
                        break;
                        case 3:
                        getInventario().eliminarProducto();
                        break;
                        case 4:
                        getInventario().modificarProducto();
                        break;
                        case 5:
                        getInventario().buscarProducto();
                        break;
                        case 6:
                        mostrarFaltantes();
                        break;
                        case 7:
                        break;
                        default:
                        System.out.println("Ingrese una opcion valida");    
                        break;
                        }
                    }while(opcion2 != 7);
                    
                } catch (InputMismatchException e) {
                    System.out.println("Ingrese un número por favor");
                    scanner.nextLine();
                }
    }

    public void gestionarEmpleados() throws InputMismatchException{
        Scanner scanner = new Scanner(System.in);
        int opcion2 = 0; 
        boolean bandera = true;
        do {
            try {
                System.out.println("Ingrese que desea realizar:");
                System.out.println("1) Agregar empleado");
                System.out.println("2) Ver empleados");
                System.out.println("3) Eliminar empleado");
                System.out.println("4) Modificar empleado");
                System.out.println("5) Buscar empleado");
                System.out.println("6) Regresar al menú");
                opcion2=scanner.nextInt();
                scanner.nextLine();
                
                switch (opcion2) {
                    case 1:
                    getEmpleados().agregarEmpleado();
                    break;
                    case 2:
                    getEmpleados().verEmpleados();
                    break;
                    case 3:
                    getEmpleados().eliminarEmpleado();
                    break;
                    case 4:
                    getEmpleados().modificarEmpleado();
                    break;
                    case 5:
                    getEmpleados().buscarEmpleado();
                    break;
                    case 6:
                    bandera = false;
                    break;
                        default:
                        System.out.println("Ingrese una opcion valida");
                        break;
                    }
            } catch (InputMismatchException e) {
                System.out.println("Ingrese un número por favor");
                scanner.nextLine();
            }
        } while (bandera);
    }

    public void hacerVenta(){
        Scanner scanner = new Scanner(System.in);
        char guardar;
        if (inventario.getProductos().size() == 0) {
            System.out.println("No hay productos agregados para realizar una venta");
        } else {
            Venta venta = new Venta(getInventario());
            venta.seleccionarProductos();
            System.out.println("Si desea guardar la venta ingrese 1, sino, escriba cualquier carácter");
            guardar = scanner.next().charAt(0);
            if (guardar == '1') {
                reporte.getVentas().add(venta);
                for (int i = 0; i < reporte.getVentas().size(); i++){
                    venta.setNumventa(i+1);
                }
                venta.restarProductos();
            }
        }
    }

    public void agregarEgreso(Egreso egreso){
        char opcion;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Si desea guardar el egreso ingrese 1, sino, escriba cualquier carácter");
        opcion = scanner.next().charAt(0);
        if(opcion == '1'){
            reporte.getEgresos().add(egreso);
        }
    }

    public void mostrarFaltantes(){
        System.out.println("------------------------------");
        if(inventario.getProductos().size() > 0){
            System.out.println("Productos a resurtir:");

            for(int i = 0; i < getInventario().getProductos().size(); i++){
                Producto producto = getInventario().getProductos().get(i);
                if(producto.getCantidadProducto() <= 5){
                    System.out.println("- " + producto.getNombre() + ": " + producto.getCantidadProducto() + " unidades");
                }
            }
        }
        System.out.println("------------------------------");
    }

    public void mostrarReportes(Reporte reporte) {
        if (reportes.isEmpty()) {
            reportes.add(reporte);
            for(int i = 0; i < reportes.size(); i++){
                reporte.setNumReporte(i+1);
            }
        }      
        for (int i = 0; i < reportes.size(); i++) {
            System.out.println("Numero de reporte: " + reporte.getNumReporte());
            reportes.get(i).verTotal();
        }
    }
}