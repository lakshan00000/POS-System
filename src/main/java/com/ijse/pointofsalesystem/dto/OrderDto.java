package com.ijse.pointofsalesystem.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {
    private List<Long> itemIds;
    
}
