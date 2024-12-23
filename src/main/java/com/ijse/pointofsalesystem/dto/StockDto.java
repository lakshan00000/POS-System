package com.ijse.pointofsalesystem.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockDto {
    private Long id;
    private Long itemId;
    private String itemName;
    private Integer quantity;
}
