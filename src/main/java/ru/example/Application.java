package ru.example;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.example.model.Vacancy;
import ru.example.repository.VacancyRepository;

@SpringBootApplication(scanBasePackages = "ru.example")
public class Application implements ApplicationRunner {
    private final VacancyRepository repository;

    public Application(VacancyRepository repository) {
        this.repository = repository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        repository.save(new Vacancy("Повар",50000, "1 год","Москва"));
        repository.save(new Vacancy("Официант",40000, "без опыта","Санкт-Петербург"));
        repository.save(new Vacancy("Курьер",50000, "","Нижний Новгород"));
        repository.save(new Vacancy("Accountant",60000, "3 years","New York"));
    }
}
