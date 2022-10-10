package net.itinajero.service;

import java.util.List;
import net.itinajero.model.Categoria;


import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICategoriasService {
	void guardar(Categoria categoria);
	List<Categoria> buscarTodas();
	Categoria buscarPorId(Integer idCategoria);
	void eliminar(Integer idCategoria);	
	List<Categoria> buscarByExample(Example<Categoria> example);
	Page<Categoria>buscarTodas(Pageable page);
	
}