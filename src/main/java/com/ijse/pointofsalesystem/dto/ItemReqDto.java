package com.ijse.pointofsalesystem.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemReqDto {
     
    private String name;
    private double price;
    private int qty;
    private Long categoryId;

    
}
