package controllers_seguridad;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import modelos_seguridad.Sistemas;
import persistenciaSeguridad_ejb.SistemasFacadeLocal;

@Named(value = "crudSistemas")
@ViewScoped
public class CrudSistemas implements Serializable {

    @EJB
    SistemasFacadeLocal sistemas_EJB;
                
    List<Sistemas> sistemas;
   
    @Inject
    Sistemas sistema;
    
    @PostConstruct
    public void init(){
        sistemas = sistemas_EJB.findAll();    
    }

    public List<Sistemas> getSistemas() {
        return sistemas;
    }

    public void setSistemas(List<Sistemas> sistemas) {
        this.sistemas = sistemas;
    }

    public Sistemas getSistema() {
        return sistema;
    }

    public void setSistema(Sistemas sistema) {
        this.sistema = sistema;
    }


       
    public void prepareCreate(){
       //System.out.println("Preparando empresa");
       this.sistema = new Sistemas();     
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
  public void eliminar(Sistemas usuario){
      sistemas_EJB.remove(usuario);
      sistemas =  sistemas_EJB.findAll();
      
  }   
  public void asignar(Sistemas usuario){
         //System.out.println("Asignar empresa");         
        this.sistema = usuario;
        
    }
}
