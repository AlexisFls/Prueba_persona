
package mx.com.gm.web;

import lombok.extern.slf4j.Slf4j;//bilbioteca para acceder al log Sljf4
import mx.com.gm.domain.Persona;
import mx.com.gm.servicio.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;//pata poder usar la anotacion controller
import org.springframework.ui.Model;//bilbioteca para acceder a la clase model
import org.springframework.web.bind.annotation.GetMapping; // biblioteca para poder usar getmapping
import org.springframework.web.bind.annotation.PostMapping;

@Controller //tiene una anotacion de component y la puede reconocer y utilizar, como es thymeleaf ya se ocupa un controlador MVC se quita el rest
@Slf4j //para manejo de mensajes al log

public class ControladorInicio {
            
    @Autowired
    private PersonaService personaService;
       
    @GetMapping("/") //Para que se mapee a una ruta
    public String inicio(Model model){
        var personas = personaService.listarPersonas();

        log.info("Ejecutando el controlador spring MVC");
        //model.addAttribute("persona", persona);
        model.addAttribute("personas", personas);
        return "index";
    }
        @GetMapping("/agregar")
        public String agregar(Persona persona){
            return "modificar";
        }
        
        @PostMapping("/guardar")
        public String guardar(Persona persona){
        personaService.guardar(persona);
        return "redirect:/";
        }
        
        @GetMapping("/editar/{idPersona}")
        public String editar(Persona persona,Model model){
        persona=personaService.encontrarPersona(persona);
        model.addAttribute("persona",persona);
        return "modificar";
        }
        
        @GetMapping("/eliminar")
        public String eliminar(Persona persona){
        personaService.eliminar(persona);
        return "redirect:/";
        }


        

}
