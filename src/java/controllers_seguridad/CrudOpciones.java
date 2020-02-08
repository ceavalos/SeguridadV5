
package controllers_seguridad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import modelos_seguridad.Empresa;
import modelos_seguridad.Opciones;
import modelos_seguridad.Sistemas;
import persistenciaSeguridad_ejb.OpcionesFacadeLocal;
import persistenciaSeguridad_ejb.SistemasFacadeLocal;

@Named
@SessionScoped
public class CrudOpciones implements Serializable{

  @EJB
  private OpcionesFacadeLocal opcionesEJB;
  
  
  @EJB
  private SistemasFacadeLocal sistemasEJB;
  
  private List<Opciones> opciones;  
  private List<Opciones> opciones2;
  
  private List<Sistemas> sistemas = new ArrayList<Sistemas>();
  
  @Inject  
  private Opciones opcion;
 
  @Inject
  Empresa empresa;
 
  @PostConstruct
  private void init(){      
      opciones =  opcionesEJB.findAll();
  }
  
  public void opcionesCia(){
      System.out.println(" Empresa " + this.opcion.getIdEmpresa().getNombre());
      opciones2 = opcionesEJB.findCiaAll(this.opcion.getIdEmpresa());
  }
  
 public void buscaopciones (){       
       System.out.println("En buscaOpciones Empresa= "+ this.opcion.getIdEmpresa().getIdEmpresa());  
       sistemas.clear();
       sistemas = sistemasEJB.FindSistemasCia(this.opcion.getIdEmpresa().getIdEmpresa());
       for (Sistemas sis : sistemas){
       System.out.println(sis.getNombreSistema());
       }
  };

    public List<Opciones> getOpciones() {
        return opciones;
    }

    public void setOpciones(List<Opciones> opciones) {
        this.opciones = opciones;
    }

    public List<Opciones> getOpciones2() {
        return opciones2;
    }

    public void setOpciones2(List<Opciones> opciones2) {
        this.opciones2 = opciones2;
    }

    public Opciones getOpcion() {
        return opcion;
    }

    public void setOpcion(Opciones opcion) {
        this.opcion = opcion;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public List<Sistemas> getSistemas() {
        return sistemas;
    }

    public void setSistemas(List<Sistemas> sistemas) {
        this.sistemas = sistemas;
    }
 
     public void asignar(Opciones opcion){
         System.out.println("Asignar opcion");         
        this.opcion = opcion;
        opcionesCia();
        
    }
  
   public void update(){        
          //System.out.println("id_NombreEmpresa: " + this.opcion.getNombre());
          opcionesEJB.edit(opcion);
           opciones =  opcionesEJB.findAll();     
    }
   
   public void prepareCreate(){
       //System.out.println("Preparando opcion");
       this.opcion = new Opciones();     
       opciones =  opcionesEJB.findAll();
       //this.opcion =  null;
   }
   
   public void almacenar(){
      // System.out.println("Creando opcion");
       opcionesEJB.create(this.opcion);
       opciones =  opcionesEJB.findAll();
   }
  
  public void eliminar(Opciones opcion){
      System.out.println("Eliminando registro id_opcion: "+ opcion.getIdEmpresa());
      opcionesEJB.remove(opcion);
      opciones =  opcionesEJB.findAll();
      
  }
  
}
