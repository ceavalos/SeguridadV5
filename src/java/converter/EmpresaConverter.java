package converter;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.convert.FacesConverter;
import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import modelos_seguridad.Empresa;
import persistenciaSeguridad_ejb.EmpresaFacade;
import persistenciaSeguridad_ejb.EmpresaFacadeLocal;

@FacesConverter(value = "empresaConverter")
public class EmpresaConverter implements Converter {

    private EmpresaFacadeLocal ejbFacade;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 ) {
            return null;
        }
        return this.getEjbFacade().find(getKey(value));
    }

    java.lang.Integer getKey(String value) {
        java.lang.Integer key;
        key = Integer.valueOf(value);
        return key;
    }

    String getStringKey(java.lang.Integer value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value);
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof Empresa) {
            Empresa o = (Empresa) object;
            return getStringKey(o.getIdEmpresa());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Empresa.class.getName()});
            return null;
        }
    }

    private EmpresaFacadeLocal getEjbFacade() {
        this.ejbFacade = CDI.current().select(EmpresaFacadeLocal.class).get();
        return this.ejbFacade;
    }
}
