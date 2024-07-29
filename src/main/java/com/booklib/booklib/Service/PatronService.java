package com.booklib.booklib.Service;

import com.booklib.booklib.Entities.Patron;
import com.booklib.booklib.Repositories.PatronRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatronService {
    private final PatronRepository patronRepository;
    @Autowired
    public PatronService(PatronRepository patronRepository) {
        this.patronRepository = patronRepository;
    }
    @Cacheable("patrons")
    public List<Patron> getAllPatrons() {
        return patronRepository.findAll();
    }
    @Cacheable("patron")
    public Patron getPatronById(Long id) {
        return  patronRepository.findById(id).orElse(null);
    }
    @Transactional
    public Patron createPatron(Patron patron) {
        return patronRepository.save(patron);
    }
    @Transactional
    public Patron updatePatron(Long id, Patron patron) {
        Optional<Patron> patronOptional = patronRepository.findById(id);
        if (patronOptional.isPresent()) {
            Patron patronToUpdate = patronOptional.get();
            patronToUpdate.setName(patron.getName());
            patronToUpdate.setContactInformation(patron.getContactInformation());
            return patronRepository.save(patronToUpdate);
        }
        else {

            throw new RuntimeException("patron not found with id " + id);
        }
    }
    @Transactional
    public void deletePatron(Long id) {
        patronRepository.deleteById(id);
    }
}
