package net.itinajero.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.itinajero.model.Vacante;
import net.itinajero.service.ICategoriasService;
import net.itinajero.service.IVacantesService;
import net.itinajero.util.Utileria;

@Controller
@RequestMapping("/vacantes")
public class VacantesController {
	
	@Value("${empleosapp.ruta.imagenes}")
	private String ruta;

	@Autowired
	private IVacantesService serviceVacantes;
	
	// para hacer uso de las categorias inyectamos el servicio usando la siguiente anotación:
	@Autowired
	private ICategoriasService serviceCategorias; 
	
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		List<Vacante> lista = serviceVacantes.buscarTodas();
    	model.addAttribute("vacantes", lista);
		return "vacantes/listVacantes";
	}
	
	@GetMapping("/create")
	public String crear(Vacante vacante, Model model) {
		// añadimos el listado de las categorias con el servicio y con el método "buscarTodas"
		model.addAttribute("categorias", serviceCategorias.buscarTodas() );
		return "vacantes/formVacante";
	}
	
	//BindingResult ayuda a mostrar el error dentro del controlador
	@PostMapping("/save")
	public String guardar(Vacante vacante, BindingResult result, RedirectAttributes attributes,
			@RequestParam("archivoImagen") MultipartFile multiPart) {
		// el siguiente condicional sirve para preguntar si existieron errores en el controlador
		if (result.hasErrors()) {
			for (ObjectError error: result.getAllErrors()){
				System.out.println("Ocurrio un error: "+ error.getDefaultMessage());
			}			
			return "vacantes/formVacante";
		}
		
		if (!multiPart.isEmpty()) {
			// ruta donde se guardan las imasgenes
			//String ruta = "c:/empleos/img-vacantes/"; // Windows
			String nombreImagen = Utileria.guardarArchivo (multiPart, ruta);
			
			if (nombreImagen != null) { // La imagen SI se subio
			  // Procesamos la variable nombreImagen
				vacante.setImagen(nombreImagen);
			
			}
		}
		
		serviceVacantes.guardar(vacante);
		
		// usamos redirect y flashattribute para mostrar el mensaje de guardado
		attributes.addFlashAttribute("msg", "Registro Guardado");		
		System.out.println("Vacante: " + vacante);		
		
		//agregamos el redirect para hacer una petición tipo get a la url "/vacantes/index"
		return "redirect:/vacantes/index"; 
	}

	@GetMapping("/delete")
	public String eliminar(@RequestParam("id") int idVacante, Model model) {
		System.out.println("Borrando vacante con id: " + idVacante);
		model.addAttribute("id", idVacante);
		return "mensaje";
	}
	
	@GetMapping("/view/{id}")
	public String verDetalle(@PathVariable("id") int idVacante, Model model) {		
		Vacante vacante = serviceVacantes.buscarPorId(idVacante);	
		System.out.println("Vacante: " + vacante);
		model.addAttribute("vacante", vacante);
		
		// Buscar los detalles de la vacante en la BD...		
		return "detalle";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	
}
