package com.viewnext.jpasiraku.business.bo;

import java.util.List;

import com.viewnext.jpasiraku.data.model.Cliente;
import com.viewnext.jpasiraku.util.DatosContacto;
import com.viewnext.jpasiraku.util.Direccion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Clase {@link ClienteBo} para tratar los {@link Cliente} en la capa de
 * servicio.
 * 
 * @author Mario Sánchez Pilar, Álvaro Ojalvo García y Jorge Casquero Sancho
 *
 */

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ClienteBo {

	private String identificadorFiscal;

	private String nombreComercial;

	private String nombre;

	private String apellido1;

	private String apellido2;

	private Direccion direccion;

	private DatosContacto datosContacto;

	private List<ProductoBo> productos;

	private ComercialBo comercial;

}
