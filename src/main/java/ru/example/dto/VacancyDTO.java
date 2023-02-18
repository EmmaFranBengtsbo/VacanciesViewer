package ru.example.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.io.Serializable;
import java.util.Objects;

@JacksonXmlRootElement(localName = "vacancy")
public class VacancyDTO implements Serializable {

    public static final long serialVersionUID = 21L;

    @JacksonXmlProperty
    private Integer id;

    @JacksonXmlProperty
    private String name;

    @JacksonXmlProperty
    private Integer salary;

    @JacksonXmlProperty
    private String experience;

    @JacksonXmlProperty
    private String city;

    public VacancyDTO() {
    }
    public VacancyDTO(Integer id, String name, int salary, String experience, String city) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.experience = experience;
        this.city = city;
    }

    public int getId() {
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

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "VacancyDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", experience='" + experience + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VacancyDTO that = (VacancyDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(salary, that.salary) && Objects.equals(experience, that.experience) && Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, salary, experience, city);
    }
}
