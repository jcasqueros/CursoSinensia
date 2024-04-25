package com.viewnext.jpasiraku.business.services.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
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

import com.viewnext.jpasiraku.business.bo.ClienteBo;
import com.viewnext.jpasiraku.business.exception.AlreadyExistsException;
import com.viewnext.jpasiraku.business.exception.NotFoundException;
import com.viewnext.jpasiraku.converters.BoToModel;
import com.viewnext.jpasiraku.converters.ModelToBo;
import com.viewnext.jpasiraku.data.model.Cliente;
import com.viewnext.jpasiraku.data.repository.ClienteRepository;

/**
 * Test mockeado de {@link ClienteServiceImpl}.
 * 
 * @author Mario Sánchez Pilar, Álvaro Ojalvo García y Jorge Casquero Sancho
 *
 */

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(OrderAnnotation.class)
class ClienteServiceTest {

	@Mock
	ClienteRepository clienteRepository;

	@Mock
	BoToModel boToModel;

	@Mock
	ModelToBo modelToBo;

	@InjectMocks
	ClienteServiceImpl clienteServiceImpl;

	private Cliente cliente;
	private ClienteBo clienteBo;

	@BeforeEach
	public void setup() {
		cliente = Cliente.builder().identificadorFiscal("1").nombreComercial("Comercial").nombre("Nombre")
				.apellido1("Apellido1").apellido2("Apellido2").build();
		clienteBo = ClienteBo.builder().identificadorFiscal("1").nombreComercial("Comercial").nombre("Nombre")
				.apellido1("Apellido1").apellido2("Apellido2").build();
	}

	@DisplayName("Test .getAll")
	@Test
	@Order(1)
	public void givenNothing_whenGetAll_thenReturnClienteBoList() {
		when(modelToBo.clienteToBo(cliente)).thenReturn(clienteBo);
		when(clienteRepository.findAll()).thenReturn(List.of(cliente));
		
		List<ClienteBo> list = clienteServiceImpl.getAll();
		
		assertThat(list).isNotEmpty();
		assertThat(list.size()).isEqualTo(1);
	}

	@DisplayName("Test .getById - Correct")
	@Test
	@Order(2)
	public void givenClienteId_whenGetById_thenReturnClienteBo() throws NotFoundException {
		when(modelToBo.clienteToBo(cliente)).thenReturn(clienteBo);
		when(clienteRepository.findById("1")).thenReturn(Optional.of(cliente));
		
		ClienteBo clienteGet = clienteServiceImpl.getById("1");
		
		assertThat(clienteGet).isNotNull();
		assertEquals("1", clienteGet.getIdentificadorFiscal());
		assertEquals("Comercial", clienteGet.getNombreComercial());
		assertEquals("Nombre", clienteGet.getNombre());
		assertEquals("Apellido1", clienteGet.getApellido1());
		assertEquals("Apellido2", clienteGet.getApellido2());
	}

	@DisplayName("Test .getById - Incorrect")
	@Test
	@Order(3)
	public void givenClienteId_whenGetById_thenThrowNotFoundException() {
		when(clienteRepository.findById("1")).thenReturn(Optional.empty());
		
		assertThrows(NotFoundException.class, () -> clienteServiceImpl.getById("1"));
	}

	@DisplayName("Test .create - Correct")
	@Test
	@Order(4)
	public void givenClienteBo_whenCreate_thenReturnSavedClienteBo() throws AlreadyExistsException {
		when(clienteRepository.existsById("1")).thenReturn(false);
		when(modelToBo.clienteToBo(cliente)).thenReturn(clienteBo);
		when(boToModel.boToCliente(clienteBo)).thenReturn(cliente);
		when(clienteRepository.save(cliente)).thenReturn(cliente);
		
		ClienteBo clienteSave = clienteServiceImpl.create(clienteBo);
		
		assertThat(clienteSave).isNotNull();
		assertEquals(clienteSave, clienteBo);
	}

	@DisplayName("Test .create - Incorrect")
	@Test
	@Order(5)
	public void givenClienteBo_whenCreate_thenThrowAlreadyExistsException() {
		when(clienteRepository.existsById(clienteBo.getIdentificadorFiscal())).thenReturn(true);
		
		assertThrows(AlreadyExistsException.class, () -> clienteServiceImpl.create(clienteBo));
	}

	@DisplayName("Test .deleteById - Correct")
	@Test
	@Order(6)
	public void givenClienteId_whenDeleteById_thenRemoveClienteBo() throws NotFoundException {
		when(clienteRepository.existsById("1")).thenReturn(true);
		
		clienteServiceImpl.deleteById("1");
		
		verify(clienteRepository, times(1)).deleteById("1");
	}

	@DisplayName("Test .deleteById - Incorrect")
	@Test
	@Order(7)
	public void givenClienteId_whenDeleteById_thenThrowNotFoundException() {
		when(clienteRepository.existsById("1")).thenReturn(false);
		
		assertThrows(NotFoundException.class, () -> clienteServiceImpl.deleteById("1"));
	}

}
