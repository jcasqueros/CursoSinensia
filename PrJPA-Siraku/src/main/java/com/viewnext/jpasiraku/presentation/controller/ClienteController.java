package com.viewnext.jpasiraku.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.viewnext.jpasiraku.business.exception.AlreadyExistsException;
import com.viewnext.jpasiraku.business.exception.NotFoundException;
import com.viewnext.jpasiraku.business.services.ClienteService;
import com.viewnext.jpasiraku.converters.BoToDto;
import com.viewnext.jpasiraku.converters.DtoToBo;
import com.viewnext.jpasiraku.presentation.dto.ClienteDto;

import lombok.extern.slf4j.Slf4j;

/**
 * Controller de {@link ClienteController}.
 * <p>
 * En él se crean endpoints partiendo de la raíz '/cliente' para consultar los
 * métodos creados en {@link ClienteService}.
 * 
 * @author Mario Sánchez Pilar
 *
 */

@Slf4j
@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	ClienteService clienteService;

	@Autowired
	BoToDto boToDto;

	@Autowired
	DtoToBo dtoToBo;

	@GetMapping("/getAll")
	public List<ClienteDto> getAll() {
		return clienteService.getAll().stream().map(cliente -> boToDto.boToClienteDto(cliente)).toList();
	}

	@GetMapping("/getById/{id}")
	public ClienteDto getById(@PathVariable String id) throws NotFoundException {
		log.debug("Solicitud de consulta al cliente con id: " + id + ".");
		return boToDto.boToClienteDto(clienteService.getById(id));
	}

	@PostMapping("/save")
	public ClienteDto save(@RequestBody ClienteDto cliente) throws AlreadyExistsException {
		return boToDto.boToClienteDto(clienteService.create(dtoToBo.dtoToClienteBo(cliente)));
	}

	@DeleteMapping("/delete/{id}")
	public void deleteById(@PathVariable String id) throws NotFoundException {
		clienteService.deleteById(id);
	}

}
