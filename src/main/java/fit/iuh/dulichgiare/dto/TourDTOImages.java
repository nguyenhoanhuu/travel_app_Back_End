package fit.iuh.dulichgiare.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class TourDTOImages {
    private long id;
    private long promotionId;
    private long tourguideId;
    private String name;
    private List<String> images;
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
}
