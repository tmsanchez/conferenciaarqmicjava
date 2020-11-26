package mx.uaemtex.ico.erp.cliente.repository;

import mx.uaemtex.ico.erp.cliente.model.Cliente;
import mx.uaemtex.ico.erp.common.repository.JpaRepository;

/**
 *
 * @author Tmsanchez
 */
public class ClienteRepository extends JpaRepository<Cliente, Integer>{
    
    public ClienteRepository() {
        super(Cliente.class);
    }
}
