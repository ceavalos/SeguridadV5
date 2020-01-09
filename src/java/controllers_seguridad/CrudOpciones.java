
package controllers_seguridad;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import modelos_seguridad.Opciones;
import persistenciaSeguridad_ejb.OpcionesFacadeLocal;

@Named
@SessionScoped
public class CrudOpciones implements Serializable{

  @EJB
  private OpcionesFacadeLocal empresaEJB;

  private List<Opciones> empresas;  
  
  @Inject  
  private Opciones empresa;

  
  @PostConstruct
  private void init(){
      //empresa  = new Empresa();
      empresas =  empresaEJB.findAll();
  }

    public List<Opciones> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<Opciones> empresas) {
        this.empresas = empresas;
    }

    public Opciones getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Opciones empresa) {
        this.empresa = empresa;
    }
          
     
     public void asignar(Opciones empresa){
         System.out.println("Asignar empresa");         
        this.empresa = empresa;
        
    }
  
   public void update(){        
          //System.out.println("id_NombreEmpresa: " + this.empresa.getNombre());
          empresaEJB.edit(empresa);
                
    }
   
   public void prepareCreate(){
       //System.out.println("Preparando empresa");
       this.empresa = new Opciones();     
       
       //this.empresa =  null;
   }
   
   public void almacenar(){
      // System.out.println("Creando empresa");
       empresaEJB.create(this.empresa);
       empresas =  empresaEJB.findAll();
   }
  
  public void eliminar(Opciones empresa){
      System.out.println("Eliminando registro id_empresa: "+ empresa.getIdEmpresa());
      empresaEJB.remove(empresa);
      empresas =  empresaEJB.findAll();
      
  }
  
}
