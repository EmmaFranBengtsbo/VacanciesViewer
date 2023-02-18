package ru.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.example.model.Vacancy;

@Transactional(readOnly = true)
public interface VacancyJPARepository extends JpaRepository<Vacancy, Integer> {
}
