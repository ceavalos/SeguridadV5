package controllers_seguridad;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import modelos_seguridad.UsuariosRoles;
import persistenciaSeguridad_ejb.UsuariosRolesFacadeLocal;

@Named
@ViewScoped
public class CrudUsuariosRoles implements Serializable {

    @EJB
    UsuariosRolesFacadeLocal usuarios_EJB;
                
    List<UsuariosRoles> usuarios;
   
    @Inject
    UsuariosRoles usuario;
    
    @PostConstruct
    public void init(){
        usuarios = usuarios_EJB.findAll();    
    }


    public List<UsuariosRoles> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<UsuariosRoles> usuarios) {
        this.usuarios = usuarios;
    }

    public UsuariosRoles getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuariosRoles usuario) {
        this.usuario = usuario;
    }
    
    public void prepareCreate(){
       //System.out.println("Preparando empresa");
       this.usuario = new UsuariosRoles();     
   }    

 public void almacenar(){
       //System.out.println("Almacenando usuario");       
       usuarios_EJB.create(this.usuario);
       usuarios =  usuarios_EJB.findAll();
   }
 
  public void update(){
      // System.out.println("Creando empresa");
       usuarios_EJB.edit(this.usuario);
      
   }
  public void eliminar(UsuariosRoles usuario){
      usuarios_EJB.remove(usuario);
      usuarios =  usuarios_EJB.findAll();
      
  }   
  public void asignar(UsuariosRoles usuario){
         //System.out.println("Asignar empresa");         
        this.usuario = usuario;
        
    }
}
