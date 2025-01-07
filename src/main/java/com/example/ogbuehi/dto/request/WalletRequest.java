package com.example.ogbuehi.dto.request;


import com.example.ogbuehi.validator.ValidIban;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletRequest {
    private Long id;

    @ValidIban(message = "{iban.valid}")
    @NotBlank(message = "{iban.notblank}")
    private String iban;

    @Size(min = 3, max = 50, message = "{name.size}")
    @NotBlank(message = "{name.notblank}")
    private String name;

    @NotNull(message = "{balance.notnull}")
    private BigDecimal balance;

    @NotNull(message = "{userId.notnull}")
    private Long userId;
}
