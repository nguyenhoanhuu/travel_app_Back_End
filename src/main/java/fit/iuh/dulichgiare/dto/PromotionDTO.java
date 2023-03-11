package fit.iuh.dulichgiare.dto;

import java.util.Date;

import lombok.Data;

@Data
public class PromotionDTO {
	private long id;
	private String name;
	private double discount;
	private Date endday;
}
