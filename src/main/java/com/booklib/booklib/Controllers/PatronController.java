package com.booklib.booklib.Controllers;


import com.booklib.booklib.Entities.Patron;
import com.booklib.booklib.Service.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/patrons")
public class PatronController {

    private final PatronService patronService;
    @Autowired
    public PatronController(PatronService patronService) {
        this.patronService = patronService;
    }

    @GetMapping
    public ResponseEntity<List<Patron>> getAllPatrons() {
        try {
            var PatronList = patronService.getAllPatrons();
            return ResponseEntity.ok(PatronList);
        }
        catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<Patron> createPatron(@RequestBody Patron patron) {
        try {
            var createdPatron = patronService.createPatron(patron);
            return ResponseEntity.ok(createdPatron);
        }
        catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patron> getPatronById(@PathVariable Long id) {
        Optional<Patron> patronOptional = Optional.ofNullable(patronService.getPatronById(id));
        return patronOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }


    @PutMapping("/{id}")
    public ResponseEntity<Patron> updateBook(@PathVariable Long id, @RequestBody Patron patronDetails) {
        try {
            Patron updatedPatron = patronService.updatePatron(id, patronDetails);
            return ResponseEntity.ok(updatedPatron);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletepatron(@PathVariable Long id) {
        patronService.deletePatron(id);
        return ResponseEntity.noContent().build();
    }


}
