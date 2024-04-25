package com.viewnext.jpasiraku.business.bo;

import com.viewnext.jpasiraku.data.model.Comercial;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Clase {@link ComercialBo} para tratar los {@link Comercial} en capa de
 * servicio.
 * 
 * @author Mario Sánchez Pilar, Álvaro Ojalvo García y Jorge Casquero Sancho
 *
 */

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ComercialBo {

	private Long codigo;

	private String nombre;

	private String apellido1;

	private String apellido2;

}
