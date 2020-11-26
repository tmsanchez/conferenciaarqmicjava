package mx.uaemtex.ico.producto.controller;

import mx.uaemtex.ico.producto.model.Producto;
import mx.uaemtex.ico.producto.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;


    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Producto> agregaProducto(
            @RequestBody Producto producto) {
        Producto productoAgregado = productoService.crear(producto);
        return ResponseEntity.ok(productoAgregado);
    }

    @GetMapping(path = "/{idProducto}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Producto> getProductoById(
            @PathVariable("idProducto") Integer idProducto) {
        Producto producto = productoService.getProductoById(idProducto);
        if (producto != null) {
            return ResponseEntity.ok(producto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Producto>> consultaProductos(
            @RequestParam(value = "nombreProducto",required = false) String nombreProducto) {
        List<Producto> productos = productoService.getProductosPorNombre(nombreProducto);

        return ResponseEntity.ok(productos);
    }
}
