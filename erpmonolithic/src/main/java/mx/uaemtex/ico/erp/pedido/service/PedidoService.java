package mx.uaemtex.ico.erp.pedido.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import mx.uaemtex.ico.erp.pedido.model.Pedido;
import mx.uaemtex.ico.erp.pedido.repository.PedidoRepository;

/**
 *
 * @author Tmsanchez
 */
@Stateless
public class PedidoService {
    
    @Inject
    private PedidoRepository pedidoRepository;
    
    public Pedido creaPedido(Pedido pedido) {
        pedidoRepository.save(pedido);
        return pedido;
    }
    
    public List<Pedido> reportePedidos(String nombreCliente) {
        return pedidoRepository.findPedidosByCliente(nombreCliente);
    }
    
}
