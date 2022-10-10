package net.itinajero.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import net.itinajero.model.Categoria;
import net.itinajero.repository.CategoriasRepository;
import net.itinajero.service.ICategoriasService;

public class CategoriasServiceJpa implements ICategoriasService {

	// para usar la los metodos en el servicio debemos inyectar una instancia en el repositorio 
	@Autowired
	private CategoriasRepository categoriasRepo;
	
	@Override
	public void guardar(Categoria categoria) {
		categoriasRepo.save(categoria);

	}

	@Override
	public List<Categoria> buscarTodas() {
		return categoriasRepo.findAll();
	}

	@Override
	public Categoria buscarPorId(Integer idCategoria) {
		Optional<Categoria> optional = categoriasRepo.findById(idCategoria);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

}
