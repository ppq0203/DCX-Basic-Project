package project.shop.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

/**
 * 
 * @author jun
 * 상품을 판매할때 제품을 동시에 등록해야 하므로 상품글과 제품의 Dto를 합쳐서 구현함.
 */
@Data
public class SalesDto {
	private int salesNo;
	private int salesPrice;
	private String salesImg;
	private String salesTitle;
	private String salesContent;
	private int salesCount;
	private int userNo;
	private int productNo;
	private String productName;
	private String productCategory;
	private List<MultipartFile> imageFile;
	
}
