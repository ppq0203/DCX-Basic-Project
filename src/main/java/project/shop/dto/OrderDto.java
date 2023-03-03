package project.shop.dto;

import java.util.Date;

import lombok.Data;

@Data
public class OrderDto {
	private Date orderDate;
	private int salesNo;
	private int userNo;
}
