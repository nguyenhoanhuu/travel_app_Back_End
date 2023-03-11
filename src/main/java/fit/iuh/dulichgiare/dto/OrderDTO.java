package fit.iuh.dulichgiare.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

	private long id;
	private long customerId;
	private long tourId;
	private String note;
	private int numberofadbult;
	private int numberofchildren;
	private Date createat;
	private double total;
	private String status;

	public OrderDTO(long customerId, long tourId, String note, int numberofadbult, int numberofchildren, Date createat,
			double total, String status) {
		super();
		this.customerId = customerId;
		this.tourId = tourId;
		this.note = note;
		this.numberofadbult = numberofadbult;
		this.numberofchildren = numberofchildren;
		this.createat = createat;
		this.total = total;
		this.status = status;
	}

}
