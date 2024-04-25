package com.viewnext.jpasiraku.presentation.exceptionhandler;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Clase 'ApiError' que se usará como plantilla para las excepciones recogidas
 * por {@link GlobalExceptionHandler}.
 * 
 * @author Mario Sánchez Pilar
 *
 *         - Nota: Las
 *         etiquetas @Data @NoArgsConstructor @RequiredArgsConstructor @NonNull
 *         requieren la dependencia de Lombok y su instalación en el equipo.
 * 
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class ApiError {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime date = LocalDateTime.now();

	@NonNull
	private String message;
}
