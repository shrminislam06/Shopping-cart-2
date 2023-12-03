package com.spingBoot.shoppingCart.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorApi {
    private String Error;
    private String Description;
    @DateTimeFormat
    private Date date;
}
