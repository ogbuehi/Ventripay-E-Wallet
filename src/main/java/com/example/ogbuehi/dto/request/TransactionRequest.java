package com.example.ogbuehi.dto.request;

import com.example.ogbuehi.model.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {
    private Long id;

    @NotNull(message = "{amount.notnull}")
    private BigDecimal amount;

    @Size(max = 50, message = "{description.size}")
    private String description;

    private Instant createdAt;

    private UUID referenceNumber;

    private Status status;

    @NotBlank(message = "fromWalletIban.notblank}")
    private String fromWalletIban;

    @NotBlank(message = "{toWalletIban.notblank}")
    private String toWalletIban;

    @NotNull(message = "{typeId.notnull}")
    private Long typeId;
}
