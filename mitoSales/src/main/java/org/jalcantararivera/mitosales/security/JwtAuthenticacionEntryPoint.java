package org.jalcantararivera.mitosales.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jalcantararivera.mitosales.exception.CustomErrorResponse;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Component
public class JwtAuthenticacionEntryPoint  implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String exceptionMsg= (String) request.getAttribute("exception");
        if (exceptionMsg==null){
            exceptionMsg="Token not found or invalid";

        }
        CustomErrorResponse errorResponse= new CustomErrorResponse(LocalDateTime.now(),exceptionMsg,request.getRequestURI());
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write(converObjectToJson(errorResponse));
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    }

    //ALTERNATIVA : GSON
    private String converObjectToJson(Object object) throws JsonProcessingException{
        if(object==null){
            return null;
        }
        ObjectMapper mapper= new ObjectMapper();
        mapper.findAndRegisterModules();
        return mapper.writeValueAsString(object);
    }
}
