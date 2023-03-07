package project.shop.dto;

import lombok.Data;

@Data
public class CustomerServiceDto {
	private String serviceTitle;
	private String serviceContent;
	private int writer;
	private int receiver;
}
