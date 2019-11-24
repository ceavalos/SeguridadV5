
package controllers;



import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelos.Opciones;
import modelos.Usuarios;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import persistencia_ejb.OpcionesFacadeLocal;


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
        
    
    public String nombreusuario(){
        Usuarios us = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarios");
        String nombre = us.getNombre();
        return nombre;
    }
    
    public String codigousuario(){
        Usuarios us = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarios");
        String codigo = us.getCodUsuario();
        return codigo;
    }
    public String empresausuario(){
        Usuarios us = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarios");
        String nombre =  us.getIdEmpresa().getIdEmpresa()+" - "+ us.getIdEmpresa().getNombre();
        return nombre;
    }
    
    public void establecerPermisos(){
       Usuarios us = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarios");
        //System.out.println("Empresa dentro de MenuController establecerPermisos "+us.getIdEmpresa().getIdEmpresa());      
        
        for (Opciones m : lista){
         
           if(m.getTipo().equals("S") && m.getIdEmpresa().getIdEmpresa().equals(us.getIdEmpresa().getIdEmpresa())){
               //System.out.println("Dentro del looop empresa= "+m.getIdEmpresa().getIdEmpresa());     
              DefaultSubMenu firstSubmenu = new DefaultSubMenu(m.getDescOpcion());  
              
              for(Opciones i : lista){
               Opciones submenu = i.getCodigoSubmenu();
               
               if(submenu != null){
                   if (submenu.getIdOpcion() == m.getIdOpcion() && us.getEstado().equals("A") && m.getIdEmpresa().getIdEmpresa().equals(us.getIdEmpresa().getIdEmpresa()) ){
                       DefaultMenuItem item = new DefaultMenuItem (i.getDescOpcion());
                       item.setUrl(i.getUrl());
                       firstSubmenu.addElement(item);
                   }                                      
                 }               
               }    
              model.addElement(firstSubmenu); 
           }else if(m.getCodigoSubmenu() == null && m.getTipo().equals("I") && m.getIdEmpresa().getIdEmpresa().equals(us.getIdEmpresa().getIdEmpresa())){
               //System.out.println("Dentro del else empresa= "+m.getIdEmpresa().getIdEmpresa());     
               DefaultMenuItem item = new DefaultMenuItem (m.getDescOpcion());
               item.setUrl(m.getUrl());
               model.addElement(item);
               
           }                     
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
