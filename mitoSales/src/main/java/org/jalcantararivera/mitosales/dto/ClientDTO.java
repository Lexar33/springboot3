package org.jalcantararivera.mitosales.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientDTO {
    private Integer idClient;
    @NotNull
    @Size(min=3,max=10)
    private String firstName;
    @NotNull
    @Size(min=3,max=10)
    private String lastName;
    @NotNull
    @Size(min=10,max=10)
    private String cardId;
    @NotNull
    @Pattern(regexp = "[0-9]+")
    private String phoneNumber;
    @Email
    @NotNull
    private String email;
    @NotNull
    @Size(min=1,max=100)
    private String address;
    @NotNull
    @Size(min=3,max=10)
    private String country;

}
