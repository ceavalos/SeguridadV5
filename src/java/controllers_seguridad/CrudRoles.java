package controllers_seguridad;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import modelos_seguridad.Roles;
import persistenciaSeguridad_ejb.RolesFacadeLocal;

@Named(value = "crudRoles")
@ViewScoped
public class CrudRoles implements Serializable {

    @EJB
    RolesFacadeLocal sistemas_EJB;
                
    List<Roles> sistemas;
   
    @Inject
    Roles sistema;
    
    @PostConstruct
    public void init(){
        sistemas = sistemas_EJB.findAll();    
    }

    public List<Roles> getSistemas() {
        return sistemas;
    }

    public void setSistemas(List<Roles> sistemas) {
        this.sistemas = sistemas;
    }

    public Roles getSistema() {
        return sistema;
    }

    public void setSistema(Roles sistema) {
        this.sistema = sistema;
    }


       
    public void prepareCreate(){
       //System.out.println("Preparando empresa");
       this.sistema = new Roles();     
   }    

 public void almacenar(){
       //System.out.println("Almacenando usuario");       
       sistemas_EJB.create(this.sistema);
       sistemas =  sistemas_EJB.findAll();
   }
 
  public void update(){
      // System.out.println("Creando empresa");
       sistemas_EJB.edit(this.sistema);
      
   }
  public void eliminar(Roles usuario){
      sistemas_EJB.remove(usuario);
      sistemas =  sistemas_EJB.findAll();
      
  }   
  public void asignar(Roles usuario){
         //System.out.println("Asignar empresa");         
        this.sistema = usuario;
        
    }
  
  public void rolesCia(Integer cia){
     this.sistemas.clear();
     this.sistemas = sistemas_EJB.findrolesCia(cia);
  }
}
