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
public class CategoryDTO {

    private Integer idCategory;
    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min=3,max=30)
    //@Pattern(regexp="[0-9]+")
    //@Email
    private String nameofCategory;
    @NotNull
    private String descriptionCategory;
    @NotNull
    private boolean enabledCategory;
    /*
    @Max(value=100)
    @Min(value=2)
    private int age;
    */
}
