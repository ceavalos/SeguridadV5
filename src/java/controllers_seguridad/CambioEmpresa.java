
package controllers_seguridad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import modelos_seguridad.Empresa;
import modelos_seguridad.Usuarios;
import modelos_seguridad.UsuariosRoles;
import persistenciaSeguridad_ejb.UsuariosFacadeLocal;

@Named
@ViewScoped
public class CambioEmpresa implements Serializable{
    
    @EJB
    UsuariosFacadeLocal usuario_EJB;   
       
    private Usuarios usuario;

    private Empresa emp;
    
    private List<Usuarios> userList;
    private List<Empresa> empresaList = new  ArrayList<Empresa>();

    public List<Empresa> getEmpresaList() {
        return empresaList;
    }

    public void setEmpresaList(List<Empresa> empresaList) {
        this.empresaList = empresaList;
    }
    
    public UsuariosRoles getUsuarioroles() {
        return usuarioroles;
    }

    public Empresa getEmp() {
        return emp;
    }

    public void setEmp(Empresa emp) {
        this.emp = emp;
    }
  
    public void setUsuarioroles(UsuariosRoles usuarioroles) {
        this.usuarioroles = usuarioroles;
    }
    
    private UsuariosRoles usuarioroles;

            
    @PostConstruct
    public void init(){        
        usuario    = new Usuarios();                         
        emp        = new Empresa();        
        buscaEmpresa();                
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    
    public String iniciarSesion(){
        //FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        
        Usuarios us; 
        String  redireccion = null;
                
        try {
          us = usuario_EJB.UsuarioIniciarSesioneEmp(usuario, emp);
         if (us != null){
             //System.out.println("Entramos a validar");
             FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarios", us);
             redireccion = "/protegido/principal?faces-redirect=true"; 
         }else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Aviso","Credenciales no validas"));    
         }
          
        } catch (Exception e) {
            //  throw e;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Aviso","Error"));
        }
        return redireccion;
    }
    
     public List<Empresa> buscaEmpresa(){
      userList=usuario_EJB.findAll();
      Usuarios us = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarios");
       
       for(Usuarios m : userList ){
           
           if (m.getCodUsuario().equals(us.getCodUsuario()) && m.getEstado().equals("A") ){
               empresaList.add(m.getIdEmpresa());
               //System.out.println("Estamos dentro  del if del loop " + m.getIdEmpresa().getIdEmpresa() );
           }
       }       
        return empresaList;
    }
    
    public String cancelar(){         
        String redireccion = "/protegido/principal?faces-redirect=true"; 
        return redireccion ;
    }

    
}
