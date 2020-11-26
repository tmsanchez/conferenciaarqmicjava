package mx.uaemtex.ico.erp.producto.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import mx.uaemtex.ico.erp.producto.model.Producto;
import mx.uaemtex.ico.erp.producto.repository.ProductoRepository;

/**
 *
 * @author Tmsanchez
 */
@Stateless
public class ProductoService {

    @Inject
    private ProductoRepository productoRepository;

    public Producto guardar(Producto producto) {
        if (producto.getIdproducto()== null) {
            productoRepository.save(producto);
        } else {
            productoRepository.update(producto);
        }

        return producto;
    }

    public Producto findById(Integer idProducto) {
        return productoRepository.findOne(idProducto);
    }

    public List<Producto> getProductos() {
        return productoRepository.findAll();
    }

}
