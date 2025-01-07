package com.example.ogbuehi.dto.response;
import com.example.ogbuehi.model.RoleType;
import lombok.Data;

@Data
public class RoleResponse {
    private Long id;
    private RoleType type;
}
