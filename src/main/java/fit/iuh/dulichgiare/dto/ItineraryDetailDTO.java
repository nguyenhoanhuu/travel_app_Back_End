package fit.iuh.dulichgiare.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItineraryDetailDTO {

	private long id;
	private long itineraryId;
	private String titile;
	private String description;

	public ItineraryDetailDTO(long itineraryId, String titile, String description) {
		super();
		this.itineraryId = itineraryId;
		this.titile = titile;
		this.description = description;
	}

}
