/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistenciaSeguridad_ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modelos_seguridad.Empresa;
import modelos_seguridad.Opciones;
import persistenciaSeguridad_ejb.AbstractFacade;

/**
 *
 * @author Carlos Avalos
 */
@Stateless
public class OpcionesFacade extends AbstractFacade<Opciones> implements OpcionesFacadeLocal {

    @PersistenceContext(unitName = "SeguridadV5PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OpcionesFacade() {
        super(Opciones.class);
    }
    
    @Override
    public List<Opciones> buscarAll(){
        List<Opciones> opt;
        String consulta;
        try {
            consulta = "FROM Opciones u ";           
            Query query = em.createQuery(consulta);
            
            List<Opciones> lista = query.getResultList();
            opt = lista;
        } catch (Exception e) {
            throw e;
        } 
      return opt;
                
    }
    @Override
    public List<Opciones> buscarSubmenu(){
     
        List<Opciones> opt;
        String consulta;
        try {
            consulta = "FROM Opciones u WHERE tipo='S' ";           
            Query query = em.createQuery(consulta);
            
            List<Opciones> lista = query.getResultList();
            opt = lista;
        } catch (Exception e) {
            throw e;
        } 
      return opt;
    }
    
    @Override
    public List<Opciones> findCiaAll(Empresa cia) {
        List<Opciones> opcionesrolesList;
        String consulta; 
        try {
            consulta = "FROM Opciones u WHERE u.idEmpresa = ?1  ";           
            Query query = em.createQuery(consulta);
            query.setParameter(1, cia);
            
            opcionesrolesList = query.getResultList();
            System.out.println("idEmpresa= "+cia.getIdEmpresa());
            
                       
       } catch (Exception e) {
            throw e;
        } 
      return opcionesrolesList;
    } 
}
