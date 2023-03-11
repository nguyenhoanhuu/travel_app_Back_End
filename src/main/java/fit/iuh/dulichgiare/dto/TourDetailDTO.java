package fit.iuh.dulichgiare.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TourDetailDTO {

	private long id;
	private String description;
	private String transport;
	private int starhotel;
	private long tourId;
	public TourDetailDTO(String description, String transport, int starhotel, long tourId) {
		super();
		this.description = description;
		this.transport = transport;
		this.starhotel = starhotel;
		this.tourId = tourId;
	}
	

}
