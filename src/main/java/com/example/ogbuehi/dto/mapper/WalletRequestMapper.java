package com.example.ogbuehi.dto.mapper;

import com.example.ogbuehi.dto.request.WalletRequest;
import com.example.ogbuehi.model.Wallet;
import com.example.ogbuehi.service.UserService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Mapper used for mapping WalletRequest fields
 */
@Mapper(componentModel = "spring",
uses = {UserService.class},
injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class WalletRequestMapper {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Mapping(target = "name", expression = "java(org.apache.commons.text.WordUtils.capitalizeFully(dto.getName()))")
    @Mapping(target = "iban", expression = "java(org.apache.commons.lang3.StringUtils.upperCase(dto.getIban()))")
    @Mapping(target = "user", ignore = true)
    public abstract Wallet toEntity(WalletRequest dto);

    public abstract WalletRequest toDto(Wallet entity);

    @AfterMapping
    void setToEntityFields(@MappingTarget Wallet entity, WalletRequest dto) {
        entity.setUser(userService.getReferenceById(dto.getUserId()));
    }

}
