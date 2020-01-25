/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistenciaSeguridad_ejb;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modelos_seguridad.Roles;

/**
 *
 * @author Carlos Avalos
 */
@Stateless
public class RolesFacade extends AbstractFacade<Roles> implements RolesFacadeLocal {

    @PersistenceContext(unitName = "SeguridadV5PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolesFacade() {
        super(Roles.class);
    }

    @Override
    public List<Roles> findrolesCia(Integer emp){
        List<Roles> roles = new ArrayList<Roles>();
        String consulta;
        
        try {
            consulta = "FROM Roles u WHERE u.estado = 'A' and u.idEmpresa.idEmpresa = ?1 ";           
            Query query = em.createQuery(consulta);
            query.setParameter(1, emp);            
            
            roles = query.getResultList();
            
        } catch (Exception e) {
            System.out.println("Error al buscar roles by cia " + e.getMessage());
        }
        return roles;
        
    }
            
}
