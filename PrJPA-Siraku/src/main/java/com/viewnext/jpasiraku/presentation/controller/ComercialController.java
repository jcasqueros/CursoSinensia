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
import com.viewnext.jpasiraku.business.services.ComercialService;
import com.viewnext.jpasiraku.converters.BoToDto;
import com.viewnext.jpasiraku.converters.DtoToBo;
import com.viewnext.jpasiraku.presentation.dto.ComercialDto;
import com.viewnext.jpasiraku.presentation.dto.ComercialDtoAux;

/**
 * Controller de {@link ComercialController}.
 * <p>
 * En él se crean endpoints partiendo de la raíz '/cliente' para consultar los
 * métodos creados en {@link ComercialService}.
 * 
 * @author Mario Sánchez Pilar
 *
 */

@RestController
@RequestMapping("/comercial")
public class ComercialController {

	@Autowired
	ComercialService comercialService;

	@Autowired
	DtoToBo dtoToBo;

	@Autowired
	BoToDto boToDto;

	@GetMapping("/getById/{id}")
	public ComercialDto getbyId(@PathVariable Long id) throws NotFoundException {
		return boToDto.boToComercialDto(comercialService.getById(id));
	}

	@GetMapping("/getById/{id}/aux")
	public ComercialDtoAux getByIdAux(@PathVariable Long id) throws NotFoundException {
		return boToDto.boToComercialAux(comercialService.getById(id));
	}

	@GetMapping("/getAll")
	public List<ComercialDto> getAll() {
		return comercialService.getAll().stream().map(comercial -> boToDto.boToComercialDto(comercial)).toList();
	}

	@GetMapping("/getAll/aux")
	public List<ComercialDtoAux> getAllAux() {
		return comercialService.getAll().stream().map(comercial -> boToDto.boToComercialAux(comercial)).toList();
	}

	@GetMapping("/getTotal")
	public Long getTotal() {
		return comercialService.getNumeroTotalComerciales();
	}

	@PostMapping("/save")
	public ComercialDto save(@RequestBody ComercialDto comercial) throws AlreadyExistsException {
		return boToDto.boToComercialDto(comercialService.create(dtoToBo.dtoToComercialBo(comercial)));
	}

	@DeleteMapping("/delete/{id}")
	public void deleteById(@PathVariable Long id) throws NotFoundException {
		comercialService.deleteById(id);
	}
}
