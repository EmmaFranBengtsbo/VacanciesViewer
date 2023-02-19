package ru.example.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.example.dto.VacancyDTO;
import ru.example.model.Vacancy;

import java.util.Objects;

@Component
public class VacancyMapper {

    private final ModelMapper mapper = new ModelMapper();

    public Vacancy toEntity(VacancyDTO dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Vacancy.class);
    }

    public VacancyDTO toDto(Vacancy entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, VacancyDTO.class);
    }
}
