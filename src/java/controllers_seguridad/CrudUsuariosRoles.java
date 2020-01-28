package controllers_seguridad;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.inject.Inject;
import modelos_seguridad.Empresa;
import modelos_seguridad.Roles;
import modelos_seguridad.Usuarios;
import modelos_seguridad.UsuariosRoles;
import persistenciaSeguridad_ejb.EmpresaFacadeLocal;
import persistenciaSeguridad_ejb.RolesFacadeLocal;
import persistenciaSeguridad_ejb.UsuariosFacadeLocal;
import persistenciaSeguridad_ejb.UsuariosRolesFacadeLocal;

@Named
@SessionScoped
public class CrudUsuariosRoles implements Serializable {
     private final static Logger LOGGER = Logger.getLogger("CrudUsuariosRoles");
    
    @EJB
    UsuariosRolesFacadeLocal usuariosRoles_EJB ;
     
    @EJB
    UsuariosFacadeLocal usuarios_EJB;
    
    @EJB
    EmpresaFacadeLocal empresa_EJB;
        
        
    @EJB
    RolesFacadeLocal roles_EJB;
    
    @Inject
    private Empresa empresa;
    @Inject
    private Usuarios usuario;
    @Inject
    private Roles  roles;
    
    List<Usuarios> usuariosList;  
    List<UsuariosRoles> usuariosRolesList;
    List<Roles> rolesList;
    
    @Inject
    UsuariosRoles usuarioRol;
    
    public UsuariosRoles getUsuarioRol() {
        return usuarioRol;
    }

    public void setUsuarioRol(UsuariosRoles usuarioRol) {
        this.usuarioRol = usuarioRol;
    }
    
    @PostConstruct
    public void init(){
        usuariosList = usuarios_EJB.findAll(); 
        //System.out.println("Asignando lista de roles x usuario");
        usuariosRolesList = usuariosRoles_EJB.findAll();
        nombre="";
        //usuario = new UsuariosRoles();
    }
    
    String nombre ;

  

    public void setUsuarios(List<Usuarios> usuarios) {
        this.usuariosList = usuarios;
    }

    
    public void setUsuario(UsuariosRoles usuario) {
        this.usuarioRol = usuario;
    }

    
    public void prepareCreate(){
       //System.out.println("Preparando empresa");
       this.usuarioRol = new UsuariosRoles();     
   }    

 public void almacenar(){
      /* System.out.println("Almacenando UsuariosRoles");   
       System.out.println("Id Empresa "+this.empresa.getIdEmpresa());   
       System.out.println("Id Usuario "+this.usuario.getIdUsuario());   
       System.out.println("Id Rol "+this.roles.getIdRol());  
       */         
              
       this.usuarioRol.setIdEmpresa(this.empresa);
       this.usuarioRol.setIdUsuario(this.usuario);
       this.usuarioRol.setIdRol(this.roles);
       //
       usuariosRoles_EJB.create(this.usuarioRol);
       usuariosRolesList =  usuariosRoles_EJB.findAll();
   }
 
  public void update(){
      this .usuarioRol.setIdEmpresa(this.empresa);
       this.usuarioRol.setIdUsuario(this.usuario);
       this.usuarioRol.setIdRol(this.roles);
    
       System.out.println("Antes del update");
       usuariosRoles_EJB.edit(this.usuarioRol);
       usuariosRolesList =  usuariosRoles_EJB.findAll();
       
       
      
   }
  public void eliminar(UsuariosRoles usuario){
      System.out.println("Codigo de rol a eliminar "+usuario.getIdUserRole());
      usuariosRoles_EJB.remove(usuario);
      usuariosRolesList =  usuariosRoles_EJB.findAll();
      
  }   
  public void asignar(UsuariosRoles usuario){
        System.out.println("Asignar empresa$= "+usuario.getIdEmpresa().getIdEmpresa() );         
        System.out.println("Asignar usuario#= "+usuario.getIdUsuario().getIdUsuario() ); 
        System.out.println("Asignar rol#= "+usuario.getIdRol().getIdRol() ); 
        
        this .usuarioRol.setIdUserRole(usuario.getIdUserRole());
                
        this.empresa.equals(usuario.getIdEmpresa());
        this.usuario.equals(usuario.getIdUsuario());
        this.roles.equals(usuario.getIdRol());
        
        
        this.empresa = empresa_EJB.find( (usuario.getIdEmpresa().getIdEmpresa()));
        this.usuario = usuarios_EJB.find((usuario.getIdUsuario().getIdUsuario()));
        this.roles   = roles_EJB.find( (usuario.getIdRol().getIdRol()));
        
        populateuser();
        populateroles();        
        //
    }
  
  public void populateuser(){     
      System.out.println("Entramos al conroller en populateUsers idEmpresa " +usuario.getIdEmpresa().getIdEmpresa());      
      usuariosList =  usuarios_EJB.FindUsersCia(usuario.getIdEmpresa().getIdEmpresa());           
  }
  
    public void populateroles(){        
        LOGGER.log(Level.INFO, "Entramos al conroller en populateRoles " + this.empresa.getIdEmpresa() );        
        rolesList= roles_EJB.findrolesCia(this.empresa.getIdEmpresa());
    };

    public List<Roles> getRolesList() {
        return rolesList;
    }

    public void setRolesList(List<Roles> rolesList) {
        this.rolesList = rolesList;
    }

    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }    

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public List<Usuarios> getUsuariosList() {
        return usuariosList;
    }

    public void setUsuariosList(List<Usuarios> usuariosList) {
        this.usuariosList = usuariosList;
    }

    public List<UsuariosRoles> getUsuariosRolesList() {
        return usuariosRolesList;
    }

    public void setUsuariosRolesList(List<UsuariosRoles> usuariosRolesList) {
        this.usuariosRolesList = usuariosRolesList;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    
    
   public void usuarioCia(){   
      usuariosList.clear();      
      LOGGER.log(Level.INFO, "El codigo de compañia es " + this.empresa.getIdEmpresa() );
      usuariosList =  usuarios_EJB.FindUsersCia(this.empresa.getIdEmpresa());
  }
    
}
