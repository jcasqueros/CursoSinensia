package com.viewnext.jpasiraku.data.model;

import java.util.List;

import com.viewnext.jpasiraku.util.DatosContacto;
import com.viewnext.jpasiraku.util.Direccion;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Clase del POJO {@link Cliente}.
 * <p>
 * Su @Id es 'identificadorFiscal'.
 * <p>
 * Tiene embedded tanto {@link Direccion} como {@link DatosContacto}.
 * <p>
 * Tiene una relación @ManyToMany con {@link Producto} y una relación @ManyToOne
 * con {@link Comercial}.
 * 
 * @author Mario Sánchez Pilar
 * 
 *         Las anotaciones @Data, @Builder, @AllArgsConstructor
 *         y @RequiredArgsConstructor requiere la dependencia y la instalación
 *         de Lombok.
 *
 */

@Entity
@Table(name = "CLIENTE")
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Cliente {

	@Id
	@Column(name = "identificador_fiscal")
	private String identificadorFiscal;

	@Column(name = "nombre_comercial")
	private String nombreComercial;

	private String nombre;

	private String apellido1;

	private String apellido2;

	@Embedded
	private Direccion direccion;

	@Embedded
	private DatosContacto datosContacto;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "CLIENTE_PRODUCTO", joinColumns = @JoinColumn(name = "identificador_fiscal"), inverseJoinColumns = @JoinColumn(name = "codigo"))
	private List<Producto> productos;

	@ManyToOne
	@JoinColumn(name = "codigo")
	private Comercial comercial;

}
