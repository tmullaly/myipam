package com.thunderhouse.myipam.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thunderhouse.myipam.model.Record;
import com.thunderhouse.myipam.repository.RecordRepository;
import com.thunderhouse.myipam.repository.ZoneRepository;

@Controller
public class RecordController {
	
    @Autowired
    private RecordRepository recordRepository;
    
    @Autowired
    private ZoneRepository zoneRepository;
    
    /**
     * Show the records of a zone
     */
    @RequestMapping("zones/{id}/records")
    public String showZoneRecords(@PathVariable long id, Model model) throws Exception {
    	model.addAttribute("zone", zoneRepository.findById(id).orElseThrow(()-> new Exception("zone not found")));
        model.addAttribute("records", recordRepository.findByZoneId(id));
        return "records/zone-records";
    }
    
    /**
     * Show the zone file to be pushed to bind
     */
    @GetMapping("zones/{id}/zonefile")
    public String showZoneFile(@PathVariable long id, Model model) throws Exception {
        model.addAttribute("zone", zoneRepository.findById(id).orElseThrow(()-> new Exception("zone not found")));
        model.addAttribute("records", recordRepository.findByZoneId(id));
        return "records/zoneshowfile";
    }
    
    /**
     * New record for zone.
     *
     * @param model
     * @return
     */
    @RequestMapping("zones/{zoneId}/record/new")
    public String newRecord(@PathVariable long zoneId, Model model) throws Exception {
    	model.addAttribute("zone", zoneRepository.findById(zoneId).orElseThrow(()-> new Exception("zone not found")));
        model.addAttribute("record", new Record());
        return "records/new-record";
    }

    /**
     * 
     * Create new record in database.
     * @throws Exception 
     * 
     **/
    @PostMapping(value = "zones/{zoneId}/record") 
    public String createRecord(@Valid Record record, BindingResult bindingResult, @PathVariable long zoneId, Model model) throws Exception {
    	System.out.println("###################");
    	System.out.println(bindingResult);
		if (bindingResult.hasErrors()) { 
			model.addAttribute("zone", zoneRepository.findById(zoneId).orElseThrow(()-> new Exception("zone not found")));
	        //model.addAttribute("record", new Record());
			return "records/new-record";
		}
        recordRepository.save(record);
        return "redirect:/zones/{zoneId}";
    }
    
    /**
     * 
     * Edit record.
     *
     */
    @GetMapping("zones/{zoneId}/record/{recordId}/edit")
    public String editRecord(@PathVariable long zoneId, @PathVariable long recordId, Model model) throws Exception {
    	model.addAttribute("zone", zoneRepository.findById(zoneId).orElseThrow(()-> new Exception("zone not found")));
        model.addAttribute("record", recordRepository.findById(recordId).orElseThrow(()-> new Exception("record not found")));
        return "records/edit-record";
    }
    
    /**
     * 
     * Update record.
     *
     */
    @PostMapping("zones/{zoneId}/record/{recordId}/update")
    public String updateZone(@PathVariable("zoneId") long zoneId, @PathVariable("recordId") long recordId, @Valid Record record, BindingResult result) {
        if (result.hasErrors()) {
            return "zones/edit-record";
        }
        
        recordRepository.save(record);
        return "redirect:/zones/{zoneId}";
    }
    
    /**
     *
     * Delete record by its id.
     *
     */
    @GetMapping("zones/{zoneId}/record/{recordId}/delete")
    public String deleteRecord(@PathVariable long zoneId, @PathVariable long recordId) {
    	Record record = recordRepository.findById(recordId).orElseThrow(() -> new IllegalArgumentException("Invalid record Id:" + recordId));
    	recordRepository.delete(record);
    	return "redirect:/zones/{zoneId}";
    }    
    
    
}
