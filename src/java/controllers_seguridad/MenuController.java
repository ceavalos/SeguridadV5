
package controllers_seguridad;



import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelos_seguridad.Opciones;
import modelos_seguridad.Sistemas;
import modelos_seguridad.Usuarios;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import persistenciaSeguridad_ejb.OpcionesFacadeLocal;

@Named
@SessionScoped
//@ViewScoped
public class MenuController implements Serializable{
    
    
    @EJB
    private OpcionesFacadeLocal EJBOpciones;
    
    
    private List<Opciones> lista;
    
    private MenuModel model;
    private Usuarios usuario;
    
    @PostConstruct
    public void init(){
        this.listarmenus();
        model = new DefaultMenuModel();
        this.establecerPermisos();
    }
    
    public void listarmenus(){
        try {
            lista = EJBOpciones.buscarAll();
        } catch (Exception e) {
            //mesaje error jsf
        }      
    };
        
    
    
    public void establecerPermisos(){
       Usuarios us = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarios");
       
       Sistemas sis = (Sistemas) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("modulo");              
       System.out.println("Dentro de MenuController establecerPermisos seleccionado " + sis.getNombreSistema());      
       
         //menu de Home
          DefaultMenuItem home = new DefaultMenuItem ("HOME");
          home.setOutcome("/protegido/principal.xhtml");
          home.setIcon("ui-icon-home");
          model.addElement(home);
              
        for (Opciones m : lista){
          if( m.getIdSistema().getIdSistema().equals(sis.getIdSistema())){                        
           if(m.getTipo().equals("S") && m.getIdEmpresa().getIdEmpresa().equals(us.getIdEmpresa().getIdEmpresa())){
               //System.out.println("Dentro del looop empresa= "+m.getIdEmpresa().getIdEmpresa());     
              DefaultSubMenu firstSubmenu = new DefaultSubMenu(m.getDescOpcion());  
              
              
                      
              for(Opciones i : lista){
               Opciones submenu = i.getCodigoSubmenu();
               
               if(submenu != null){
                   if (submenu.getIdOpcion() == m.getIdOpcion() && us.getEstado().equals("A") && m.getIdEmpresa().getIdEmpresa().equals(us.getIdEmpresa().getIdEmpresa()) ){
                       DefaultMenuItem item = new DefaultMenuItem (i.getDescOpcion());
                       //item.setOutcome(i.getUrl());  
                       //item.setUrl(i.getUrl());
                       if (i.getUrl().length()>5){
                          item.setOutcome(i.getUrl());
                       }
                       
                       firstSubmenu.addElement(item);
                   }                                      
                 }               
               }    
              model.addElement(firstSubmenu); 
           }else if(m.getCodigoSubmenu() == null && m.getTipo().equals("I") && m.getIdEmpresa().getIdEmpresa().equals(us.getIdEmpresa().getIdEmpresa())){
               //System.out.println("Dentro del else empresa= "+m.getIdEmpresa().getIdEmpresa());     
               DefaultMenuItem item = new DefaultMenuItem (m.getDescOpcion());
               //item.setUrl(m.getUrl());
               if (m.getUrl().length()>5){
                   item.setOutcome(m.getUrl()); 
               }
               
               model.addElement(item);
               
           }   
          } //if( m.getIdSistema().getIdSistema().equals(sis.getIdSistema()))
        }      
    }
    

    public MenuModel getModel() {
        return model;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }
    
    public void cerrarSesion(){
      FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }
}
