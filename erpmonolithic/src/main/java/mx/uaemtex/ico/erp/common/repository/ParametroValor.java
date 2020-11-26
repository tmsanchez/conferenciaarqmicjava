package mx.uaemtex.ico.erp.common.repository;

import java.io.Serializable;

/**
 *
 * @author tmsanchez
 */
public class ParametroValor implements Serializable {
    private String nombreParam;
    private Object valor;

    public ParametroValor(String nombreParam, Object valor) {
        this.nombreParam = nombreParam;
        this.valor = valor;
    }

    public String getNombreParam() {
        return nombreParam;
    }

    public void setNombreParam(String nombreParam) {
        this.nombreParam = nombreParam;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }
    
    
}
