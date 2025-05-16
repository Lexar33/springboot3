package org.jalcantararivera.mitosales.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenericResponse<T> {
    private int status;
    private String message;
    //private T data;
    private List<T> data;
}
