package com.cscb845f89497.sqlinjection;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDTO {

    private String username;
    private String customerId;
    private String accNumber;
    private String branchId;
    private String password;
    private BigDecimal balance;
}
