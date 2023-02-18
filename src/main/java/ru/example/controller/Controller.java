package ru.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.example.dto.Vacancies;
import ru.example.dto.VacancyDTO;
import ru.example.model.Vacancy;
import ru.example.service.VacancyService;
import ru.example.util.exception.BadRequestException;
import ru.example.util.exception.NotFoundException;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/vacancy", produces = MediaType.APPLICATION_XML_VALUE)
public class Controller {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final VacancyService service;

    public Controller(VacancyService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public VacancyDTO get(@PathVariable int id) {
        log.info("get vacancy with id {}", id);
        VacancyDTO vacancy = service.findById(id);
        if (vacancy == null) {
            throw new NotFoundException("No vacancy with id "+ id);
        } else {
            return vacancy;
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Vacancy> put(@Valid @RequestBody VacancyDTO to) {
        log.info("put vacancy");
        Vacancy vacancy = new Vacancy(to.getName(), to.getSalary(), to.getExperience(), to.getCity());
        if (!service.save(vacancy)) {
            throw new BadRequestException("Bad request");
        }
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/vacancy" + "/{id}")
                .buildAndExpand(vacancy.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(vacancy);
    }

    @GetMapping
    public Vacancies getAll() {
        log.info("get all vacancies");
        return service.findAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete vacancy with id {}", id);
        if (!service.delete(id)) {
            throw new NotFoundException("No vacancy with id " + id);
        }
    }
}