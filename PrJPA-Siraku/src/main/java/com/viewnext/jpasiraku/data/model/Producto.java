package com.viewnext.jpasiraku.data.model;

import java.util.Date;

import com.viewnext.jpasiraku.util.Familia;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Clase del POJO {@link Producto}.
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
@Table(name = "PRODUCTO")
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Producto {

	@Id
	@TableGenerator(name = "PRODUCTO_SEQ", table = "SEQUENCE", pkColumnName = "nombre", pkColumnValue = "PRODUCTO_SEQ", valueColumnName = "valor", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "PRODUCTO_SEQ")
	private Long codigo;

	private String nombre;

	private double precio;

	@Temporal(value = TemporalType.DATE)
	private Date fechaAlta;

	@Column(columnDefinition = "ENUM")
	private Familia familia;

	private boolean descatalogado;

}
