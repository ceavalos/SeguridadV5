
package controllers_seguridad;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import modelos_seguridad.Empresa;
import persistenciaSeguridad_ejb.EmpresaFacade;
import persistenciaSeguridad_ejb.EmpresaFacadeLocal;

@Named
@SessionScoped
public class CrudEmpresa implements Serializable{

  @EJB
  private EmpresaFacadeLocal empresaEJB;

  private List<Empresa> empresas;  
  
 @Inject  
  private  Empresa empresa;

  @PostConstruct
  private void init(){
      //empresa  = new Empresa();
      empresas =  empresaEJB.findAll();
  }

    public List<Empresa> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
          
     
     public void asignar(Empresa empresa){
         System.out.println("Asignar empresa");         
        this.empresa = empresa;
        
    }
  
   public void update(){        
          //System.out.println("id_NombreEmpresa: " + this.empresa.getNombre());
          empresaEJB.edit(empresa);
                
    }
   
   public void prepareCreate(){
       //System.out.println("Preparando empresa");
       this.empresa = new Empresa();     
       empresa.setDireccion("");
       empresa.setNombre("");
       //this.empresa =  null;
   }
   
   public void almacenar(){
      // System.out.println("Creando empresa");
       empresaEJB.create(this.empresa);
       empresas =  empresaEJB.findAll();
   }
  
  public void eliminar(Empresa empresa){
      System.out.println("Eliminando registro id_empresa: "+ empresa.getIdEmpresa());
      empresaEJB.remove(empresa);
      empresas =  empresaEJB.findAll();
      
  }
  
}
