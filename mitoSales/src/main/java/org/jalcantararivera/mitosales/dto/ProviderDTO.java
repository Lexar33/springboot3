package org.jalcantararivera.mitosales.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProviderDTO {
    private Integer idProvider;
    @NotNull
    @Size(min=3)
    private String nameProvider;
    @NotNull
    @Size(min=3)
    private String addressProvider;
    @NotNull
    private boolean enabledProvider;

}
