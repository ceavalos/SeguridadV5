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
import modelos_seguridad.SistemasRole;
import modelos_seguridad.Usuarios;

/**
 *
 * @author Carlos Avalos
 */
@Stateless
public class SistemasRoleFacade extends AbstractFacade<SistemasRole> implements SistemasRoleFacadeLocal {

    @PersistenceContext(unitName = "SeguridadV5PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SistemasRoleFacade() {
        super(SistemasRole.class);
    }

   
    
}
