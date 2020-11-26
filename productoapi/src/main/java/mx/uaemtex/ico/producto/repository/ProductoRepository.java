package mx.uaemtex.ico.producto.repository;

import mx.uaemtex.ico.producto.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    List<Producto> findByNombreProductoContaining(String nombreProducto);

}
