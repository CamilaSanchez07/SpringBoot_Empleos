package net.itinajero.service;

import java.util.List;
import net.itinajero.model.Vacante;

public interface IVacantesService {
	List<Vacante> buscarTodas();
	Vacante buscarPorId(Integer idVacante);
	void guardar(Vacante vacante);
}
