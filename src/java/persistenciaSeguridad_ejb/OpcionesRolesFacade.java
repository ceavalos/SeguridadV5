/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistenciaSeguridad_ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelos_seguridad.OpcionesRoles;
import persistenciaSeguridad_ejb.AbstractFacade;

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
    
}
