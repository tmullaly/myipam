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

import com.thunderhouse.myipam.model.DnsServer;
import com.thunderhouse.myipam.repository.DnsServerRepository;

@Controller
public class DnsServerController {

    private DnsServerRepository dnsServerRepository;

    @Autowired
    public DnsServerController(DnsServerRepository dnsServerRepository) {
        this.dnsServerRepository = dnsServerRepository;
    }

    @GetMapping("/dns-servers")
    public String getAllDnsServers(Model model) {
        model.addAttribute("dnsServers", dnsServerRepository.findAll());
        return "dns-servers/list-dns-servers";
    }
    
    @GetMapping("/dns-servers/{id}")
    public String getDnsServerById(@PathVariable("id") long id, Model model) {
        DnsServer dnsServer = dnsServerRepository.findById(id)
        		.orElseThrow(() -> new IllegalArgumentException("Invalid dnsServer Id:" + id));
        model.addAttribute("dnsServer", dnsServer);
        return "dns-servers/show-dns-server";
    }
    
    @GetMapping("/dns-servers/new")
    public String newDnsServerForm(DnsServer dnsServer) {
        return "dns-servers/new-dns-server";
    }
    
    @PostMapping("/dns-servers")
    public String createDnsServer(@Valid DnsServer dnsServer, BindingResult result) {
        if (result.hasErrors()) {
            return "dns-servers/new-dns-server";
        }
        dnsServerRepository.save(dnsServer);
        return "redirect:/dns-servers";
    }

    @GetMapping("/dns-servers/{id}/edit")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        DnsServer dnsServer = dnsServerRepository.findById(id)
        		.orElseThrow(() -> new IllegalArgumentException("Invalid dnsServer Id:" + id));
        model.addAttribute("dnsServer", dnsServer);
        return "dns-servers/edit-dns-server";
    }

    @PostMapping("/dns-servers/{id}/update")
    public String updateDnsServer(@PathVariable("id") long id, @Validated DnsServer dnsServer, BindingResult result,
        Model model) {
        if (result.hasErrors()) {
            dnsServer.setId(id);
            return "dns-servers/update-dns-server";
        }
        dnsServerRepository.save(dnsServer);
        return "redirect:/dns-servers";
    }

    /**
    *
    * Delete record by its id.
    *
    */
   @GetMapping("dns-servers/{id}/delete")
   public String deleteRecord(@PathVariable long id) {
   	DnsServer dnsServer = dnsServerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid DnsServer Id:" + id));
   	dnsServerRepository.delete(dnsServer);
   	return "redirect:/dns-servers";
   } 

}