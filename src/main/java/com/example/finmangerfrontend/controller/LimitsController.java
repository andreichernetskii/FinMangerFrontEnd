package com.example.finmangerfrontend.controller;

import com.example.finmangerfrontend.dto.Limit;
import com.example.finmangerfrontend.service.ApiService;
import com.example.finmangerfrontend.service.LimitsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Controller
public class LimitsController {
    private final LimitsService limitsService;
    private final ApiService apiService;

    public LimitsController( LimitsService limitsService, ApiService apiService ) {
        this.limitsService = limitsService;
        this.apiService = apiService;
    }

    @GetMapping( "/limits" )
    public String showLimits( Model model ) {
        List<Limit> limits = limitsService.getLimits();
        List<String> categories = apiService.getCategories();
        List<String> limitTypes = limitsService.getLimitTypes();
        model.addAttribute( "limits", limits );
        model.addAttribute( "categories", categories );
        model.addAttribute( "limitTypes", limitTypes );
        return "limits.html";
    }

    @PostMapping( "/add-new-limit" )
    public String addNewLimit( Limit limit ) {
        limitsService.addNewLimit( limit );
        return "redirect:/limits";
    }

    @PutMapping( "/update-limit" )
    public String updateLimit( Limit limit ) {
        limitsService.updateLimit( limit );
        return "redirect:/limits";
    }

    @PostMapping( "/delete-limit" )
    public String deleteLimit( Long id ) {
        limitsService.deleteLimit( id );
        return "redirect:/limits";
    }
}
