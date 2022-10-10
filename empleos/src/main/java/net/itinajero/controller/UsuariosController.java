package net.itinajero.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.itinajero.model.Usuario;
import net.itinajero.service.IUsuariosService;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {
    
	//@Autowired
	//private IUsuariosService serviceUsuarios;
	
    @GetMapping("/index")
	public String mostrarIndex(Model model) {
    	// List<Usuario> lista = serviceUsuarios.buscarTodos();
    	// model.addAttribute("usuario", lista);
    	return "usuarios/listUsuarios";
	}
    
    @GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idUsuario, RedirectAttributes attributes) {		    	
    	//serviceUsuarios.eliminar(idUsuario);
		attributes.addFlashAttribute("msg", "El Usuario fue eliminado!");
    	
		return "redirect:/usuarios/index";
	}
}
