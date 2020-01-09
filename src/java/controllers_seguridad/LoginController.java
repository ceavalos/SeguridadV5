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
import modelos_seguridad.Roles;
import modelos_seguridad.Usuarios;
import modelos_seguridad.UsuariosRoles;
import persistenciaSeguridad_ejb.EmpresaFacadeLocal;
import persistenciaSeguridad_ejb.RolesFacadeLocal;
import persistenciaSeguridad_ejb.UsuariosFacadeLocal;
import persistenciaSeguridad_ejb.UsuariosRolesFacadeLocal;

@Named
@ViewScoped
public class LoginController implements Serializable{
    
    @EJB
    UsuariosFacadeLocal usuario_EJB; 
    @EJB
    EmpresaFacadeLocal Empresa_EJB;
    @EJB
    RolesFacadeLocal roles_EJB;
    @EJB
    UsuariosRolesFacadeLocal user_rolEJB;
    
    
    private Usuarios usuario;
    
    private Empresa emp;    
    private List<Empresa> empresaList;

    private Roles role;
    private List<Roles> rolesList;
    
    
    @PostConstruct
    public void init(){
        usuario    = new Usuarios();           
        emp        = new Empresa();       
        role      = new Roles();
        empresaList= new ArrayList<Empresa>();
        rolesList  = new ArrayList<Roles>();
        //buscaEmpresa();    
        //populateRoles();
    }

    public Roles getRoles() {
        return role;
    }

    public void setRoles(Roles roles) {
        this.role = roles;
    }

    public List<Roles> getRolesList() {
        return rolesList;
    }

    public void setRolesList(List<Roles> rolesList) {
        this.rolesList = rolesList;
    }

   

    public Empresa getEmp() {
        return emp;
    }

    public void setEmp(Empresa emp) {
        this.emp = emp;
    }

    
    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public List<Empresa> getEmpresaList() {
        return empresaList;
    }

    public void setEmpresaList(List<Empresa> empresaList) {
        this.empresaList = empresaList;
    }

    
    public String iniciarSesion(){
        Usuarios us; 
        String  redireccion = null;
        Roles rolid = roles_EJB.find(this.role.getIdRol());
        //System.out.println("Estamos en iniciar Sesion de LoginController");
        try {
          //us = usuario_EJB.UsuarioIniciarSesion(usuario);
          us = usuario_EJB.UsuarioIniciarSesioneEmp(usuario, emp);
         if (us != null){
             //System.out.println("Entramos a LoginController.iniciarSesion metodovalidar empresa= " + us.getIdEmpresa().getIdEmpresa());
             FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarios", us);
             //System.out.println("Almacenando la variable del rol " + rolid.getNombreRol() );
             FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("rol", rolid);
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
        empresaList = Empresa_EJB.findAll();
        return empresaList;
    }
    
    public List<Empresa> populateEmpresa(){
        List<Empresa> empresa  = Empresa_EJB.findAll();
        List<Usuarios> usuario = usuario_EJB.findAll();        
        System.out.println("Dentro de populateEmpresa Antes de empresaList.add(null) ");
        empresaList.clear();        
        for (Usuarios u : usuario){
            System.out.println(" dentro del for " + u.getCodUsuario()+ " usuario en variable " + this.usuario.getCodUsuario());
            if(u.getCodUsuario().equals(this.usuario.getCodUsuario()) && u.getEstado().equals("A") ){
                //System.out.println("Dentro del IF -- usurio entra a empresa "+u.getIdEmpresa().getNombre());
                empresaList.add(u.getIdEmpresa());
            }
        }        
        populateRoles();
        return empresaList;
    };
   
    public void populateRoles(){
        List<Usuarios> usuario      = usuario_EJB.findAll();        
        List<Roles>    roles        = roles_EJB.findAll();           
        List<UsuariosRoles> userRol = user_rolEJB.findAll();
        
        rolesList.clear();   
        
        
        //Seleccionando del listado el usuario introducico
        for (Usuarios u : usuario){
            //System.out.println(" dentro del for " + u.getCodUsuario()+ " usuario en variable " + this.usuario.getCodUsuario());
            if(   u.getCodUsuario().equals(this.usuario.getCodUsuario())
                && u.getIdEmpresa().getIdEmpresa().equals(this.emp.getIdEmpresa())
                    ){
                 System.out.println("Entramos a validar Usuario y empresa");
                // Validando que rol tiene asignado ese usuario para la empresa seleccionada
                for (UsuariosRoles userrol : userRol){
                    System.out.println("En el for de roles "+userrol.getIdUserRole() + " "+u.getIdUsuario());
                    if(   userrol.getIdUserRole().equals(u.getIdUsuario())                       
                            ){
                        
                     //Asignando el rol del usuario para la empresa seleccionada
                     System.out.println("Entramos a validar Rol y empresa... solo falta asignar "+userrol.getIdEmpresa().getIdEmpresa() + " rol " +userrol.getIdRol());
                     for (Roles r: roles){
                         System.out.println("Dentro del loop de roles " + r.getIdEmpresa().getIdEmpresa() + " rol = "+r.getIdRol() ) ;
                         if(   r.getIdEmpresa().getIdEmpresa().equals(userrol.getIdEmpresa().getIdEmpresa() )
                            && r.getIdRol().equals(userrol.getIdRol().getIdRol()) && r.getEstado().equals("A")
                                 ){
                             System.out.println("Correr Desnudos por la casa Cesar Cansino!! ");
                             rolesList.add(r);
                         }
                     }   
                        


                    }
                    
                }
            }
        }
        
        
    }    
    
    
}
