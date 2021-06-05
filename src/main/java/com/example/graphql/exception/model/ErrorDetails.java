package com.example.graphql.exception.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {
    @NonNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "EEEEE yyyy-MM-dd HH:mm:ss",locale = "en_GB")
    private Date errorTimestamp;
    @NonNull
    private String messageDetails;
    @NonNull
    private Integer errorCode;
    @NonNull
    private String invalidField;
}

