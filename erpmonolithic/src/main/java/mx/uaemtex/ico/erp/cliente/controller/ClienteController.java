package mx.uaemtex.ico.erp.cliente.controller;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import mx.uaemtex.ico.erp.cliente.model.Cliente;
import mx.uaemtex.ico.erp.cliente.service.ClienteService;

/**
 *
 * @author Tmsanchez
 */
@SessionScoped
@Named
public class ClienteController implements Serializable{
    
    @Inject
    private ClienteService clienteService;
    
    private Cliente cliente;
    
    private List<Cliente> clientes;

    public void onListar(ActionEvent actionEvent) {
        obtenerClientes();
    }
    
    private void obtenerClientes() {
        clientes = clienteService.getClientes();
    }
    
    public String onAgregar() {
        cliente = new Cliente();
        return "clienteForm";
    }
    
    public String onGuardar() {
        clienteService.guardar(cliente);
        obtenerClientes();
        return "cliente";
    }
    
    public String onEditar(Cliente cliente) {
        this.cliente = cliente;
        return "clienteForm";
    }
    
    public String onRegresar() {
        obtenerClientes();
        return "cliente";
    }
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
    
    
}
