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
import modelos_seguridad.Opciones;
import modelos_seguridad.OpcionesRoles;
import modelos_seguridad.Usuarios;

/**
 *
 * @author Carlos Avalos
 */
@Stateless
public class OpcionesRolesFacade extends AbstractFacade<OpcionesRoles> implements OpcionesRolesFacadeLocal {

    @PersistenceContext(unitName = "SeguridadV5PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OpcionesRolesFacade() {
        super(OpcionesRoles.class);
    }

    @Override
    public List<Opciones> findCiaAll(Opciones cia) {
        List<Opciones> opcionesrolesList;
        String consulta; 
        try {
            consulta = "FROM OpcionesRoles u WHERE u.idEmpresa = ?1  ";           
            Query query = em.createQuery(consulta);
            query.setParameter(1, cia.getIdEmpresa());
            
            opcionesrolesList = query.getResultList();
            System.out.println("idEmpresa= "+cia.getIdEmpresa());
            
                       
       } catch (Exception e) {
            throw e;
        } 
      return opcionesrolesList;
    } 
   
    
    
}
