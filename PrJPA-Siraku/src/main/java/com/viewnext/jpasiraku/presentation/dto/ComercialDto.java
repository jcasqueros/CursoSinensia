package com.viewnext.jpasiraku.presentation.dto;

import com.viewnext.jpasiraku.data.model.Comercial;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Clase {@link ComercialDto} para tratar {@link Comercial} en capa de
 * presentación.
 * 
 * @author Mario Sánchez Pilar, Álvaro Ojalvo García y Jorge Casquero Sancho
 *
 */

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ComercialDto {

	private Long codigo;

	private String nombre;

	private String apellido1;

	private String apellido2;

}
