package mx.uaemtex.ico.erp.pedido.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import mx.uaemtex.ico.erp.pedido.model.Pedido;
import mx.uaemtex.ico.erp.pedido.service.PedidoService;

/**
 *
 * @author Tmsanchez
 */
@ViewScoped
@Named
public class ReportePedidosController implements Serializable {

    @Inject
    private PedidoService pedidoService;

    private String nombreCliente;

    private List<Pedido> pedidos;

    private BigDecimal totalGeneral;

    public void obtenPedidos(ActionEvent actionEvent) {
        pedidos = pedidoService.reportePedidos(nombreCliente);

        totalGeneral = BigDecimal.ZERO;
        pedidos.forEach(pedido -> {
            totalGeneral = totalGeneral.add(pedido.getTotal());
        });
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public BigDecimal getTotalGeneral() {
        return totalGeneral;
    }

    public void setTotalGeneral(BigDecimal totalGeneral) {
        this.totalGeneral = totalGeneral;
    }

}
