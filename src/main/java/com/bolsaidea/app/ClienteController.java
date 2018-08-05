package com.bolsaidea.app;

import com.bolsaidea.app.dao.IClienteDao;
import com.bolsaidea.app.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class ClienteController {
    @Autowired
    private IClienteDao clienteDao;
    @RequestMapping(value="listar",method = RequestMethod.GET)
    public String listar(Model model){
        model.addAttribute("titulo","Listado Clientes");
        model.addAttribute("clientes",clienteDao.findAll());
        return "listar";
    }
    @RequestMapping(value="/form")
    public String crear(Map<String,Object> model){
        Cliente cliente = new Cliente();
        model.put("cliente",cliente);
        model.put("titulo","Formulario de cliente");
        return "form";
    }
    @RequestMapping(value="/form", method=RequestMethod.POST)
    public String guardar(@Valid Cliente cliente, BindingResult result){
        if(result.hasErrors()){
            return "form";
        }
        clienteDao.save(cliente);
        return "redirect:listar";
    }
}
