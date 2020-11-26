package mx.uaemtex.ico.erp.producto.controller;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import mx.uaemtex.ico.erp.producto.model.Producto;
import mx.uaemtex.ico.erp.producto.service.ProductoService;

/**
 *
 * @author Tmsanchez
 */
@SessionScoped
@Named
public class ProductoController implements Serializable{
    
    @Inject
    private ProductoService productoService;
    
    private Producto producto;
    
    private List<Producto> productos;

    public void onListar(ActionEvent actionEvent) {
        obtenerProductos();
    }
    
    private void obtenerProductos() {
        productos = productoService.getProductos();
    }
    
    public String onAgregar() {
        producto = new Producto();
        return "productoForm";
    }
    
    public String onGuardar() {
        productoService.guardar(producto);
        obtenerProductos();
        return "producto";
    }
    
    public String onEditar(Producto producto) {
        this.producto = producto;
        return "productoForm";
    }
    
    public String onRegresar() {
        obtenerProductos();
        return "producto";
    }
    
    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
    
    
}
