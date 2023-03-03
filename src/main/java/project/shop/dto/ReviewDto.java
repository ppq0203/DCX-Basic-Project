package project.shop.dto;

import lombok.Data;

@Data
public class ReviewDto {
	private String reviewTitle;
	private String reviewContent;
	private int reviewScore;
	private int salesNo;
	private int userNo;
	
}
