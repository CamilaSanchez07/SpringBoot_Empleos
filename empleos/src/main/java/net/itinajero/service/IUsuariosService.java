package net.itinajero.service;

import java.util.List;

import net.itinajero.model.Usuario;

public interface IUsuariosService {
	void guardar(Usuario usuario);
	void eliminar(Integer idUsuario);
	List<Usuario> buscarTodos();
	// metodo que llama a los usuarios bucados por username
	Usuario buscarPorUsername(String username);
}
