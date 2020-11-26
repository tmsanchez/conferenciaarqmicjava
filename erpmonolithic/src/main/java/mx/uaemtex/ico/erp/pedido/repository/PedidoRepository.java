package mx.uaemtex.ico.erp.pedido.repository;

import java.util.List;
import mx.uaemtex.ico.erp.common.repository.JpaRepository;
import mx.uaemtex.ico.erp.pedido.model.Pedido;

/**
 *
 * @author Tmsanchez
 */
public class PedidoRepository extends JpaRepository<Pedido, Integer> {

    public PedidoRepository() {
        super(Pedido.class);
    }

    public List<Pedido> findPedidosByCliente(String nombreCliente) {
        StringBuilder consulta = new StringBuilder("select o from Pedido o ");

        if (!nombreCliente.isEmpty()) {
            consulta.append(" where o.idcliente.nombre like ?1 order by o.idpedido ");
            return getList(consulta.toString(), new Object[]{"%" + nombreCliente + "%"});
        } else {
            consulta.append(" order by o.idpedido ");
            return getList(consulta.toString(), new Object[0]);
        }

    }
}
