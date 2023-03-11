package fit.iuh.dulichgiare.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TourDTO {
	private long id;
	private long promotionId;
	private long tourguideId;
	private String name;
	private String image;
	private String departure;
	private String destination;
	private Date startday;
	private int numberofday;
	private int numberofpeople;
	private int subcriber;
	private String type;
	private double price;
	private Date createat;
	private int liked;
	public TourDTO(long promotionId, long tourguideId, String name, String image, String departure,
			String destination, Date startday, int numberofday, int numberofpeople, int subcriber, String type,
			double price, Date createat, int liked) {
		super();
		this.promotionId = promotionId;
		this.tourguideId = tourguideId;
		this.name = name;
		this.image = image;
		this.departure = departure;
		this.destination = destination;
		this.startday = startday;
		this.numberofday = numberofday;
		this.numberofpeople = numberofpeople;
		this.subcriber = subcriber;
		this.type = type;
		this.price = price;
		this.createat = createat;
		this.liked = liked;
	}
	

	
}
