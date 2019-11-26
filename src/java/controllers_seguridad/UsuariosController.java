
package controllers_seguridad;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelos_seguridad.Empresa;
import modelos_seguridad.Usuarios;
import persistenciaSeguridad_ejb.UsuariosFacadeLocal;

@Named
@ViewScoped        
public class UsuariosController implements Serializable{
    
    @EJB
    UsuariosFacadeLocal usuarios_EJB;
    Usuarios usuarios;
    Empresa empresa;
    
    List<Usuarios> usuariosList;

    public List<Usuarios> getUsuariosList() {
        return usuariosList;
    }

    public void setUsuariosList(List<Usuarios> usuariosList) {
        this.usuariosList = usuariosList;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
    
    
    
    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }
    
 public void registrar(){
        try{
            System.out.println("Salida de registrar " );

            usuarios_EJB.create(usuarios);
        }catch(Exception e){
            //mensaje error
            System.out.println("Salida Error");
        }
    }
 
    @PostConstruct
    public void init(){
        usuarios = new Usuarios();        
        
        usuariosList =  usuarios_EJB.findAll();
    }
    
    
}
