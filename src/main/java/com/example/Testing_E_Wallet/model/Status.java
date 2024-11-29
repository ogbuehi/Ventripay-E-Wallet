package com.example.Testing_E_Wallet.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {
    PENDING("Pending"),
    SUCCESS("Success"),
    ERROR("Error");

    private String label;
}
