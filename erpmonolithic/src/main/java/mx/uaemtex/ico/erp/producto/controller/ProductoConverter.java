package mx.uaemtex.ico.erp.producto.controller;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;
import mx.uaemtex.ico.erp.producto.model.Producto;
import mx.uaemtex.ico.erp.producto.service.ProductoService;
/**
 *
 * @author Tmsanchez
 */
@Named 
@FacesConverter (value = "productoConverter", managed = true)
public class ProductoConverter implements Converter {
    
    @Inject
    private ProductoService productoService;
    
    @Override 
    public Object getAsObject(FacesContext fc, UIComponent uic, String value ){
        if(value != null  && value.trim().length() > 0 ){
            try {
                return productoService.findById(Integer.valueOf(value));   
            }catch (NumberFormatException e){
                    throw new ConverterException(
                            new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                    "Error de conversion","No es Producto valido"));
            }
        }else{
            return null;
        }
        
    }     
    
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object){
        if (object !=null) {
            return String.valueOf(((Producto) object).getIdproducto());
        }else{
            return null;
        }
    }
    
}
