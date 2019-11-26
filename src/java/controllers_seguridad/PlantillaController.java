
package controllers_seguridad;

import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelos_seguridad.Usuarios;

@Named
@ViewScoped
public class PlantillaController implements Serializable{
    
    public void verificarSesion(){
        try {
         Usuarios us=  (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarios");
         if(us == null){
             FacesContext.getCurrentInstance().getExternalContext().redirect("./../../permisos.xhtml");
         }
        } catch (Exception e) {
            //guardar Log para intentos de sesion no validos 
        }
    }
    
}
