package ru.example.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "vacancies")
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    @NotBlank
    @Size(min = 2, max = 50)
    private String name;

    @Column(name = "salary")
    @Range(max = 10000000)
    private Integer salary;

    @Column(name = "experience")
    @Size(max = 50)
    private String experience;

    @Column(name = "city")
    @Size(max = 30)
    private String city;

    public Vacancy() {}

    public Vacancy(Integer id, String name, Integer salary, String experience, String city) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.experience = experience;
        this.city = city;
    }

    public Vacancy(String name, Integer salary, String experience, String city) {
        this(null, name, salary, experience, city);
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    public String getExperience() {
        return experience;
    }

    public String getCity() {
        return city;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(int salaryLevel) {
        this.salary = salaryLevel;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Vacancy{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", experience='" + experience + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
