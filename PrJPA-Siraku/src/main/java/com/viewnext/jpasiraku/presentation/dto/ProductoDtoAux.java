package com.viewnext.jpasiraku.presentation.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ProductoDtoAux {

	private Long codigo;

	private String familiaNombre;

	private double precio;

	private Date fechaAlta;

	private boolean descatalogado;

}
