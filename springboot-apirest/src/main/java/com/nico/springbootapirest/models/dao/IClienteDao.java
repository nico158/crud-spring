package com.nico.springbootapirest.models.dao;

import com.nico.springbootapirest.models.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface IClienteDao extends CrudRepository<Cliente, Long> {

}
