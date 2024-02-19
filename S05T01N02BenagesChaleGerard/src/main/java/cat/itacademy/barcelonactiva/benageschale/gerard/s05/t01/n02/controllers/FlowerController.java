package cat.itacademy.barcelonactiva.benageschale.gerard.s05.t01.n02.controllers;

import cat.itacademy.barcelonactiva.benageschale.gerard.s05.t01.n02.model.domain.FlowerEntity;
import cat.itacademy.barcelonactiva.benageschale.gerard.s05.t01.n02.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.benageschale.gerard.s05.t01.n02.model.exceptions.FlowerNotFoundException;
import cat.itacademy.barcelonactiva.benageschale.gerard.s05.t01.n02.model.service.FlowerService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/api/flowers")
@Controller
public class FlowerController {

    @Autowired
    private FlowerService flowerService;

    @Operation(summary = "Add a new flower")
    @PostMapping(value = "/swagger/flower/add")
    public ResponseEntity<String> createFruit(@RequestBody FlowerEntity flower) {
        flowerService.saveFlower(flower);
        return new ResponseEntity<>("Fruita afegida amb èxit", HttpStatus.CREATED);
    }
    @Operation(summary = "Delete flower")
    @DeleteMapping(value = "/swagger/delete/{id}")
    public ResponseEntity<String> deleteFruitById(@PathVariable("id") Long id) throws FlowerNotFoundException{
        FlowerEntity flower = flowerService.findFlower(id);
        flowerService.deleteFlower(id);
        return new ResponseEntity<>("Fruita eliminada amb èxit", HttpStatus.GONE);

    }
    @Operation(summary = "Get all flower")
    @GetMapping(value = "/swagger/getAll")
    public ResponseEntity<List<FlowerDTO>> getAllFruits(){
        List<FlowerDTO> dtoFlowers = new ArrayList<FlowerDTO>();
            flowerService.findAllBy().forEach(s -> dtoFlowers.add(flowerService.flowerConverter(s)));

        return new ResponseEntity<>(dtoFlowers, HttpStatus.OK);
    }

    @Operation(summary = "Get one flower")
    @GetMapping("/swagger/getOne/{id}")
    public ResponseEntity<FlowerDTO> searchFruitById(@PathVariable("id") Long id) throws FlowerNotFoundException {
        FlowerEntity flower = flowerService.findFlower(id);
        FlowerDTO flowerDTO = new FlowerDTO(flower.getId(), flower.getFlowerName(),flower.getFlowerCountry());
        return new ResponseEntity<>(flowerDTO, HttpStatus.FOUND);
    }


    @Operation(summary = "Update flower")
    @PutMapping("/swagger/update/{id}")
    public ResponseEntity<String> updateFruitById(@PathVariable("id") long id, @RequestBody FlowerEntity updateFlower) throws FlowerNotFoundException {
        FlowerEntity flower = flowerService.findFlower(id);

        if (updateFlower != null) {
            flower.setFlowerName(updateFlower.getFlowerName());
            flower.setFlowerCountry(updateFlower.getFlowerCountry());
            flowerService.updateFlower(flower);
            return new ResponseEntity<>("Canvis realitzats amb èxit", HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("Sense canvis", HttpStatus.NOT_MODIFIED);
    }

    @GetMapping(value = "/")
    public String home() {
        return "home";
    }

    @GetMapping(value = "/created")
    public String flowerCreated() {
        return "flowerCreated";
    }

    @GetMapping(value = "/add")
    public String createFlower(Model model) {
        FlowerEntity flower = new FlowerEntity();
        model.addAttribute("title", "Afegir flor");
        model.addAttribute("flower", flower);
        return "addFlower";
    }

    @PostMapping(value = "/save")
    public String saveFlower(@ModelAttribute FlowerEntity flower) {

        flowerService.saveFlower(flower);
        return "redirect:/api/flowers/created";
    }

    @GetMapping(value = "/getAll")
    public String getAllFlower(Model model) {
        List<FlowerDTO> dtoFlowers = new ArrayList<FlowerDTO>();
        flowerService.findAllBy().forEach(s -> dtoFlowers.add(flowerService.flowerConverter(s)));
        ResponseEntity<String> responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).body("La taula de flors és buida.");

        model.addAttribute("title", "Llista de flors");
        model.addAttribute("flowers", dtoFlowers);
        model.addAttribute("miResponseEntity", responseEntity);
        return "flowerList";
    }

    @GetMapping(value = "/getOne")
    public String serachFlowerByName(Model model, @RequestParam(value = "query", required = false) String q) {
        List<FlowerDTO> dtoFlowers = new ArrayList<FlowerDTO>();
        List<FlowerDTO> filteredList = new ArrayList<FlowerDTO>();
        flowerService.findAllBy().forEach(s -> dtoFlowers.add(flowerService.flowerConverter(s)));
        dtoFlowers.stream()
                .filter(fl -> fl.getFlowerName().contains(q))
                .forEach(filteredList::add);
        if (!filteredList.isEmpty()) {
            model.addAttribute("title", "Cerca: " + q + "...");
            model.addAttribute("flowers", filteredList);
            return "flowerList";
        } else {
            model.addAttribute("title", "Cerca: " + q + "... 0 Flors trobades.");
            return "emptySearch";
        }
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model) throws FlowerNotFoundException {
        FlowerEntity flower = flowerService.findFlower(id);

        model.addAttribute("title", "Editar flor");
        model.addAttribute("flower", flower);
        return "updateFlower";
    }

    @PostMapping("/update/{id}")
    public String updateFlowerById(@PathVariable("id") long id, @ModelAttribute FlowerEntity flower) throws FlowerNotFoundException {
        flowerService.updateFlower(flower);
        return "redirect:/api/flowers/getAll";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteFlowerById(@PathVariable("id") Long id, Model model) throws FlowerNotFoundException {
        FlowerEntity flower = flowerService.findFlower(id);
        flowerService.deleteFlower(id);
        return "redirect:/api/flowers/getAll";
    }

}