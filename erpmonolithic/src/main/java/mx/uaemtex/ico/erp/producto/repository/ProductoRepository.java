package mx.uaemtex.ico.erp.producto.repository;

import mx.uaemtex.ico.erp.common.repository.JpaRepository;
import mx.uaemtex.ico.erp.producto.model.Producto;

/**
 *
 * @author Tmsanchez
 */
public class ProductoRepository extends JpaRepository<Producto, Integer> {
    
    public ProductoRepository() {
        super(Producto.class);
    }
    
}
