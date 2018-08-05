package com.bolsaidea.app.dao;

import com.bolsaidea.app.entity.Cliente;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class ClienteDaoImpl implements IClienteDao {
    @PersistenceContext
    private EntityManager em;
    @Transactional
    @Override
    public List<Cliente> findAll() {
        return em.createQuery(" from Cliente").getResultList();

    }

    @Override
    @Transactional
    public void save(Cliente cliente) {
        em.persist(cliente);
    }

}