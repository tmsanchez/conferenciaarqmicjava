package mx.uaemtex.ico.producto.service;

import mx.uaemtex.ico.producto.model.Producto;

import java.util.List;

public interface ProductoService {

    public Producto crear(Producto producto);

    public List<Producto> getProductosPorNombre(String nombre);

    public Producto getProductoById(Integer idProducto);

}
