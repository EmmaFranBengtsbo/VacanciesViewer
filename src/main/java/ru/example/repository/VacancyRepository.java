package ru.example.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.example.model.Vacancy;

import java.util.List;

@Repository
public class VacancyRepository{

    private static final Sort SORT_NAME = Sort.by(Sort.Direction.ASC, "name");

    private final VacancyJPARepository repository;

    public VacancyRepository(VacancyJPARepository repository) {
        this.repository = repository;
    }

    public Vacancy findById(int id) {
        return  repository.findById(id).orElse(null);
    }

    public List<Vacancy> findAll() {
        return (List<Vacancy>) repository.findAll(SORT_NAME);
    }

    @Modifying
    @Transactional
    public boolean deleteById(int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public Vacancy save(Vacancy vacancy) {
        return  repository.save(vacancy);
    }
}
