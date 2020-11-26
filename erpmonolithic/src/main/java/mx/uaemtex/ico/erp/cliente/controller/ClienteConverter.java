package mx.uaemtex.ico.erp.cliente.controller;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;
import mx.uaemtex.ico.erp.cliente.model.Cliente;
import mx.uaemtex.ico.erp.cliente.service.ClienteService;

/**
 *
 * @author Tmsanchez
 */
@Named 
@FacesConverter (value = "clienteConverter", managed = true)
public class ClienteConverter implements Converter {
    
    @Inject
    private ClienteService clienteService;
    
    @Override 
    public Object getAsObject(FacesContext fc, UIComponent uic, String value ){
        if(value != null  && value.trim().length() > 0 ){
            try {
                return clienteService.findById(Integer.valueOf(value));   
            }catch (NumberFormatException e){
                    throw new ConverterException(
                            new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                    "Error de conversion","No es Cliente valido"));
            }
        }else{
            return null;
        }
        
    }     
    
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object){
        if (object !=null) {
            return String.valueOf(((Cliente) object).getIdcliente());
        }else{
            return null;
        }
    }
    
}
