package persistenciaSeguridad_ejb;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.List;
import javax.ejb.Local;
import modelos_seguridad.Plantillacontroller;

/**
 *
 * @author Carlos Avalos
 */
@Local
public interface PlantillacontrollerFacadeLocal {

    void create(Plantillacontroller plantillacontroller);

    void edit(Plantillacontroller plantillacontroller);

    void remove(Plantillacontroller plantillacontroller);

    Plantillacontroller find(Object id);

    List<Plantillacontroller> findAll();

    List<Plantillacontroller> findRange(int[] range);

    int count();
    
}
