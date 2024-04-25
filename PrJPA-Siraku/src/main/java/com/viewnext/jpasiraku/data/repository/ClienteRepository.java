package com.viewnext.jpasiraku.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.viewnext.jpasiraku.data.model.Cliente;

/**
 * Interfaz {@link ClienteRepository} extiende de {@link JpaRepository}.
 * <p>
 * Esta interfaz proporciona método para tratar objetos {@link Cliente} de la
 * base de datos usando JPA.
 * 
 * @author Mario Sánchez Pilar
 *
 */

public interface ClienteRepository extends JpaRepository<Cliente, String> {

//	@Query("SELECT CONCAT(SUBSTR(c.nombre, 1, 1), '. ', c.apellido1, ' ', c.apellido2) FROM Cliente c WHERE c.nombre LIKE :nombre")
//	List<Object> buscamePorNombre(String nombre);
//
//	@Query("SELECT CONCAT(SUBSTR(c.nombre, 1, 1), '. ', c.apellido1, ' ', c.apellido2) FROM Cliente c WHERE c.direccion.provincia LIKE :provincia ORDER BY c.apellido1 ASC")
//	List<Object> buscamePorProvincia(String provincia);

}
