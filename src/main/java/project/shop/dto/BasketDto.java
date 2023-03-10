package project.shop.dto;

import lombok.Data;

@Data
public class BasketDto {
	private int salesNo;
	private int amount;
	
	@Override
    public boolean equals(Object obj) {
//        return super.equals(obj); 기존 Object클래스 구현 내용
        if (this == obj) return true; //같은 객체를 참조하여 참조값이 같은경우 true를 바로 리턴해준다.
        if (obj == null || getClass() != obj.getClass()) return false; //비교하는 객체가 null인지 클래스가 같은지 체크한다.
        BasketDto student = (BasketDto) obj;
        return salesNo == student.salesNo;
    }
}