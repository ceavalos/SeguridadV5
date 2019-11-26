package persistenciaSeguridad_ejb;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelos_seguridad.Plantillacontroller;
import persistenciaSeguridad_ejb.AbstractFacade;

/**
 *
 * @author Carlos Avalos
 */
@Stateless
public class PlantillacontrollerFacade extends AbstractFacade<Plantillacontroller> implements PlantillacontrollerFacadeLocal {

    @PersistenceContext(unitName = "SeguridadV5PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PlantillacontrollerFacade() {
        super(Plantillacontroller.class);
    }
    
}
