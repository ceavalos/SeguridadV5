/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistenciaSeguridad_ejb;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modelos_seguridad.Empresa;
import modelos_seguridad.Usuarios;
import modelos_seguridad.UsuariosRoles;

/**
 *
 * @author Carlos Avalos
 */
@Stateless
public class UsuariosRolesFacade extends AbstractFacade<UsuariosRoles> implements UsuariosRolesFacadeLocal {

    @PersistenceContext(unitName = "SeguridadV5PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public List<Usuarios> FindUsersCia(Integer emp){      
      String consulta;             
      List<Usuarios> lista =  new ArrayList<Usuarios>();
      
        try {
            consulta = "FROM Usuarios u WHERE u.estado = 'A' and u.idEmpresa.idEmpresa = ?1 ";           
            Query query = em.createQuery(consulta);
            query.setParameter(1, emp);            
            
            lista = query.getResultList();
            /*
            for(Usuarios lis : lista){
                System.out.print ("Revisando lista empresa " + emp +" usuario de empresa "+ lis.getCodUsuario());
            }*/
            
        } catch (Exception e) {
            System.out.println("Error al leer usuarios para desplegar " + e.getMessage());
        }
        return lista;
    };
            
    public UsuariosRolesFacade() {
        super(UsuariosRoles.class);
    }
    
}
