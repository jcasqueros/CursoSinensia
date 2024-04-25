package com.viewnext.jpasiraku.business.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viewnext.jpasiraku.business.bo.ProductoBo;
import com.viewnext.jpasiraku.business.exception.AlreadyExistsException;
import com.viewnext.jpasiraku.business.exception.NotFoundException;
import com.viewnext.jpasiraku.business.services.ProductoService;
import com.viewnext.jpasiraku.converters.BoToModel;
import com.viewnext.jpasiraku.converters.ModelToBo;
import com.viewnext.jpasiraku.data.repository.ProductoRepository;
import com.viewnext.jpasiraku.util.Familia;

@Service
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	ProductoRepository productoRepository;

	@Autowired
	BoToModel boToModel;

	@Autowired
	ModelToBo modelToBo;

	@Override
	public ProductoBo getById(Long id) throws NotFoundException {
		return modelToBo.productoToBo(productoRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("No se ha encontrado un producto con el id " + id + ".")));
	}

	@Override
	public List<ProductoBo> getAll() {
		return productoRepository.findAll().stream().map(producto -> modelToBo.productoToBo(producto)).toList();
	}

	@Override
	public List<ProductoBo> getByFamilia(Familia familia) {
		return productoRepository.findByFamilia(familia).stream().map(producto -> modelToBo.productoToBo(producto))
				.toList();
	}

	@Override
	public List<ProductoBo> getBetweenPriceRange(double min, double max) {
		return productoRepository.findByPrecioBetween(min, max).stream()
				.map(producto -> modelToBo.productoToBo(producto)).toList();
	}

	@Override
	public List<ProductoBo> getBetweenDates(Date desde, Date hasta) {
		return productoRepository.findByFechaAltaBetween(desde, hasta).stream()
				.map(producto -> modelToBo.productoToBo(producto)).toList();
	}

	@Override
	public Long getTotal() {
		return productoRepository.count();
	}

	@Override
	public ProductoBo create(ProductoBo producto) throws AlreadyExistsException {
		if (productoRepository.existsById(producto.getCodigo())) {
			throw new AlreadyExistsException("El producto con codigo " + producto.getCodigo() + " ya existe.");
		} else {
			return modelToBo.productoToBo(productoRepository.save(boToModel.boToProducto(producto)));
		}
	}

}
