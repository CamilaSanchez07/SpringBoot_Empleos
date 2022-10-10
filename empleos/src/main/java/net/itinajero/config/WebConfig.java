package net.itinajero.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//la siguiente notacion sirve para configurar metodos de las clases
@Configuration
public class WebConfig implements WebMvcConfigurer{

	// para inyectar el valor de la propiedad en esta variable utilizamos la notacion:
	@Value("${empleosapp.ruta.imagenes}")
	private String rutaImagenes;
	
	// en esta caso se esta configurando la url donde se van a encontrar las imagenes
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//registry.addResourceHandler("/logos/**").addResourceLocations("file:c:/empleos/img-vacantes/"); // Windows
		
		registry.addResourceHandler("/logos/**").addResourceLocations("file:" + rutaImagenes); // Windows
		}
}
