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
import modelos_seguridad.Opciones;
import modelos_seguridad.OpcionesRoles;
import modelos_seguridad.Roles;
import modelos_seguridad.Usuarios;
import modelos_seguridad.UsuariosRoles;
import persistenciaSeguridad_ejb.EmpresaFacadeLocal;
import persistenciaSeguridad_ejb.OpcionesFacadeLocal;
import persistenciaSeguridad_ejb.OpcionesRolesFacadeLocal;
import persistenciaSeguridad_ejb.RolesFacadeLocal;

@Named
@SessionScoped
public class CrudOpcionesRoles implements Serializable {
     private final static Logger LOGGER = Logger.getLogger("CrudOpcionesRoles");
    
    @EJB
    OpcionesFacadeLocal Opciones_EJB ;
     
    
    @EJB
    EmpresaFacadeLocal empresa_EJB;
        
        
    @EJB
    RolesFacadeLocal roles_EJB;
    
    @EJB
    OpcionesRolesFacadeLocal opcionesRoles_EJB;
    
    
    @Inject
    private Empresa empresa;
    
    @Inject
    private Roles  roles;
    
    List<Usuarios> usuariosList;      
    List<Roles> rolesList;
    List<UsuariosRoles> usuariosrolesList;
    List<OpcionesRoles> opcionesrolesList;
    List<Opciones>  opcionesrolesList2;
    
   @Inject 
   Opciones opciones;
    
    @Inject
    OpcionesRoles opcionesroles;
    
    @PostConstruct
    public void init(){
        opcionesrolesList = opcionesRoles_EJB.findAll(); 
        //System.out.println("Asignando lista de roles x usuario");
        rolesList = roles_EJB.findAll();               
    }
    
  

    public void setUsuarios(List<Usuarios> usuarios) {
        this.usuariosList = usuarios;
    }

    
    public void setUsuario(UsuariosRoles usuario) {
        //this.usuarioRol = usuario;
    }

    
    public void prepareCreate(){
       //System.out.println("Preparando empresa");
       //this.usuarioRol = new UsuariosRoles();     
   }    

    public Opciones getOpciones() {
        return opciones;
    }

    public void setOpciones(Opciones opciones) {
        this.opciones = opciones;
    }

 public void almacenar(){   
     this.opcionesroles.setIdEmpresa(this.empresa); 
     this.opcionesroles.setIdRol(this.roles);
     this.opcionesroles.setIdOpcion(this.opciones);
     opcionesRoles_EJB.create(opcionesroles);
     opcionesrolesList = opcionesRoles_EJB.findAll(); 
   }
 
  public void update(){
      
     this.opcionesroles.setIdEmpresa(this.empresa); 
     this.opcionesroles.setIdRol(this.roles);
     this.opcionesroles.setIdOpcion(this.opciones);
     
     System.out.println("Estamos en update empresa= "+this.empresa.getNombre());
     System.out.println("Estamos en update Roles= "+this.roles.getNombreRol());
     System.out.println("Estamos en update Opcion= "+this.opciones.getDescOpcion());
     
     opcionesRoles_EJB.edit(opcionesroles);
     opcionesrolesList = opcionesRoles_EJB.findAll(); 
      
   }
  public void eliminar(OpcionesRoles opcion){     
      opcionesRoles_EJB.remove(opcion);
      opcionesrolesList = opcionesRoles_EJB.findAll(); 
  }   
  
  
  public void asignar(OpcionesRoles opcion){   
       this.opcionesroles = opcion;
       this.empresa = empresa_EJB.find( (opcion.getIdEmpresa().getIdEmpresa()));
       this.opciones = Opciones_EJB.find(opcion.getIdOpcion().getIdOpcion());
       this.roles   = roles_EJB.find( (opcion.getIdRol().getIdRol()));
        
       buscaopciones();
       buscaroles();              
    }
  
  
  public void buscaopciones (){
       //System.out.println("En buscaOpciones Empresa= "+this.empresa.getIdEmpresa());       
       opcionesrolesList2 = Opciones_EJB.findCiaAll(this.empresa);
  };
 
  public void buscaroles (){
       //System.out.println("En buscaRoles Empresa= "+this.empresa.getIdEmpresa());       
       rolesList = roles_EJB.findrolesCia(this.empresa);
       
  };
 
    public List<Opciones> getOpcionesrolesList2() {
        return opcionesrolesList2;
    }

    public void setOpcionesrolesList2(List<Opciones> opcionesrolesList2) {
        this.opcionesrolesList2 = opcionesrolesList2;
    }
         
  
    public void populateroles(){        
        LOGGER.log(Level.INFO, "Entramos al conroller en populateRoles " + this.empresa.getIdEmpresa() );        
        rolesList= roles_EJB.findrolesCia(this.empresa);
    };

    public List<Roles> getRolesList() {
        return rolesList;
    }

    public void setRolesList(List<Roles> rolesList) {
        this.rolesList = rolesList;
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

    public List<OpcionesRoles> getOpcionesrolesList() {
        return opcionesrolesList;
    }

    public void setOpcionesrolesList(List<OpcionesRoles> opcionesrolesList) {
        this.opcionesrolesList = opcionesrolesList;
    }

    public OpcionesRoles getOpcionesroles() {
        return opcionesroles;
    }

    public void setOpcionesroles(OpcionesRoles opcionesroles) {
        this.opcionesroles = opcionesroles;
    }

    
}
