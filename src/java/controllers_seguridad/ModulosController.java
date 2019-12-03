
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
public class ModulosController implements Serializable{

@EJB
private SistemasFacadeLocal sistemas_ejb;

private List<Sistemas> modulos ;

@PostConstruct
private void init(){    
    //BuscaModulos();
    modulos = sistemas_ejb.findAll();
    
}

private void BuscaModulos(){
    //List<Sistemas> lista = sistemas_ejb.findAll();    
    //modulos = sistemas_ejb.findAll();
    /*List<Sistemas> lista =  new ArrayList<>();
    lista =  sistemas_ejb.findAll();    
    Usuarios us = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarios");
        
    for (Sistemas k : lista){
        if (k.getIdEmpresa().getIdEmpresa().equals(us.getIdEmpresa().getIdEmpresa()) && k.getEstado().equals("A")  ){
            modulos.add(k);
        }
    }*/    
} 

    public List<Sistemas> getModulos() {
        return modulos;
    }

    public void setModulos(List<Sistemas> modulos) {
        this.modulos = modulos;
    }

   
 
}
