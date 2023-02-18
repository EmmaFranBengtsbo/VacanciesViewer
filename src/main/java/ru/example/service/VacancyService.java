package ru.example.service;

import org.springframework.stereotype.Service;
import ru.example.dto.Vacancies;
import ru.example.dto.VacancyDTO;
import ru.example.model.Vacancy;
import ru.example.repository.VacancyRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class VacancyService {

    VacancyRepository repository;

    public VacancyService(VacancyRepository repository) {
        this.repository = repository;
    }
    public VacancyDTO findById(int id) {
        Vacancy v = repository.findById(id);
        if (v != null) {
            return new VacancyDTO(v.getId(),v.getName(),v.getSalary(),v.getExperience(),v.getCity());
        } else {
            return null;
        }
    }

    public Vacancies findAll() {
        List<Vacancy> listOfVacancies = repository.findAll();
        if (listOfVacancies.isEmpty()) {
            return null;
        } else {
            List<VacancyDTO> dtos = new ArrayList<>();
            for (Vacancy v : listOfVacancies) {
                dtos.add(new VacancyDTO(v.getId(),v.getName(),v.getSalary(),v.getExperience(),v.getCity()));
            }
            return new Vacancies(dtos);
        }
    }

    public boolean delete(int id) {
        return repository.deleteById(id);
    }

    public boolean save(Vacancy vacancy) {
        return repository.save(vacancy) != null;
    }
}
