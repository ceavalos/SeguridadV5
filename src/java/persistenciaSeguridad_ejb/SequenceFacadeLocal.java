/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistenciaSeguridad_ejb;

import java.util.List;
import javax.ejb.Local;
import modelos_seguridad.Sequence;

/**
 *
 * @author Carlos Avalos
 */
@Local
public interface SequenceFacadeLocal {

    void create(Sequence sequence);

    void edit(Sequence sequence);

    void remove(Sequence sequence);

    Sequence find(Object id);

    List<Sequence> findAll();

    List<Sequence> findRange(int[] range);

    int count();
    
}
