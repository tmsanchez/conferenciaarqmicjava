package mx.uaemtex.ico.producto.service;

import mx.uaemtex.ico.producto.model.Producto;
import mx.uaemtex.ico.producto.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;


    @Override
    public Producto crear(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public List<Producto> getProductosPorNombre(String nombreProducto) {
        if (nombreProducto != null && ! nombreProducto.isEmpty()) {
            return productoRepository.findByNombreProductoContaining(nombreProducto);
        } else {
            return productoRepository.findAll();
        }
    }

    @Override
    public Producto getProductoById(Integer idProducto) {
        return productoRepository.getOne(idProducto);
    }
}
