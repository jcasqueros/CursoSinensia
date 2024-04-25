package com.viewnext.jpasiraku.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.viewnext.jpasiraku.data.model.Comercial;

/**
 * Interfaz {@link ComercialRepository} extiende de {@link JpaRepository}.
 * <p>
 * Esta interfaz proporciona método para tratar objetos {@link Comercial} de la
 * base de datos usando JPA.
 * 
 * @author Mario Sánchez Pilar
 *
 */

public interface ComercialRepository extends JpaRepository<Comercial, Long> {

	List<Comercial> findByNombreIgnoreCase(String nombre);

	List<Comercial> findByNombreLike(String nombre);

}
