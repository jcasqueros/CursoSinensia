package com.viewnext.jpasiraku.presentation.dto;

import java.util.List;

import com.viewnext.jpasiraku.business.bo.ComercialBo;
import com.viewnext.jpasiraku.util.DatosContacto;
import com.viewnext.jpasiraku.util.Direccion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ClienteDtoAux {

	private String identificadorFiscal;

	private String nombreComercial;

	private String nombreCompleto;

	private Direccion direccion;

	private DatosContacto datosContacto;

	private List<ProductoDto> productos;

	private ComercialBo comercial;

}
