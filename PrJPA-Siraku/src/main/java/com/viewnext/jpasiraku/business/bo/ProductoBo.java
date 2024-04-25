package com.viewnext.jpasiraku.business.bo;

import java.util.Date;

import com.viewnext.jpasiraku.data.model.Producto;
import com.viewnext.jpasiraku.util.Familia;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase {@link ProductoBo} para tratar {@link Producto} en capa de servicio.
 * 
 * @author Mario Sánchez Pilar, Álvaro Ojalvo García y Jorge Casquero Sancho
 *
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductoBo {

	private Long codigo;

	private String nombre;

	private double precio;

	private Date fechaAlta;

	private Familia familia;

	private boolean descatalogado;
}
