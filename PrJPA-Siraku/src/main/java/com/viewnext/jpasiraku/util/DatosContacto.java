package com.viewnext.jpasiraku.util;

import com.viewnext.jpasiraku.data.model.Cliente;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Clase @Embeddable de {@link DatosContacto}.
 * <p>
 * Los datos de esta clase seran embedidos en {@link Cliente}.
 * 
 * @author Mario Sánchez Pilar
 *
 */

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Embeddable
public class DatosContacto {

	private String telefono1;

	private String telefono2;

	private String email;

}
