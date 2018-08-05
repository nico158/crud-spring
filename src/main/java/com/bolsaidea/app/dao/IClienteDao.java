package com.bolsaidea.app.dao;

import com.bolsaidea.app.entity.Cliente;

import java.util.List;


public interface IClienteDao {
    public List<Cliente> findAll();
    public void save(Cliente cliente);
}
