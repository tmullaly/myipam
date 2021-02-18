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

import com.thunderhouse.myipam.model.Host;
import com.thunderhouse.myipam.repository.HostRepository;

@Controller
public class HostController {

    private HostRepository hostRepository;

    @Autowired
    public HostController(HostRepository hostRepository) {
        this.hostRepository = hostRepository;
    }

    @GetMapping("/hosts")
    public String getAllHosts(Model model) {
        model.addAttribute("hosts", hostRepository.findAll());
        return "hosts/list-hosts";
    }
    
    @GetMapping("/hosts/{id}")
    public String getHostById(@PathVariable("id") long id, Model model) {
        Host host = hostRepository.findById(id)
        		.orElseThrow(() -> new IllegalArgumentException("Invalid host Id:" + id));
        model.addAttribute("host", host);
        return "hosts/show-host";
    }
    
    @GetMapping("/hosts/new")
    public String newHostForm(Host host) {
        return "hosts/new-host";
    }
    
    @PostMapping("/hosts")
    public String createHost(@Valid Host host, BindingResult result) {
    	System.out.println("###################");
    	System.out.println(result);
        if (result.hasErrors()) {
            return "hosts/new-host";
        }
        hostRepository.save(host);
        return "redirect:/hosts";
    }
    
    


    

   
    
    

    @GetMapping("/hosts/{id}/edit")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Host host = hostRepository.findById(id)
        		.orElseThrow(() -> new IllegalArgumentException("Invalid host Id:" + id));
        model.addAttribute("host", host);
        return "hosts/edit-host";
    }

    @PostMapping("/hosts/{id}/update")
    public String updateHost(@PathVariable("id") long id, @Validated Host host, BindingResult result,
        Model model) {
        if (result.hasErrors()) {
            host.setId(id);
            return "hosts/update-host";
        }
        hostRepository.save(host);
        return "redirect:/hosts";
    }

    /**
    *
    * Delete record by its id.
    *
    */
   @GetMapping("hosts/{hostId}/delete")
   public String deleteRecord(@PathVariable long hostId) {
   	Host host = hostRepository.findById(hostId).orElseThrow(() -> new IllegalArgumentException("Invalid Host Id:" + hostId));
   	hostRepository.delete(host);
   	return "redirect:/hosts";
   } 

}