package com.viewnext.jpasiraku.presentation.dto;

import java.util.Date;

import com.viewnext.jpasiraku.data.model.Producto;
import com.viewnext.jpasiraku.util.Familia;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Clase {@link ProductoDto} para tratar {@link Producto} en capa de
 * presentación.
 * 
 * @author Mario Sánchez Pilar, Álvaro Ojalvo García y Jorge Casquero Sancho
 *
 */

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ProductoDto {

	private Long codigo;

	private String nombre;

	private double precio;

	private Date fechaAlta;

	private Familia familia;

	private boolean descatalogado;

}
