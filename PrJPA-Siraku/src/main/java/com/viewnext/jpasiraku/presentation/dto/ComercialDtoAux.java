package com.viewnext.jpasiraku.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ComercialDtoAux {

	private Long codigo;

	private String nombreCompleto;

}
