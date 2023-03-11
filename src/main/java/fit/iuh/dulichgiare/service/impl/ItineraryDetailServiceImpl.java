package fit.iuh.dulichgiare.service.impl;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fit.iuh.dulichgiare.dto.ItineraryDTO;
import fit.iuh.dulichgiare.dto.ItineraryDetailDTO;
import fit.iuh.dulichgiare.dto.PromotionDTO;
import fit.iuh.dulichgiare.dto.TourDTO;
import fit.iuh.dulichgiare.dto.TourGuideDTO;
import fit.iuh.dulichgiare.entity.Itinerary;
import fit.iuh.dulichgiare.entity.ItineraryDetail;
import fit.iuh.dulichgiare.entity.Promotion;
import fit.iuh.dulichgiare.entity.Tour;
import fit.iuh.dulichgiare.entity.TourGuide;
import fit.iuh.dulichgiare.repository.ItineraryDetailRepository;
import fit.iuh.dulichgiare.service.ItineraryDetailService;
import fit.iuh.dulichgiare.service.ItineraryService;
import fit.iuh.dulichgiare.service.PromotionService;
import fit.iuh.dulichgiare.service.TourGuideService;
import fit.iuh.dulichgiare.service.TourService;

@Service
public class ItineraryDetailServiceImpl implements ItineraryDetailService {
    @Autowired
    private ItineraryDetailRepository itineraryDetailRepository;
    @Autowired
    private ItineraryService itineraryService;
    @Autowired
    private TourService tourService;
    @Autowired
    private PromotionService promotionService;
    @Autowired
    private TourGuideService tourGuideService;

    @Override
    public List<ItineraryDetailDTO> getAllItineraryDetails() throws InterruptedException, ExecutionException {

        return itineraryDetailRepository.findAll().stream().map(itinerarDetail -> {
            ItineraryDetailDTO dto = new ItineraryDetailDTO();
            dto.setId(itinerarDetail.getId());
            dto.setTitile(itinerarDetail.getTitile());
            dto.setDescription(itinerarDetail.getDescription());
            dto.setItineraryId(itinerarDetail.getItinerary().getId());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public int saveItineraryDetail(ItineraryDetailDTO itineraryDetailDTO)
            throws InterruptedException, ExecutionException {
        if (itineraryDetailDTO != null) {
            ItineraryDTO itinerary = itineraryService.getItineraryById(itineraryDetailDTO.getItineraryId());
            TourDTO tourDTO = tourService.getTourById(itinerary.getTourId());
            PromotionDTO promotionDTO = promotionService.getPromotionById(tourDTO.getPromotionId());
            Promotion promotion = new Promotion(promotionDTO.getId(), promotionDTO.getName(),
                    promotionDTO.getDiscount(), promotionDTO.getEndday());
            TourGuideDTO tourGuideDTO = tourGuideService.getTourGuideById(tourDTO.getTourguideId());
            TourGuide tourGuide = new TourGuide(tourGuideDTO.getId(), tourGuideDTO.getName(), tourGuideDTO.getAddress(),
                    tourGuideDTO.getPhone(), tourGuideDTO.getEmail());
            Tour tour = new Tour(tourDTO.getId(), promotion, tourGuide, tourDTO.getName(), tourDTO.getImage(),
                    tourDTO.getDeparture(), tourDTO.getDestination(), tourDTO.getStartday(), tourDTO.getNumberofday(),
                    tourDTO.getNumberofpeople(), tourDTO.getSubcriber(), tourDTO.getType(), tourDTO.getPrice(),
                    tourDTO.getCreateat(), tourDTO.getLiked());
            Itinerary itinerary2 = new Itinerary(itinerary.getId(), tour, itinerary.getDescription());
            ItineraryDetail itineraryDetail = new ItineraryDetail(itinerary2, itineraryDetailDTO.getTitile(),
                    itineraryDetailDTO.getDescription());
            itineraryDetailRepository.save(itineraryDetail);
            return 0;

        }
        return 1;
    }

    @Override
    public int updateItineraryDetail(ItineraryDetailDTO itineraryDetailDTO)
            throws InterruptedException, ExecutionException {
        if (itineraryDetailDTO != null) {
            ItineraryDTO itinerary = itineraryService.getItineraryById(itineraryDetailDTO.getItineraryId());
            TourDTO tourDTO = tourService.getTourById(itinerary.getTourId());
            PromotionDTO promotionDTO = promotionService.getPromotionById(tourDTO.getPromotionId());
            Promotion promotion = new Promotion(promotionDTO.getId(), promotionDTO.getName(),
                    promotionDTO.getDiscount(), promotionDTO.getEndday());

            TourGuideDTO tourGuideDTO = tourGuideService.getTourGuideById(tourDTO.getTourguideId());

            TourGuide tourGuide = new TourGuide(tourGuideDTO.getId(), tourGuideDTO.getName(), tourGuideDTO.getAddress(),
                    tourGuideDTO.getPhone(), tourGuideDTO.getEmail());
            Tour tour2 = new Tour(tourDTO.getId(), promotion, tourGuide, tourDTO.getName(), tourDTO.getImage(),
                    tourDTO.getDeparture(), tourDTO.getDestination(), tourDTO.getStartday(), tourDTO.getNumberofday(),
                    tourDTO.getNumberofpeople(), tourDTO.getSubcriber(), tourDTO.getType(), tourDTO.getPrice(),
                    tourDTO.getCreateat(), tourDTO.getLiked());

            Itinerary itinerary2 = new Itinerary(itinerary.getId(), tour2, itinerary.getDescription());
            ItineraryDetail itineraryDetail = new ItineraryDetail(itineraryDetailDTO.getId(), itinerary2,
                    itineraryDetailDTO.getTitile(), itineraryDetailDTO.getDescription());
            itineraryDetailRepository.save(itineraryDetail);
            return 0;

        }
        return 1;
    }

    @Override
    public String deleteItineraryDetail(long id) throws InterruptedException, ExecutionException {
        if (id > 0) {
            itineraryDetailRepository.deleteById(id);
            return "delete success id - " + id;
        }
        return "Delete failed";
    }

    @Override
    public ItineraryDetailDTO getItineraryDetailById(long id) throws InterruptedException, ExecutionException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ItineraryDetailDTO> findItineraryDetailByItineraryId(long id)
            throws InterruptedException, ExecutionException {
        return itineraryDetailRepository.findItineraryDetailByItineraryId(id).stream().map(itinerarDetail -> {
            ItineraryDetailDTO dto = new ItineraryDetailDTO();
            dto.setId(itinerarDetail.getId());
            dto.setTitile(itinerarDetail.getTitile());
            dto.setDescription(itinerarDetail.getDescription());
            dto.setItineraryId(itinerarDetail.getItinerary().getId());
            return dto;
        }).collect(Collectors.toList());
    }

}
