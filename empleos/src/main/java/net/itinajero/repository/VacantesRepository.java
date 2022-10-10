package net.itinajero.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import net.itinajero.model.Vacante;

public interface VacantesRepository extends JpaRepository<Vacante, Integer> {
	
	// metodo que regresa una lista de tipo vacante
	List<Vacante> findByEstatus(String estatus);
	
	// metodo que regresa una lista de tipo vacantes, de acuerdo al DESTACADO y ESTATUS
	List<Vacante> findByDestacadoAndEstatusOrderByIdDesc(int destacado, String estatus);
	
	// metodo que regresa una lista de tipo vacante, la cual busca por un cierto rango de salario, listandolos del mayor al menor
	List<Vacante> findBySalarioBetweenOrderBySalarioDesc(double s1, double s2);
	
	// metodo que regresa una lista de tipo vacante, este busca por varios, ademas de mostrarlos descendientemente
	List<Vacante> findByEstatusIn(String[] estatus);
	
}
