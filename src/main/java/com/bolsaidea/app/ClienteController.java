package com.bolsaidea.app;

import com.bolsaidea.app.dao.IClienteDao;
import com.bolsaidea.app.entity.Cliente;
import com.bolsaidea.app.paginator.PageRender;
import com.bolsaidea.app.services.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.Map;

@Controller
@SessionAttributes("cliente")
public class ClienteController {
    @Autowired
    private IClienteService clienteService;
    @RequestMapping(value="listar",method = RequestMethod.GET)
    public String listar(@RequestParam(name="page",defaultValue ="0" ) int page, Model model){
        Pageable pageRequest = new PageRequest(page,5);
        Page<Cliente> clientes = clienteService.findAll(pageRequest);
        PageRender<Cliente> pageRender = new PageRender<Cliente>("/listar", clientes);
        model.addAttribute("titulo","Listado Clientes");
        model.addAttribute("clientes",clientes);
        model.addAttribute("page",pageRender);
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
    public String guardar(@Valid Cliente cliente, BindingResult result, Model model, SessionStatus status){
        if(result.hasErrors()){
            model.addAttribute("titulo", "Formulario de Cliente");
            return "form";
        }
        clienteService.save(cliente);
        status.setComplete();
        return "redirect:listar";
    }
    @RequestMapping(value="/form/{id}")
    public String editar(@PathVariable(value="id") Long id, Map<String,Object> model){
        Cliente cliente = null;
        if (id > 0) {

            cliente = clienteService.findOne(id);
        }else{
            return "redirect:/listar";
        }
        model.put("cliente",cliente);
        model.put("titulo","Editar cliente");
        return "form";
    }
    @RequestMapping(value="/eliminar/{id}")
    public String eliminar(@PathVariable(value="id") Long id){
        if( id > 0){
            clienteService.delete(id);
        }
        return "redirect:/listar";
    }

}
