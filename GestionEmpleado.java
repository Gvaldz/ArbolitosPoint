import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GestionEmpleado {
    private ArrayList<Empleado> empleados;
    
    public GestionEmpleado() {
        empleados = new ArrayList<>();
    }
    
    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }
    
    public void agregarEmpleado() {
        String contraseña;
        
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el usuario del empleado:");
        String nombre = scanner.nextLine();
    
        do{ 
            System.out.println("Ingrese una contraseña para el empleado:");
            System.out.println("Minimo 6 digitos");
            contraseña = scanner.nextLine();
            if(contraseña.length() < 6){
                System.out.println("Ingrese una contraseña válida");
            }
        }while(contraseña.length() < 6);
    
        String rol;
        if (empleados.size() == 0) {
            rol = "Administrador";
            } else {
            rol = "Empleado";
        }
    
        Empleado empleado = new Empleado(nombre, contraseña, rol);
        empleados.add(empleado);
    
        System.out.println("Empleado agregado con éxito.");
    }

    public void modificarEmpleado() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Ingrese el nombre del empleado que desea modificar:");
            String nombreEmpleado = scanner.nextLine();
            boolean encontrado = false;
            for (int i = 0; i < empleados.size(); i++) {
                Empleado empleado = empleados.get(i);
                if (empleado.getUsuario().equals(nombreEmpleado)) {
                    encontrado = true;
                    System.out.println("¿Qué desea modificar del empleado " + nombreEmpleado + "?");
                    System.out.println("1) Usuario");
                    System.out.println("2) Contraseña");
                    System.out.println("3) Rol");
                    System.out.println("Para cancelar seleccione cualquier numero");

                    int opcion = scanner.nextInt();
                    scanner.nextLine(); 

                    switch (opcion) {
                        case 1:
                            System.out.println("Ingrese el nuevo usuario:");
                            String nuevoNombre = scanner.nextLine();
                            empleado.setUsuario(nuevoNombre);
                            break;
                        case 2:
                            String contraseña;
                            System.out.println("Ingrese la nueva contraseña:");
                            do{ 
                                System.out.println("Ingrese una contraseña para el empleado:");
                                System.out.println("Minimo 6 digitos");
                                contraseña = scanner.nextLine();
                                if(contraseña.length() < 6){
                                    System.out.println("Ingrese una contraseña válida");
                                }
                            }while(contraseña.length() < 6);
                            empleado.setContraseña(contraseña);
                            break;
                        case 3:
                            System.out.println("Ingrese el nuevo rol:");
                            String nuevoRol = scanner.nextLine();
                            empleado.setRol(nuevoRol);
                            break;
                        default:
                            System.out.println("Opción no válida.");
                            break;
                    }
                    System.out.println("Empleado modificado con éxito.");
                }
            }
            if (!encontrado) {
                System.out.println("No se encontró ningún empleado con el nombre " + nombreEmpleado);
            }
        } catch (InputMismatchException e) {
            System.out.println("Ingrese un número");
        } 
    }

    public void verEmpleados(){
        if(empleados.size() == 0) {
            System.out.println("No hay empleados registrados.");
        } else {
            System.out.println("Lista de empleados:");
            for (int i = 0; i < empleados.size(); i++) {
                Empleado empleado = empleados.get(i);
                System.out.println("------------------------------");
                System.out.println("Nombre: " + empleado.getUsuario() + ", Rol: " + empleado.getRol());
            }
        }
    }

    public void buscarEmpleado() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el nombre del empleado que desea buscar:");
        String nombreEmpleado = scanner.nextLine();
        boolean encontrado = false;
        for (int i = 0; i < empleados.size(); i++) {
            Empleado empleado = empleados.get(i);
            if (empleado.getUsuario().equals(nombreEmpleado)) {
                encontrado = true;
                System.out.println("Empleado encontrado:");
                System.out.println("Nombre: " + empleado.getUsuario());
                System.out.println("Contraseña: " + empleado.getContraseña());
                System.out.println("Rol: " + empleado.getRol());
            }
        }
        if (!encontrado) {
            System.out.println("No se encontró ningún empleado con el nombre " + nombreEmpleado);        
        }
    }
  
    public void eliminarEmpleado() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el nombre del empleado que desea eliminar:");
        String nombreEmpleado = scanner.nextLine();
        boolean encontrado = false;
        for (int i = 0; i < empleados.size(); i++) {
            Empleado empleado = empleados.get(i);
            if (empleado.getUsuario().equals(nombreEmpleado)) {
                encontrado = true;
                if (empleado.getRol() == "Empleado") {                    
                    empleados.remove(empleado);
                    System.out.println("Empleado '" + nombreEmpleado + "' eliminado con éxito.");
                }else{
                    System.out.println("Error. El administrador no puede ser eliminado");
                }
            }
        }
        if (!encontrado) {
            System.out.println("No se encontró ningún empleado con el nombre " + nombreEmpleado);
        }
    }
}