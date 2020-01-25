
package controllers_seguridad;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import javax.inject.Inject;
import javax.inject.Named;
import modelos_seguridad.Empresa;
import persistenciaSeguridad_ejb.EmpresaFacadeLocal;


@Named
@ViewScoped
public class EmpresaController implements Serializable{
    
    @EJB
    EmpresaFacadeLocal empresaEJB;
    
    @Inject
    Empresa empresa;
    
  
    List<Empresa> listado;

    public List<Empresa> getListado() {
        return listado;
    }

    public void setListado(List<Empresa> listado) {
        this.listado = listado;
    }
    
    @PostConstruct
    public void init(){
       // empresa = new Empresa();
        listado = empresaEJB.findAll();
    }
    
    
    public void registrar(){
        try{
            System.out.println("Salida de registrar " );
            empresaEJB.create(empresa);
        }catch(Exception e){
            //mensaje error
            System.out.println("Salida Error");
        }
    }
        
     public void valorar(){              
       int i = 0;             
     }
    

    public Empresa getEmpresa() {
        return empresa;
}

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
     
    //@PostConstruct
    public void llenartabla(){
        listado = empresaEJB.findAll();
    }
    
}
