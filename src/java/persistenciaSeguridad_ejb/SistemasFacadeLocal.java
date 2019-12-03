/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistenciaSeguridad_ejb;

import java.util.List;
import javax.ejb.Local;
import modelos_seguridad.Sistemas;
import modelos_seguridad.Usuarios;

/**
 *
 * @author Carlos Avalos
 */
@Local
public interface SistemasFacadeLocal {

    void create(Sistemas sistemas);

    void edit(Sistemas sistemas);

    void remove(Sistemas sistemas);

    Sistemas find(Object id);

    List<Sistemas> findAll();

    List<Sistemas> findRange(int[] range);

    int count();
    
    
}
