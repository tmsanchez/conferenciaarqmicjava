package mx.uaemtex.ico.erp.cliente.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import mx.uaemtex.ico.erp.cliente.model.Cliente;
import mx.uaemtex.ico.erp.cliente.repository.ClienteRepository;

/**
 *
 * @author Tmsanchez
 */
@Stateless
public class ClienteService {

    @Inject
    private ClienteRepository clienteRepository;

    public Cliente guardar(Cliente cliente) {
        if (cliente.getIdcliente() == null) {
            clienteRepository.save(cliente);
        } else {
            clienteRepository.update(cliente);
        }

        return cliente;
    }

    public Cliente findById(Integer idCliente) {
        return clienteRepository.findOne(idCliente);
    }

    public List<Cliente> getClientes() {
        return clienteRepository.findAll();
    }

}
