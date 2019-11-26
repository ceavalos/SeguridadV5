/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistenciaSeguridad_ejb;

import java.util.List;
import javax.ejb.Local;
import modelos_seguridad.OpcionesRoles;

/**
 *
 * @author Carlos Avalos
 */
@Local
public interface OpcionesRolesFacadeLocal {

    void create(OpcionesRoles opcionesRoles);

    void edit(OpcionesRoles opcionesRoles);

    void remove(OpcionesRoles opcionesRoles);

    OpcionesRoles find(Object id);

    List<OpcionesRoles> findAll();

    List<OpcionesRoles> findRange(int[] range);

    int count();
    
}
