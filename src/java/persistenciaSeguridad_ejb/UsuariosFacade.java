package persistenciaSeguridad_ejb;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modelos_seguridad.Empresa;
import modelos_seguridad.Usuarios;

/**
 *
 * @author Carlos Avalos
 */
@Stateless
public class UsuariosFacade extends AbstractFacade<Usuarios> implements UsuariosFacadeLocal {

    @PersistenceContext(unitName = "SeguridadV5PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuariosFacade() {
        super(Usuarios.class);
    }
    
    @Override
    public Usuarios UsuarioIniciarSesion (Usuarios us){
        Usuarios usuario = null;
        String consulta;
        try {
            consulta = "FROM Usuarios u WHERE u.codUsuario = ?1 and u.clave = ?2 and u.estado='A' ";           
            Query query = em.createQuery(consulta);
            query.setParameter(1, us.getCodUsuario());
            query.setParameter(2, us.getClave());
            
            List<Usuarios> lista = query.getResultList();
            if(!lista.isEmpty()){
                usuario = lista.get(0);
            }
        } catch (Exception e) {
            throw e;
        } 
      return usuario;
    }

    @Override
    public Usuarios UsuarioIniciarSesioneEmp(Usuarios us, Empresa emp ){
        Usuarios usuario = null;
        String consulta; 
        try {
            consulta = "FROM Usuarios u WHERE u.codUsuario = ?1 and u.clave = ?2 and u.estado='A' ";           
            Query query = em.createQuery(consulta);
            query.setParameter(1, us.getCodUsuario());
            query.setParameter(2, us.getClave());
            
            List<Usuarios> lista = query.getResultList();
            //System.out.println("idEmpresa= "+emp.getIdEmpresa());
            
            for (Usuarios m : lista){
                if (m.getIdEmpresa().getIdEmpresa().equals(emp.getIdEmpresa())){
                    usuario = m;
                }
            }
            /*if(!lista.isEmpty()  ){
                //System.out.println(" Recorriendo el loop UsuariosFacade idEmpresa id Empresa en user: " );
                usuario = lista.get(1);
            }*/
        } catch (Exception e) {
            throw e;
        } 
      return usuario;
    }
   
}
