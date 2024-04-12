public abstract class Producto {
    protected String nombre;
    protected float precio;
    protected String tipo;
    protected int cantidadProducto;

    public Producto(){}

    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }
    public int getCantidadProducto() {
        return cantidadProducto;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getNombre() {
        return nombre;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
    public float getPrecio() {
        return precio;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getTipo() {
        return tipo;
    }
    
    public abstract void ingresarDescripcion();

    public abstract void mostrarDetalles();
}
