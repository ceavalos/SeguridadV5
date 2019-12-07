
package controllers_seguridad;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelos_seguridad.Roles;
import modelos_seguridad.Sistemas;
import modelos_seguridad.Usuarios;

@Named
//@SessionScoped
@ViewScoped
public class NombreSesiones implements Serializable{
      
    private String modulo = new String();
    private String nombrerol = new String();

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }
    
    @PostConstruct
    public void init(){
       // nombrerol();
      //  nombremodulo();
        
    }

    public String getNombrerol() {
        return nombrerol;
    }

    public void setNombrerol(String nombrerol) {
        this.nombrerol = nombrerol;
    }
    public String nombreusuario(){
        Usuarios us = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarios");
        String nombre = us.getNombre();
        return nombre;
    }
    
    public String codigousuario(){
        Usuarios us = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarios");
        String codigo = us.getCodUsuario();
        return codigo;
    }
    public String empresausuario(){
        Usuarios us = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarios");
        String nombre =  us.getIdEmpresa().getIdEmpresa()+" - "+ us.getIdEmpresa().getNombre();
        return nombre;
    }
    
    public String nombrerol(){
        Roles rol = (Roles) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("rol");               
        nombrerol  =  rol.getNombreRol();
        return nombrerol;
    }
    
    public String nombremodulo(){
        Sistemas sis = (Sistemas) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("modulo");         
        modulo =  sis.getNombreSistema();
        return  modulo;
        
    }
}
