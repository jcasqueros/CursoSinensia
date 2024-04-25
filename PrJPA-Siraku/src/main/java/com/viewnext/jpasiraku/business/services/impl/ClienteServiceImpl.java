package com.viewnext.jpasiraku.business.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viewnext.jpasiraku.business.bo.ClienteBo;
import com.viewnext.jpasiraku.business.exception.AlreadyExistsException;
import com.viewnext.jpasiraku.business.exception.NotFoundException;
import com.viewnext.jpasiraku.business.services.ClienteService;
import com.viewnext.jpasiraku.converters.BoToModel;
import com.viewnext.jpasiraku.converters.ModelToBo;
import com.viewnext.jpasiraku.data.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	BoToModel boToModel;

	@Autowired
	ModelToBo modelToBo;

	@Override
	public List<ClienteBo> getAll() {
		return clienteRepository.findAll().stream().map(cliente -> modelToBo.clienteToBo(cliente)).toList();
	}

	@Override
	public ClienteBo getById(String id) throws NotFoundException {
		return modelToBo.clienteToBo(clienteRepository.findById(id).orElseThrow(
				() -> new NotFoundException("No se ha encontrado un cliente con el identificador fiscal " + id + ".")));
	}

	@Override
	public ClienteBo create(ClienteBo cliente) throws AlreadyExistsException {
		if (clienteRepository.existsById(cliente.getIdentificadorFiscal())) {
			throw new AlreadyExistsException(
					"El cliente con identificador fiscal " + cliente.getIdentificadorFiscal() + " ya existe.");
		} else {
			return modelToBo.clienteToBo(clienteRepository.save(boToModel.boToCliente(cliente)));
		}
	}

	@Override
	public void deleteById(String id) throws NotFoundException {
		if (clienteRepository.existsById(id)) {
			clienteRepository.deleteById(id);
		} else {
			throw new NotFoundException("No se ha encontrado un cliente con el identificador fiscal " + id + ".");
		}
	}

}
