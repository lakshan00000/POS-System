package com.ijse.pointofsalesystem.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemReqDto {
     
    private String name;
    private Double price;
    private String description;
    private Long categoryId;

    
}
