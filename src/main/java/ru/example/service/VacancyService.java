package ru.example.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.example.dto.Vacancies;
import ru.example.dto.VacancyDTO;
import ru.example.model.Vacancy;
import ru.example.repository.VacancyRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VacancyService {

    VacancyRepository repository;

    public VacancyService(VacancyRepository repository) {
        this.repository = repository;
    }
    public VacancyDTO findById(int id) {
        Vacancy vacancy = repository.findById(id);
        if (vacancy != null) {
            ModelMapper modelMapper = new ModelMapper();
            return  modelMapper.map(vacancy, VacancyDTO.class);
        } else {
            return null;
        }
    }

    public Vacancies findAll() {
        List<Vacancy> listOfVacancies = repository.findAll();
        if (listOfVacancies.isEmpty()) {
            return null;
        } else {
            ModelMapper modelMapper = new ModelMapper();
            List<VacancyDTO> dtos = listOfVacancies.stream()
                    .map(vacancy -> modelMapper.map(vacancy, VacancyDTO.class))
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
