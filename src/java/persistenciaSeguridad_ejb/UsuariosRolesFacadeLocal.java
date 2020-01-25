/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistenciaSeguridad_ejb;

import java.util.List;
import javax.ejb.Local;
import modelos_seguridad.Empresa;
import modelos_seguridad.Usuarios;
import modelos_seguridad.UsuariosRoles;

/**
 *
 * @author Carlos Avalos
 */
@Local
public interface UsuariosRolesFacadeLocal {

    void create(UsuariosRoles usuariosRoles);

    void edit(UsuariosRoles usuariosRoles);

    void remove(UsuariosRoles usuariosRoles);

    UsuariosRoles find(Object id);

    List<UsuariosRoles> findAll();

    List<UsuariosRoles> findRange(int[] range);

    List<Usuarios> FindUsersCia(Integer emp);
    
    int count();
    
}
