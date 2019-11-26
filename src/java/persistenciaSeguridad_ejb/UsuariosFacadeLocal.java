package persistenciaSeguridad_ejb;

import java.util.List;
import javax.ejb.Local;
import modelos_seguridad.Empresa;
import modelos_seguridad.Usuarios;

@Local
public interface UsuariosFacadeLocal {

    void create(Usuarios usuarios);

    void edit(Usuarios usuarios);

    void remove(Usuarios usuarios);

    Usuarios find(Object id);

    List<Usuarios> findAll();

    List<Usuarios> findRange(int[] range);

    int count();
    
    Usuarios UsuarioIniciarSesion(Usuarios us);
    
    Usuarios UsuarioIniciarSesioneEmp(Usuarios us, Empresa emp);
    
}
