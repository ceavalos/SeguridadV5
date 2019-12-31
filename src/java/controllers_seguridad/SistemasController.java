package controllers_seguridad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelos_seguridad.Sistemas;
import modelos_seguridad.Usuarios;
import persistenciaSeguridad_ejb.SistemasFacadeLocal;

@Named
@ViewScoped
public class SistemasController implements Serializable{

@EJB
private SistemasFacadeLocal sistemas_EJB;    

private List<Sistemas> sistemas ;

    public List<Sistemas> getSistemas() {
        return sistemas;
    }

    public void setSistemas(List<Sistemas> sistemas) {
        this.sistemas = sistemas;
    }
        
@PostConstruct
public void init(){
    ValidaModulos();
}
    
public void ValidaModulos(){
   List<Sistemas> modulos = sistemas_EJB.findAll();   
   Usuarios us=  (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarios");
   sistemas = new ArrayList<Sistemas>();
   
   for (Sistemas m: modulos){
       if( m.getIdEmpresa().getIdEmpresa().equals(us.getIdEmpresa().getIdEmpresa()) && m.getEstado().equals("A") ){
           //System.out.println("Estamos dentro de SistemasController - valdar");
           sistemas.add(m);
       }
   }   
}

public void almacenamodulo(Sistemas modulo){
    System.out.println("Almacenando la variable global del modulo " + modulo.getNombreSistema());
    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("modulo", modulo);
}

 }
