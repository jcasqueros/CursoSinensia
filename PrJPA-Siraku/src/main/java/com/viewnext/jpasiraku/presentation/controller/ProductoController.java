package com.viewnext.jpasiraku.presentation.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.viewnext.jpasiraku.business.exception.AlreadyExistsException;
import com.viewnext.jpasiraku.business.exception.NotFoundException;
import com.viewnext.jpasiraku.business.services.ProductoService;
import com.viewnext.jpasiraku.converters.BoToDto;
import com.viewnext.jpasiraku.converters.DtoToBo;
import com.viewnext.jpasiraku.presentation.dto.ProductoDto;

/**
 * Controller de {@link ProductoController}.
 * <p>
 * En él se crean endpoints partiendo de la raíz '/cliente' para consultar los
 * métodos creados en {@link ProductoService}.
 * 
 * @author Mario Sánchez Pilar
 *
 */

@RestController
@RequestMapping("/producto")
public class ProductoController {

	@Autowired
	ProductoService productoService;

	@Autowired
	DtoToBo dtoToBo;

	@Autowired
	BoToDto boToDto;

	@GetMapping("/get/{id}")
	public ProductoDto get(@PathVariable Long id) throws NotFoundException {
		return boToDto.boToProductoDto(productoService.getById(id));
	}

	@GetMapping("/getAll")
	public List<ProductoDto> getAll() {
		return productoService.getAll().stream().map(producto -> boToDto.boToProductoDto(producto)).toList();
	}

	@GetMapping("/getByPrices/{min}/{max}")
	public List<ProductoDto> getPrices(@PathVariable double min, @PathVariable double max) {
		return productoService.getBetweenPriceRange(min, max).stream()
				.map(producto -> boToDto.boToProductoDto(producto)).toList();
	}

	@GetMapping("/getByDates/{desde}/{hasta}")
	public List<ProductoDto> getDates(@PathVariable Date desde, @PathVariable Date hasta) {
		return productoService.getBetweenDates(desde, hasta).stream().map(producto -> boToDto.boToProductoDto(producto))
				.toList();
	}

	@GetMapping("/getTotal")
	public Long getTotal() {
		return productoService.getTotal();
	}

	@PostMapping("/save")
	public ProductoDto save(@RequestBody ProductoDto producto) throws AlreadyExistsException {
		return boToDto.boToProductoDto(productoService.create(dtoToBo.dtoToProductoBo(producto)));
	}

}
