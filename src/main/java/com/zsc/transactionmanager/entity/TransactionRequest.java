package com.zsc.transactionmanager.entity;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class TransactionRequest {
    private String id;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.00", message = "Amount must not smaller than 0")
    private BigDecimal amount;

    @NotBlank(message = "Type is required")
    @Pattern(regexp = "^(debit|credit)$", message = "Type must be either debit or credit")
    private String type;

    @Size(max = 126, message = "Description is too long, must not exceed 126 characters")
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
