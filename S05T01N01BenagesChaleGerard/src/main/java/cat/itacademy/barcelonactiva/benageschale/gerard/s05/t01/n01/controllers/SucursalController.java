package cat.itacademy.barcelonactiva.benageschale.gerard.s05.t01.n01.controllers;

import cat.itacademy.barcelonactiva.benageschale.gerard.s05.t01.n01.model.domain.Sucursal;
import cat.itacademy.barcelonactiva.benageschale.gerard.s05.t01.n01.model.dto.SucursalDTO;
import cat.itacademy.barcelonactiva.benageschale.gerard.s05.t01.n01.model.exceptions.SucursalNotFoundException;
import cat.itacademy.barcelonactiva.benageschale.gerard.s05.t01.n01.model.service.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/api/sucursals")
public class SucursalController {

    @Autowired
    private SucursalService sucursalService;

    @GetMapping(value = "/")
    public String home(){
        return "home";
    }

    @GetMapping(value = "/created")
    public String sucursalCreated(){
        return "sucursalCreated";
    }

    @GetMapping(value = "/add")
    public String createSucursal(Model model){
        Sucursal sucursal = new Sucursal();
        model.addAttribute("title", "Afegir sucursal");
        model.addAttribute("sucursal", sucursal);
        return "addSucursal";
    }

    @PostMapping(value = "/save")
    public String saveSucursal(@ModelAttribute Sucursal sucursal){

        sucursalService.saveSucursal(sucursal);
        return "redirect:/api/sucursals/created";
    }

    @GetMapping(value = "/getAll")
    public String getAllSucursal(Model model) {
        List<SucursalDTO> dtoSucursals = new ArrayList<SucursalDTO>();
        sucursalService.findAllBy().forEach(s-> dtoSucursals.add(sucursalService.sucursalConverter(s)));
        ResponseEntity<String> responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).body("La taula de sucursals és buida.");

        model.addAttribute("title", "Llista de sucursals");
        model.addAttribute("sucursals", dtoSucursals);
        model.addAttribute("miResponseEntity", responseEntity);
        return "sucursalList";
    }

    @GetMapping(value = "/getOne")
    public String serachSucursalByName(Model model, @RequestParam(value ="query", required = false)String q){
        List<SucursalDTO> dtoSucursals = new ArrayList<SucursalDTO>();
        List<SucursalDTO> filteredList = new ArrayList<SucursalDTO>();
        sucursalService.findAllBy().forEach(s-> dtoSucursals.add(sucursalService.sucursalConverter(s)));
        dtoSucursals.stream()
                .filter(fl -> fl.getSucursalName().contains(q))
                .forEach(filteredList::add);
        if(!filteredList.isEmpty()) {
            model.addAttribute("title", "Cerca: " + q + "...");
            model.addAttribute("sucursals", filteredList);
            return "sucursalList";
        }else{
            model.addAttribute("title", "Cerca: " + q + "... 0 Sucursals trobades.");
            return "emptySearch";
        }
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model) throws SucursalNotFoundException {
        Sucursal sucursal = sucursalService.findSucursal(id);

        model.addAttribute("title", "Editar sucursal");
        model.addAttribute("sucursal", sucursal);
        return "updateSucursal";
    }

    @PostMapping("/update/{id}")
    public String updateSucursalById(@PathVariable("id") long id, @ModelAttribute Sucursal sucursal) throws SucursalNotFoundException {
        sucursalService.updateSucursal(sucursal);
        return "redirect:/api/sucursals/getAll";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteSucursalById(@PathVariable("id") Long id, Model model) throws SucursalNotFoundException {
        Sucursal sucursal = sucursalService.findSucursal(id);
        sucursalService.deleteSucursal(id);
        return "redirect:/api/sucursals/getAll";
    }
}