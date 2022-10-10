package net.itinajero.service;

import java.util.List;
import net.itinajero.model.Usuario;

public interface IUsuariosService {
	List<Usuario> buscarTodos();
	void guardar(Usuario usuario);
	void eliminar(Integer idUsuario);
}