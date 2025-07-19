package com.priyanka.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "Customer", description = "schema to hold customer info")
public class CustomerDto {
    @Schema(description = "email of customer ", example = "priyanka@gmail.com")
    @NotNull(message = "email can't be null")
    @Email(message = "please enter valid email")
    private String email;

    @Schema(description = "name of customer ", example = "priyanka gaikwad")
    @NotNull(message = "name can't be null")
    @Size(min = 5, max = 30, message = "enter name in between 5 to 30 characters")
    private String name;

    @Schema(description = "mobilenumber of customer ", example = "9999999999")
    @NotNull(message = "mobile number can't be null")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "please enter valid mobile number")
    private String mobileNumber;

    @Schema(hidden = true)
    private AccountsDto accountsDto;
}
