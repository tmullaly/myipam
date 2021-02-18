package com.thunderhouse.myipam.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.thunderhouse.myipam.model.Zone;
import com.thunderhouse.myipam.repository.ZoneRepository;

@Controller
public class ZoneController {

    private ZoneRepository zoneRepository;

    @Autowired
    public ZoneController(ZoneRepository zoneRepository) {
        this.zoneRepository = zoneRepository;
    }

    @GetMapping("/zones")
    public String getAllZones(Model model) {
        model.addAttribute("zones", zoneRepository.findAll());
        return "zones/list-zones";
    }
    
    @GetMapping("/zones/{id}")
    public String getZoneById(@PathVariable("id") long id, Model model) {
        Zone zone = zoneRepository.findById(id)
        		.orElseThrow(() -> new IllegalArgumentException("Invalid zone Id:" + id));
        model.addAttribute("zone", zone);
        return "zones/show-zone";
    }
    
    @GetMapping("/zones/new")
    public String newZoneForm(Zone zone) {
        return "zones/new-zone";
    }
    
    @PostMapping("/zones")
    public String createZone(@Valid Zone zone, BindingResult result) {
    	System.out.println("###################");
    	System.out.println(result);
        if (result.hasErrors()) {
            return "zones/new-zone";
        }
        zoneRepository.save(zone);
        return "redirect:/zones";
    }
    
    


    

   
    
    

    @GetMapping("/zones/{id}/edit")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Zone zone = zoneRepository.findById(id)
        		.orElseThrow(() -> new IllegalArgumentException("Invalid zone Id:" + id));
        model.addAttribute("zone", zone);
        return "zones/edit-zone";
    }

    @PostMapping("/zones/{id}/update")
    public String updateZone(@PathVariable("id") long id, @Validated Zone zone, BindingResult result,
        Model model) {
        if (result.hasErrors()) {
            zone.setId(id);
            return "zones/update-zone";
        }
        zone.setSerial(zone.updateSerial(zone.getSerial()));
        zoneRepository.save(zone);
        return "redirect:/zones";
    }

    /**
    *
    * Delete record by its id.
    *
    */
   @GetMapping("zones/{zoneId}/delete")
   public String deleteRecord(@PathVariable long zoneId) {
   	Zone zone = zoneRepository.findById(zoneId).orElseThrow(() -> new IllegalArgumentException("Invalid Zone Id:" + zoneId));
   	zoneRepository.delete(zone);
   	return "redirect:/zones";
   } 

}