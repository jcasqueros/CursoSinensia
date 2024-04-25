package com.viewnext.jpasiraku.data.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.viewnext.jpasiraku.data.model.Producto;
import com.viewnext.jpasiraku.util.Familia;

/**
 * Interfaz {@link ProductoRepository} extiende de {@link JpaRepository}.
 * <p>
 * Esta interfaz proporciona método para tratar objetos {@link Producto} de la
 * base de datos usando JPA.
 * 
 * @author Mario Sánchez Pilar
 *
 */

public interface ProductoRepository extends JpaRepository<Producto, Long> {

	List<Producto> findByFamilia(Familia familia);

	List<Producto> findByPrecioBetween(double min, double max);

	List<Producto> findByFechaAltaBetween(Date desde, Date hasta);

}
