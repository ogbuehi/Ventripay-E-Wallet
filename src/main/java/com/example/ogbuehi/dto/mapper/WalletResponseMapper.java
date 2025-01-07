package com.example.ogbuehi.dto.mapper;


import com.example.ogbuehi.dto.response.UserResponse;
import com.example.ogbuehi.dto.response.WalletResponse;
import com.example.ogbuehi.model.User;
import com.example.ogbuehi.model.Wallet;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.text.MessageFormat;

/**
 * Mapper used for mapping WalletResponse fields
 */
@Mapper(componentModel = "spring")
public interface WalletResponseMapper {
    Wallet toEntity(WalletResponse dto);

    WalletResponse toDto(Wallet entity);

    @AfterMapping
    default void setFullName(@MappingTarget UserResponse dto, User entity) {
        dto.setFullName(MessageFormat.format("{0} {1}", entity.getFirstname(), entity.getFirstname()));
    }
}
