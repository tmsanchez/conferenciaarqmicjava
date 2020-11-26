package mx.uaemtex.ico.erp.pedido.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import mx.uaemtex.ico.erp.cliente.model.Cliente;
import mx.uaemtex.ico.erp.cliente.service.ClienteService;
import mx.uaemtex.ico.erp.common.controller.JsfUtil;
import mx.uaemtex.ico.erp.pedido.model.Pedido;
import mx.uaemtex.ico.erp.pedido.service.PedidoService;
import mx.uaemtex.ico.erp.producto.model.Producto;
import mx.uaemtex.ico.erp.producto.service.ProductoService;

/**
 *
 * @author Tmsanchez
 */
@ViewScoped
@Named
public class NuevoPedidoController implements Serializable {

    @Inject
    private PedidoService pedidoService;
    
    @Inject
    private ClienteService clienteService;
    
    @Inject
    private ProductoService productoService;
    
    private Pedido pedido;

    private BigDecimal cantidad;

    private Producto producto;
    
    public NuevoPedidoController() {
        
    }
    
    @PostConstruct
    public void inicializaCaptura() {
        pedido = new Pedido();
        cantidad = BigDecimal.ONE;
        producto = new Producto();
    }
    
    public void onAgregaProducto(ActionEvent actionEvent) {
        if (cantidad.compareTo(BigDecimal.ZERO) < -1 ) {
            JsfUtil.addErrorMessage("Cantidad Inválida",
                    "La cantidad tiene que ser mayor a Cero");
            return;
        }
        
        if (producto == null) {
            JsfUtil.addErrorMessage("Producto inválido", 
                    "Debe seleccionar un producto");
            return;
            
        }

        pedido.agregaProducto(producto, cantidad);

        JsfUtil.addMessage("Producto agregado", 
                "El producto se agregó correctamente");
    }
    
    public void onGuardar(ActionEvent actionEvent) {
        if (pedido.getTotal().compareTo(BigDecimal.ZERO) == 0) {
            JsfUtil.addErrorMessage("No hay productos",
                    "El pedido no contiene productos");
            return;
        } 
        
        pedidoService.creaPedido(pedido);
        
        JsfUtil.addMessage("Pedido registrado", 
                "El Pedido se registró correctamente");
        
        inicializaCaptura();
    }
    
    public String onSalir() {
        return "/index";
    }

    public List<Cliente> getClientes() {
        return clienteService.getClientes();
    }
    
    public List<Producto> getProductos() {
        return productoService.getProductos();
    }
    
    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    
}
