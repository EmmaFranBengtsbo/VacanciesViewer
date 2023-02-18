package ru.example.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.ArrayList;
import java.util.List;

@JacksonXmlRootElement
public class Vacancies {

    public static final long serialVersionUID = 22L;

    @JacksonXmlProperty(localName = "vacancy")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<VacancyDTO> vacancies = new ArrayList<>();

    public Vacancies() {
    }
    public Vacancies(List<VacancyDTO> vacancies) {
        this.vacancies = vacancies;
    }

    public List<VacancyDTO> getVacancies() {
        return vacancies;
    }

    public void setVacancies(List<VacancyDTO> vacancyDTOList) {
        this.vacancies = vacancyDTOList;
    }
}
