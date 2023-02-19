package ru.example.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.example.dto.Vacancies;
import ru.example.dto.VacancyDTO;
import ru.example.mapper.VacancyMapper;
import ru.example.model.Vacancy;
import ru.example.repository.VacancyRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VacancyService {

    private final VacancyRepository repository;

    private final VacancyMapper mapper;

    public VacancyService(VacancyRepository repository, VacancyMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public VacancyDTO findById(int id) {
        Vacancy vacancy = repository.findById(id);
        if (vacancy != null) {
            return  mapper.toDto(vacancy);
        } else {
            return null;
        }
    }

    public Vacancies findAll() {
        List<Vacancy> listOfVacancies = repository.findAll();
        if (listOfVacancies.isEmpty()) {
            return null;
        } else {
            List<VacancyDTO> dtos = listOfVacancies.stream()
                    .map(mapper::toDto)
                    .collect(Collectors.toList());
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
