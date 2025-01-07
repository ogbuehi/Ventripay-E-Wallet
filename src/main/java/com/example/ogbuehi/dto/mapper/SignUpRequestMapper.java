package com.example.ogbuehi.dto.mapper;

import com.example.ogbuehi.dto.request.SignUpRequest;
import com.example.ogbuehi.model.Role;
import com.example.ogbuehi.model.RoleType;
import com.example.ogbuehi.model.User;
import com.example.ogbuehi.service.RoleService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;

/**
 * Mapper used for mapping SignupRequest fields
 */

@Mapper(componentModel = "spring",
        uses = {PasswordEncoder.class, RoleService.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)

public abstract class SignUpRequestMapper {

    private RoleService roleService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Mapping(target = "firstname", expression = "java(org.apache.commons.text.WordUtils.capitalizeFully(dto.getFirstName()))")
    @Mapping(target = "lastname", expression = "java(org.apache.commons.text.WordUtils.capitalizeFully(dto.getLastName()))")
    @Mapping(target = "username", expression = "java(dto.getUsername().trim().toLowerCase())")
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "roles", ignore = true)
    public abstract User toEntity(SignUpRequest dto);

    @AfterMapping
    void setToEntityFields(@MappingTarget User entity, SignUpRequest dto) {
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        final List<RoleType> roleTypes = dto.getRoles().stream()
                .map(RoleType::valueOf)
                .toList();
        final List<Role> roles = roleService.getReferenceByTypeIsIn(new HashSet<>(roleTypes));
        entity.setRoles(new HashSet<>(roles));
    }

}
