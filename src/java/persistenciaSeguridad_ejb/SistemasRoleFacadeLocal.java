/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistenciaSeguridad_ejb;

import java.util.List;
import javax.ejb.Local;
import modelos_seguridad.SistemasRole;

/**
 *
 * @author Carlos Avalos
 */
@Local
public interface SistemasRoleFacadeLocal {

    void create(SistemasRole sistemasRole);

    void edit(SistemasRole sistemasRole);

    void remove(SistemasRole sistemasRole);

    SistemasRole find(Object id);

    List<SistemasRole> findAll();

    List<SistemasRole> findRange(int[] range);

    int count();
    
}
