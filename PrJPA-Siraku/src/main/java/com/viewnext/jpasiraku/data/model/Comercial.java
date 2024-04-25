package com.viewnext.jpasiraku.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Clase del POJO {@link Comercial}.
 * <p>
 * Su @Id es 'codigo' y se va generando cada vez que se hace un insert.
 * 
 * @author Mario Sánchez Pilar
 * 
 *         Las anotaciones @Data, @Builder, @AllArgsConstructor
 *         y @RequiredArgsConstructor requiere la dependencia y la instalación
 *         de Lombok.
 *
 */

@Entity
@Table(name = "COMERCIAL")
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Comercial {

	@Id
	@TableGenerator(name = "COMERCIAL_SEQ", table = "SEQUENCE", pkColumnName = "nombre", pkColumnValue = "COMERCIAL_SEQ", valueColumnName = "valor", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "COMERCIAL_SEQ")
	private Long codigo;

	private String nombre;

	private String apellido1;

	private String apellido2;

}
