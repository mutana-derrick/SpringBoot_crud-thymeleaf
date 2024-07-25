package com.springboot.CRUD.controllers;

import com.springboot.CRUD.models.Computers;
import com.springboot.CRUD.models.ComputersDto;
import com.springboot.CRUD.services.ComputerRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/computers")
public class Computerscontroller {

    @Autowired
    private ComputerRepository repo;

    @GetMapping({"","/"})
    public String showComputersList(Model model){
        List<Computers> computersList = repo.findAll(Sort.by(Sort.Direction.DESC,"id"));
        model.addAttribute("computers", computersList);
        return "computers/index";
    }


    @GetMapping({"/record"})
    public String recordComputersForm(Model model){
        ComputersDto computersDto = new ComputersDto();
        model.addAttribute("computersDto",computersDto);
        return "computers/RecordComputers";

    }



    @PostMapping({"/record"})
    public String recordComputers(@Valid @ModelAttribute ComputersDto computersDto, BindingResult result){

        if (result.hasErrors()){
            return "computers/RecordComputers";
        }

        Computers computer = new Computers();
        computer.setComputerName(computersDto.getComputerName());
        computer.setSerialNumber(computersDto.getSerialNumber());
        computer.setDate();

        repo.save(computer);

        return "redirect:/computers";

    }

    @GetMapping({"/edit"})
    public String showEditPage(Model model, @RequestParam int id){

        try{
            Computers computer = repo.findById(id).get();
            model.addAttribute("computer",computer);

            ComputersDto computersDto = new ComputersDto();

            computersDto.setId(computer.getId());
            computersDto.setComputerName(computer.getComputerName());
            computersDto.setSerialNumber(computer.getSerialNumber());
            computersDto.setDate(computer.getDate());

            model.addAttribute("computersDto",computersDto);

        }catch (Exception e){
            System.out.println("Exception: " + e.getMessage());
            return "redirect:/computers";
        }
        return "computers/EditComputers";
    }


    @PostMapping("/edit")
    public String updateComputer(Model model,@RequestParam int id,@Valid @ModelAttribute ComputersDto computersDto, BindingResult result) {

        if (result.hasErrors()) {
            // If there are validation errors, stay on the same page and show errors
            model.addAttribute("computersDto", computersDto);
            return "computers/EditComputers";
        }

        try {

            Computers existingComputer = repo.findById(computersDto.getId()).get();

            existingComputer.setComputerName(computersDto.getComputerName());
            existingComputer.setSerialNumber(computersDto.getSerialNumber());

            repo.save(existingComputer);

            return "redirect:/computers"; // Redirect to the computers list page after successful update
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return "redirect:/edit?id=" + computersDto.getId(); // Redirect back to edit page with error handling (optional)
        }
    }


    @GetMapping("/delete")
    public String deleteComputer(@RequestParam int id){
        try{
            Computers computers = repo.findById(id).get();

            repo.delete(computers);
        }catch (Exception e){
            System.out.println("Exception"+ e.getMessage());
        }
        return "redirect:/computers";

    }




}
