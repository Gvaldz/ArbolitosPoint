import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Venta {
    private Inventario inventario;
    private float total;
    private int numventa;
    private float pago;
    private float cambio;
    private ArrayList<Producto> productosComprados;
    private ArrayList<Integer> cantidadesCompradas;
    private String fecha;

    public Venta(Inventario inventario) {
        this.inventario = inventario;
        this.productosComprados = new ArrayList<>();
        this.cantidadesCompradas = new ArrayList<>();
    }

    public ArrayList<Integer> getCantidadesCompradas() {
        return cantidadesCompradas;
    }

    public void setCantidadesCompradas(ArrayList<Integer> cantidadesCompradas) {
        this.cantidadesCompradas = cantidadesCompradas;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getNumventa() {
        return numventa;
    }

    public void setNumventa(int numventa) {
        this.numventa = numventa;
    }

    public float getPago() {
        return pago;
    }

    public void setPago(float pago) {
        this.pago = pago;
    }

    public float getCambio() {
        return cambio;
    }

    public void setCambio(float cambio) {
        this.cambio = cambio;
    }

    public ArrayList<Producto> getProductosComprados() {
        return productosComprados;
    }

    public void setProductosComprados(ArrayList<Producto> productosComprados) {
        this.productosComprados = productosComprados;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFecha() {
        return fecha;
    }

    public void seleccionarProductos() throws InputMismatchException {
        int num = 0;
        char opc;
        boolean continuar = true;
        int cantidadComprada;
        Scanner scanner = new Scanner(System.in);
        do {
            inventario.verProductos();
            boolean numValido = false;
            while (!numValido) {
                try {
                    System.out.println("Ingrese el número de producto");
                    num = scanner.nextInt();
                    numValido = (num >= 1 && num <= inventario.getProductos().size());
                    if (!numValido) {
                        System.out.println("Número de producto inválido. Inténtelo de nuevo.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Ingrese un número válido.");
                    scanner.nextLine(); 
                }
            }
            Producto productoSeleccionado = inventario.getProductos().get(num - 1);
            boolean cantidadValida = false;
            do {
                System.out.println("Unidades restantes: " + productoSeleccionado.getCantidadProducto());
                System.out.println("Ingrese la cantidad de producto");
                try {
                    cantidadComprada = scanner.nextInt();
                    if (cantidadComprada >= 1 && cantidadComprada <= productoSeleccionado.getCantidadProducto()) {
                        productosComprados.add(productoSeleccionado);
                        cantidadesCompradas.add(cantidadComprada);
                        cantidadValida = true;
                    } else {
                        System.out.println("Cantidad inválida, por favor ingrese una cantidad válida.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Ingrese un número válido.");
                    scanner.nextLine(); 
                }
            } while (!cantidadValida);
            System.out.println("Si desea agregar otro producto escriba 1, si no, teclee cualquier caracter");
            opc = scanner.next().charAt(0);
            if (opc != '1') {
                continuar = false;
            }
        } while (continuar);
        hacerCuentas();
    }

    public void hacerCuentas() throws InputMismatchException{
        Scanner scanner = new Scanner(System.in);
        total = 0;
        for (int i = 0; i < productosComprados.size(); i++) {
            Producto producto = productosComprados.get(i);
            int cantidad = cantidadesCompradas.get(i);
            total += producto.getPrecio() * cantidad;
        }
        System.out.println("Total: "+ getTotal());
        do{
            try {
                System.out.println("Ingrese la cantidad con que pagará el cliente");
                setPago(scanner.nextFloat());
                if(pago < getTotal()){
                    System.out.println("Ingrese una cantidad igual o mayor al total");
                }

            } catch (InputMismatchException e) {
                System.out.println("Ingrese un número por favor");
                scanner.nextLine(); 
            }
        }while(pago < getTotal());
        setCambio(pago - total);
        System.out.println("Cambio: " + getCambio());
        mostrarTicket();
    }

    public void mostrarTicket(){
        Calendar fecha = Calendar.getInstance();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fechaString = formato.format(fecha.getTime()); 
        setFecha(fechaString);
        System.out.println("Fecha: " + fechaString);
        System.out.println("********** TICKET DE VENTA **********");
        System.out.println("Productos vendidos:");
        for (int i = 0; i < productosComprados.size(); i++) {
            Producto producto = productosComprados.get(i);
            Integer cantidad = cantidadesCompradas.get(i);
            System.out.println("Nombre: " + producto.getNombre() + ", Cantidad: " + cantidad + ", Precio unitario: $" + producto.getPrecio());
        }
        System.out.println("Total: " + getTotal());
        System.out.println("Cambio: " + getCambio());
    }

    public void restarProductos(){
        for (int i = 0; i < productosComprados.size(); i++) {
            Producto producto = productosComprados.get(i);
            producto.setCantidadProducto(producto.getCantidadProducto() - cantidadesCompradas.get(i));
        }
    }
}