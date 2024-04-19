import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Inventario {
    private ArrayList<Producto> productos;
    
    public Inventario() {
        productos = new ArrayList<>();
    }
    
    public ArrayList<Producto> getProductos() {
        return productos;
    }
    
    public void agregarProducto(int opcion) {
        Scanner scanner = new Scanner(System.in);
            try {
                Producto producto = null;
                switch (opcion) {
                    case 1:
                        producto = new Comestible();
                        System.out.println("Ingrese el nombre del comestible:");
                        break;
                    case 2:
                        producto = new Bebida();
                        System.out.println("Ingrese el nombre de la bebida:");
                        break;
                    case 3:
                        producto = new Papeleria();
                        System.out.println("Ingrese el nombre del artículo de papelería:");
                        break;
                    default:
                        break;
                }
                String nombre;
                boolean encontrado = false; 
                do {
                    encontrado = false;
                    nombre = scanner.nextLine().trim();
                    for (int i = 0; i < productos.size(); i++) {
                        Producto p = productos.get(i);
                        if (p.getNombre().equalsIgnoreCase(nombre)) {
                            encontrado = true;
                            System.out.println("¡El producto ya existe en el inventario! Ingrese otro nombre.");
                        }
                    }
                } while (encontrado);

                producto.setNombre(nombre);
                
                do {
                    try {
                        System.out.println("Ingrese el precio:");
                        float nuevoPrecio = scanner.nextFloat();
                        producto.setPrecio(nuevoPrecio);
                        if (producto.getPrecio() <= 0) {
                            System.out.println("Ingrese un precio válido");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Por favor, ingrese un valor numérico válido para el precio.");
                        scanner.nextLine();
                    }
                } while (producto.getPrecio() <= 0);
                do {
                    try {
                        System.out.println("Ingrese la cantidad de productos:");
                        int nuevaCantidad = scanner.nextInt();
                        producto.setCantidadProducto(nuevaCantidad);
                        if (producto.getCantidadProducto() <= 0) {
                            System.out.println("Ingrese una cantidad válida");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Por favor, ingrese un valor numérico válido para la cantidad.");
                        scanner.nextLine();
                    }
                } while (producto.getCantidadProducto() <= 0);
                switch (opcion) {
                    case 1:
                        ((Comestible) producto).ingresarDescripcion();
                        break;
                    case 2:
                        ((Bebida) producto).ingresarDescripcion();
                        break;
                    case 3:
                        ((Papeleria) producto).ingresarDescripcion();
                        break;
                    default:
                        break;
                }
                System.out.println("Ficha del Producto:");
                System.out.println("Nombre: " + producto.getNombre());
                System.out.println("Precio: " + producto.getPrecio());
                System.out.println("Cantidad: " + producto.getCantidadProducto());
                producto.mostrarDetalles();
                int resp;
                System.out.println("Si desea guardar el producto ingrese 1, sino, escriba cualquier carácter");
                resp = scanner.next().charAt(0);
                scanner.nextLine(); 
                if(resp == '1'){
                    productos.add(producto);
                    System.out.println("Producto agregado con éxito.");
                } 
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número entero.");
                scanner.nextLine();
            }        
    }

    public void modificarProducto(int opcion, Producto producto) {
        Scanner scanner = new Scanner(System.in);
        switch (opcion) {
            case 1:
                System.out.println("Ingrese el nuevo nombre:");
                String nuevoNombre = scanner.nextLine();
                producto.setNombre(nuevoNombre);
                break;
                case 2:
                do {
                    try {
                        System.out.println("Ingrese el nuevo precio:");
                        float nuevoPrecio = scanner.nextFloat();
                        producto.setPrecio(nuevoPrecio);
                        if (producto.getPrecio() <= 0) {
                            System.out.println("Ingrese un precio válido");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Por favor, ingrese un valor numérico válido para el precio.");
                        scanner.nextLine();
                    }
                } while (producto.getPrecio() <= 0);
                break;
                case 3:
                do {
                    try {
                        System.out.println("Ingrese la nueva cantidad:");
                        int nuevaCantidad = scanner.nextInt();
                        producto.setCantidadProducto(nuevaCantidad);
                        if (producto.getCantidadProducto() <= 0) {
                            System.out.println("Ingrese una cantidad válida");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Por favor, ingrese un valor numérico válido para la cantidad.");
                        scanner.nextLine();
                    }
                } while (producto.getCantidadProducto() <= 0);
                break;
            case 4:
            switch (producto.getTipo()) {
                case "Comestible":
                    ((Comestible) producto).ingresarDescripcion();
                    break;
                case "Bebida":
                    ((Bebida) producto).ingresarDescripcion();
                    break;
                case "Papeleria":
                ((Papeleria) producto).ingresarDescripcion();
                break;
                default:
                System.out.println("Opción no válida.");
                break;
            }
            break;
            default:
            System.out.println("Opción no válida.");
            break;
        }
    }
    
    public void verProductos(){
        if(productos.size()==0){
            System.out.println("No se han agregado productos");
        }else{
            System.out.println("Productos disponibles:");
            for(int i = 0; i < productos.size(); i++){
                Producto producto = productos.get(i);
                System.out.println("------------------------------");
                System.out.println((i+1) + ")");
                System.out.println("Nombre: " + producto.getNombre());
                System.out.println("Precio: " + producto.getPrecio());
                System.out.println("Cantidad: " + producto.getCantidadProducto());
                producto.mostrarDetalles();
            }
        }
    }

    public void buscarProducto() {
        Scanner scanner = new Scanner(System.in);

        String nombreProducto;
        if(productos.size()==0){
            System.out.println("No se han agregado productos");
        }else{
            System.out.println("Ingrese el nombre del producto que desea buscar:");
            nombreProducto = scanner.nextLine().trim();
        
            boolean encontrado = false;
            for (int i = 0; i < productos.size(); i++) {
                Producto producto = productos.get(i);
                if (producto.getNombre().equalsIgnoreCase(nombreProducto)) {
                    System.out.println("Nombre: " + producto.getNombre());
                    System.out.println("Precio: " + producto.getPrecio());
                    System.out.println("Cantidad: " + producto.getCantidadProducto());
                    producto.mostrarDetalles();
                    encontrado = true;
                }
            }
            if (!encontrado) {
                System.out.println("No se encontró ningún producto con el nombre " + nombreProducto);
            }
        }
    }
  
    public void eliminarProducto() {
        Scanner scanner = new Scanner(System.in);

        if(productos.size()==0){
            System.out.println("No se han agregado productos");
        }else{
            String nombreProducto;
            System.out.println("Ingrese el nombre del producto que desea eliminar:");
            nombreProducto = scanner.nextLine();
            
            boolean encontrado = false;
            for (int i = 0; i < productos.size(); i++) {
                Producto producto = productos.get(i);
                if (producto.getNombre().equals(nombreProducto)) {
                    encontrado = true;
                    productos.remove(i);
                    System.out.println("El producto '" + nombreProducto + "' ha sido eliminado.");
                }
            }
            if (!encontrado) {
                System.out.println("No se encontró ningún producto con el nombre " + nombreProducto);
            }
        }
    }
}
