package controllers_seguridad;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import modelos_seguridad.Empresa;
import modelos_seguridad.Usuarios;
import persistenciaSeguridad_ejb.EmpresaFacadeLocal;
import persistenciaSeguridad_ejb.UsuariosFacadeLocal;

@Named(value = "crudUsuarios")
@ViewScoped
public class CrudUsuarios implements Serializable {

    @EJB
    UsuariosFacadeLocal usuarios_EJB;
                
    List<Usuarios> usuarios;
   
    @Inject
    Usuarios usuario;
    
    @PostConstruct
    public void init(){
        usuarios = usuarios_EJB.findAll();    
    }


    public List<Usuarios> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuarios> usuarios) {
        this.usuarios = usuarios;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }
    
    public void prepareCreate(){
       //System.out.println("Preparando empresa");
       this.usuario = new Usuarios();     
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
  public void eliminar(Usuarios usuario){
      usuarios_EJB.remove(usuario);
      usuarios =  usuarios_EJB.findAll();
      
  }   
  public void asignar(Usuarios usuario){
         //System.out.println("Asignar empresa");         
        this.usuario = usuario;
        
    }
}
