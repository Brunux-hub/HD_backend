package api_healthy_pet.Converters;

import api_healthy_pet.Enums.EspecialidadVet;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Converter
public class EspecialidadListConverter implements AttributeConverter<List<EspecialidadVet>, String> {

    @Override
    public String convertToDatabaseColumn(List<EspecialidadVet> attribute) {
        if (attribute == null || attribute.isEmpty()) {
            return null;
        }
        return attribute.stream()
                .map(EspecialidadVet::name)
                .collect(Collectors.joining(","));
    }

    @Override
    public List<EspecialidadVet> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isBlank()) {
            return Collections.emptyList();
        }
        return Arrays.stream(dbData.split(","))
                .map(EspecialidadVet::valueOf)
                .toList();
    }
}
