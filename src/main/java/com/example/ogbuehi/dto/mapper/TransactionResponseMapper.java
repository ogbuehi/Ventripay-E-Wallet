package com.example.ogbuehi.dto.mapper;

import com.example.ogbuehi.dto.response.TransactionResponse;

import com.example.ogbuehi.model.Transaction;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import static com.example.ogbuehi.common.Constants.DATE_TIME_FORMAT;


/**
 * Mapper used for mapping TransactionResponse fields
 */
@Mapper(componentModel = "spring")
public interface TransactionResponseMapper {
    Transaction toEntity(TransactionResponse dto);

    @Mapping(target = "createdAt", ignore = true)
    TransactionResponse toDto(Transaction entity);

    @AfterMapping
    default void formatCreatedAt(@MappingTarget TransactionResponse dto, Transaction entity) {
        LocalDateTime datetime = LocalDateTime.ofInstant(entity.getCreatedAt(), ZoneOffset.UTC);
        dto.setCreatedAt(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT).format(datetime));
    }
}
