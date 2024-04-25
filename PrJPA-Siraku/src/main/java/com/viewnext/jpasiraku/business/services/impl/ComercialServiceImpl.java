package com.viewnext.jpasiraku.business.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viewnext.jpasiraku.business.bo.ComercialBo;
import com.viewnext.jpasiraku.business.exception.AlreadyExistsException;
import com.viewnext.jpasiraku.business.exception.NotFoundException;
import com.viewnext.jpasiraku.business.services.ComercialService;
import com.viewnext.jpasiraku.converters.BoToModel;
import com.viewnext.jpasiraku.converters.ModelToBo;
import com.viewnext.jpasiraku.data.repository.ComercialRepository;

@Service
public class ComercialServiceImpl implements ComercialService {

	@Autowired
	ComercialRepository comercialRepository;

	@Autowired
	BoToModel boToModel;

	@Autowired
	ModelToBo modelToBo;

	@Override
	public ComercialBo getById(Long id) throws NotFoundException {
		return modelToBo.comercialToBo(comercialRepository.findById(id).orElseThrow(
				() -> new NotFoundException("No se ha encontrado un cliente con el identificador fiscal " + id + ".")));
	}

	@Override
	public List<ComercialBo> getAll() {
		return comercialRepository.findAll().stream().map(x -> modelToBo.comercialToBo(x)).toList();
	}

	@Override
	public Long getNumeroTotalComerciales() {
		return comercialRepository.count();
	}

	@Override
	public ComercialBo create(ComercialBo comercial) throws AlreadyExistsException {
		if (comercialRepository.existsById(comercial.getCodigo())) {
			throw new AlreadyExistsException("El comercial con codigo " + comercial.getCodigo() + " ya existe.");
		} else {
			return modelToBo.comercialToBo(comercialRepository.save(boToModel.boToComercial(comercial)));
		}
	}

	@Override
	public void deleteById(Long id) throws NotFoundException {
		if (comercialRepository.existsById(id)) {
			comercialRepository.deleteById(id);
		} else {
			throw new NotFoundException("No se ha encontrado un cliente con el identificador fiscal " + id + ".");
		}
	}

	@Override
	public ComercialBo update(ComercialBo comercialBo) throws NotFoundException {
		if (comercialRepository.existsById(comercialBo.getCodigo())) {
			return modelToBo.comercialToBo(
					comercialRepository.save(boToModel.boToComercial(new ComercialBo(comercialBo.getCodigo(),
							comercialBo.getNombre(), comercialBo.getApellido1(), comercialBo.getApellido2()))));
		} else {
			throw new NotFoundException(
					"No se ha encontrado un cliente con el identificador fiscal " + comercialBo.getCodigo() + ".");
		}
	}

}
