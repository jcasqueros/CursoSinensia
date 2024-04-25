package com.viewnext.jpasiraku.business.services.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.viewnext.jpasiraku.business.bo.ComercialBo;
import com.viewnext.jpasiraku.business.exception.AlreadyExistsException;
import com.viewnext.jpasiraku.business.exception.NotFoundException;
import com.viewnext.jpasiraku.converters.BoToModel;
import com.viewnext.jpasiraku.converters.ModelToBo;
import com.viewnext.jpasiraku.data.model.Comercial;
import com.viewnext.jpasiraku.data.repository.ComercialRepository;

/**
 * Test para {@link ComercialServiceImpl}.
 * 
 * @author Mario Sánchez Pilar, Álvaro Ojalvo García y Jorge Casquero Sancho
 *
 */

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(OrderAnnotation.class)
class ComercialServiceTest {

	@Mock
	ComercialRepository comercialRepository;

	@Mock
	ModelToBo modelToBo;

	@Mock
	BoToModel boToModel;

	@InjectMocks
	ComercialServiceImpl comercialServiceImpl;

	private Comercial comercial;
	private ComercialBo comercialBo;

	@BeforeEach
	public void setup() {
		comercial = new Comercial(1L, "Mario", "Sanchez", "Pilar");
		comercialBo = new ComercialBo(1L, "Mario", "Sanchez", "Pilar");
	}

	@DisplayName("Test .getById - Correct")
	@Test
	@Order(1)
	public void givenComercialId_whenGetById_thenReturnComercialBo() throws NotFoundException {
		when(comercialRepository.findById(1L)).thenReturn(Optional.of(comercial));
		when(modelToBo.comercialToBo(comercial)).thenReturn(comercialBo);

		ComercialBo comercialGet = comercialServiceImpl.getById(1L);

		assertThat(comercialGet).isNotNull();
		assertEquals(1L, comercialGet.getCodigo());
		assertEquals("Mario", comercialGet.getNombre());
		assertEquals("Sanchez", comercialGet.getApellido1());
		assertEquals("Pilar", comercialGet.getApellido2());
	}

	@DisplayName("Test .getById - Incorrect")
	@Test
	@Order(2)
	public void givenComercialId_whenGetById_thenThrowNotFoundException() {
		when(comercialRepository.findById(1L)).thenReturn(Optional.empty());
		
		assertThrows(NotFoundException.class, () -> comercialServiceImpl.getById(1L));
	}

	@DisplayName("Test .getAll")
	@Test
	@Order(3)
	public void givenNothing_whenGetAll_thenReturnComercialBoList() {
		when(modelToBo.comercialToBo(comercial)).thenReturn(comercialBo);
		when(comercialRepository.findAll()).thenReturn(List.of(comercial));
		
		List<ComercialBo> list = comercialServiceImpl.getAll();
		
		assertThat(list).isNotEmpty();
		assertThat(list.size()).isEqualTo(1);
	}

	@DisplayName("Test .create - Correct")
	@Test
	@Order(4)
	public void givenComercialBo_whenCreate_thenReturnSavedComercialBo() throws AlreadyExistsException {
		when(comercialRepository.existsById(comercialBo.getCodigo())).thenReturn(false);
		when(modelToBo.comercialToBo(comercial)).thenReturn(comercialBo);
		when(boToModel.boToComercial(comercialBo)).thenReturn(comercial);
		when(comercialRepository.save(comercial)).thenReturn(comercial);
		
		ComercialBo comercialCreate = comercialServiceImpl.create(comercialBo);
		
		assertThat(comercialCreate).isNotNull();
		assertThat(comercialCreate).isEqualTo(comercialBo);
	}

	@DisplayName("Test .create - Incorrect")
	@Test
	@Order(5)
	public void givenComercialBo_whenCreate_thenThrowAlreadyExistsException() {
		when(comercialRepository.existsById(comercialBo.getCodigo())).thenReturn(true);
		
		assertThrows(AlreadyExistsException.class, () -> comercialServiceImpl.create(comercialBo));
	}

	@DisplayName("Test .deleteById - Correct")
	@Test
	@Order(6)
	public void givenComercialId_whenDeleteById_thenRemoveComercialBo() throws NotFoundException {
		when(comercialRepository.existsById(1L)).thenReturn(true);
		
		comercialServiceImpl.deleteById(1L);
		
		verify(comercialRepository, times(1)).deleteById(1L);
	}

	@DisplayName("Test .deleteById - Incorrect")
	@Test
	@Order(7)
	public void givenComercialId_whenDeleteById_thenThrowNotFoundException() throws NotFoundException {
		when(comercialRepository.existsById(1L)).thenReturn(false);
	
		assertThrows(NotFoundException.class, () -> comercialServiceImpl.deleteById(1L));
	}

	@DisplayName("Test .getNumeroTotalComerciales")
	@Test
	@Order(8)
	public void givenNothing_whenGetNumeroTotalComerciales() {
		when(comercialRepository.count()).thenReturn(1L);
		
		Long numeroTotal = comercialServiceImpl.getNumeroTotalComerciales();
		
		assertThat(numeroTotal).isEqualByComparingTo(1L);
	}

	@DisplayName("Test .update - Correct")
	@Test
	@Order(9)
	public void givenComercialBo_whenUpdate_thenReturnUpdatedComercialBo() throws NotFoundException {
		when(comercialRepository.save(comercial)).thenReturn(comercial);
		when(modelToBo.comercialToBo(comercial)).thenReturn(comercialBo);
		when(boToModel.boToComercial(comercialBo)).thenReturn(comercial);
		when(comercialRepository.existsById(1L)).thenReturn(true);
		
		ComercialBo comercialUpdate = comercialServiceImpl.update(comercialBo);
		
		assertThat(comercialUpdate).isNotNull();
		assertThat(comercialUpdate).isEqualTo(comercialBo);
	}

	@DisplayName("Test .update - Incorrect")
	@Test
	@Order(10)
	public void givenComercialBo_whenUpdate_thenThrowNotFoundException() {
		when(comercialRepository.existsById(comercialBo.getCodigo())).thenReturn(false);
		
		assertThrows(NotFoundException.class, () -> comercialServiceImpl.update(comercialBo));
	}
}
