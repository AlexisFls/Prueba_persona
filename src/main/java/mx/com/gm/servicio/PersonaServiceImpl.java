package mx.com.gm.servicio;

import java.util.List;
import mx.com.gm.dao.PersonaDao;
import mx.com.gm.domain.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service //Sirve para poder inyectar esta clase como una implementacion de la clase Persona Service 
public class PersonaServiceImpl implements PersonaService{
    
    @Autowired
    private PersonaDao personaDao;
    
    @Override
    @Transactional(readOnly=true)//ya que solo se a leer info
    public List<Persona> listarPersonas() {
        return (List<Persona>) personaDao.findAll();
    }

    @Override
    @Transactional //ya que si se va modificar info en la basededatos osea debe hacer commit o roll back
    public void guardar(Persona persona) {
        personaDao.save(persona);
    }

    @Override
    @Transactional //ya que si se va modificar info en la basededatos osea debe hacer commit o roll back

    public void eliminar(Persona persona) {
        personaDao.delete(persona);
    }

    @Override
    @Transactional(readOnly=true)//ya que solo se a leer info

    public Persona encontrarPersona(Persona persona) {
        return personaDao.findById(persona.getIdPersona()).orElse(null);
    }
    
}
