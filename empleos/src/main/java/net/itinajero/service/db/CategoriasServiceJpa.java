package net.itinajero.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import net.itinajero.model.Categoria;
import net.itinajero.repository.CategoriasRepository;
import net.itinajero.service.ICategoriasService;

@Service
// Con la anotacion @Primary le estamos diciendo al controlador que utilice primeramente esta interfaz
@Primary
public class CategoriasServiceJpa implements ICategoriasService {

	// para inyectar una instancia agregamos la notacion @Autowired
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
