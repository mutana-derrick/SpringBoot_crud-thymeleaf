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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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



}
