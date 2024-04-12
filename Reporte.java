import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Calendar;

public class Reporte {
    private ArrayList<Venta> ventas;
    private ArrayList<Egreso> egresos;
    private int numReporte;
    private float totalIngresos;
    private float totalEgresos;
    private float totalCaja;

    public Reporte(){
        this.ventas = new ArrayList<>();
        this.egresos = new ArrayList<>();
    }

    public ArrayList<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(ArrayList<Venta> ventas) {
        this.ventas = ventas;
    }

    public ArrayList<Egreso> getEgresos() {
        return egresos;
    }

    public void setEgresos(ArrayList<Egreso> egresos) {
        this.egresos = egresos;
    }
    
    public int getNumReporte() {
        return numReporte;
    }

    public void setNumReporte(int numReporte) {
        this.numReporte = numReporte;
    }

    public float getTotalEgresos() {
        return totalEgresos;
    }

    public void setTotalEgresos(float totalEgresos) {
        this.totalEgresos = totalEgresos;
    }

    public float getTotalIngresos() {
        return totalIngresos;
    }

    public void setTotalIngresos(float totalIngresos) {
        this.totalIngresos = totalIngresos;
    }

    public float getTotalCaja() {
        return totalCaja;
    }

    public void setTotalCaja(float totalCaja) {
        this.totalCaja = totalCaja;
    }

    public void modificarTotalCaja(){
        Scanner scanner = new Scanner(System.in);
        float cantidad = 0;
        do {
            try {
                System.out.println("Ingrese la cantidad de dinero que tiene en caja: ");
                cantidad = scanner.nextFloat();
                setTotalCaja(cantidad);
                if (cantidad <= 0) {
                    System.out.println("Ingrese una cantidad válida");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, ingrese un valor numérico válido.");
                scanner.nextLine();
            }
        } while (cantidad <= 0);
    }

    public void verVentas(){
        if(ventas.size() == 0){
            System.out.println("No se han agregado ventas");
        }else{
            System.out.println("------------------------------");
            System.out.println("Ventas del día");
            for(int i = 0; i < ventas.size(); i++){
                Venta venta = ventas.get(i);
                System.out.println("------------------------------");
                System.out.println(venta.getNumventa());
                venta.mostrarTicket();
            }
        }
        verEgresos();
    }

    public void verEgresos(){
        if(egresos.size() == 0){
            System.out.println("No se han agregado egresos");
        }else{
            System.out.println("------------------------------");
            System.out.println("Egresos del día");
            for(int i = 0; i < egresos.size(); i++){
                Egreso egreso = egresos.get(i);
                System.out.println("------------------------------");
                System.out.println("Descripcion:" + egreso.getDescripcion());
                System.out.println("Egreso:" + egreso.getTotal());
                System.out.println("------------------------------");
            }
        }  
        verTotal(); 
    }

    public void verTotal(){
        totalIngresos = 0;
        totalEgresos = 0;
        for (int i = 0; i < ventas.size(); i++) {           
            Venta venta = ventas.get(i);
            totalIngresos += venta.getTotal();
        }
        for (int i = 0; i < egresos.size(); i++) {
            Egreso egreso = egresos.get(i);
            totalEgresos += egreso.getTotal();
        }
        float total = (totalIngresos - totalEgresos);
        totalCaja = totalCaja + total;
        Calendar fecha = Calendar.getInstance();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fechaString = formato.format(fecha.getTime()); 
        System.out.println("Fecha" + fechaString);        
        System.out.println("------------------------------");
        System.out.println("Ingresos totales del día: " + totalIngresos);
        System.out.println("------------------------------");
        System.out.println("Egresos totales del día: " + totalEgresos);                
        System.out.println("------------------------------");
        System.out.println("Dinero que hay en caja: " + totalCaja);
        System.out.println("------------------------------");
        totalCaja = totalCaja - total;
        totalIngresos = 0;
        totalEgresos = 0;
        total = 0;
    }
}