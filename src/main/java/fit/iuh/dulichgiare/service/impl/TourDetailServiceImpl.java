package fit.iuh.dulichgiare.service.impl;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fit.iuh.dulichgiare.dto.PromotionDTO;
import fit.iuh.dulichgiare.dto.TourDTO;
import fit.iuh.dulichgiare.dto.TourDetailDTO;
import fit.iuh.dulichgiare.dto.TourGuideDTO;
import fit.iuh.dulichgiare.entity.Promotion;
import fit.iuh.dulichgiare.entity.Tour;
import fit.iuh.dulichgiare.entity.TourDetail;
import fit.iuh.dulichgiare.entity.TourGuide;
import fit.iuh.dulichgiare.repository.TourDetailRepository;
import fit.iuh.dulichgiare.service.PromotionService;
import fit.iuh.dulichgiare.service.TourDetailService;
import fit.iuh.dulichgiare.service.TourGuideService;
import fit.iuh.dulichgiare.service.TourService;

@Service
public class TourDetailServiceImpl implements TourDetailService {

    @Autowired
    private TourDetailRepository tourDetailRepository;
    @Autowired
    private TourService tourService;
    @Autowired
    private PromotionService promotionService;
    @Autowired
    private TourGuideService tourGuideService;

    @Override
    public List<TourDetailDTO> getAllTours() throws InterruptedException, ExecutionException {
        return tourDetailRepository.findAll().stream().map(tourdetail -> {
            TourDetailDTO dto = new TourDetailDTO();
            dto.setId(tourdetail.getId());
            dto.setTourId(tourdetail.getTour().getId());
            dto.setTransport(tourdetail.getTransport());
            dto.setDescription(tourdetail.getDescription());
            dto.setStarhotel(tourdetail.getstarhotel());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public int saveTourDetail(TourDetailDTO tourDetailDTO) throws InterruptedException, ExecutionException {
        if (tourDetailDTO != null) {
            TourDTO tourDTO = tourService.getTourById(tourDetailDTO.getTourId());
            PromotionDTO promotionDTO = promotionService.getPromotionById(tourDTO.getPromotionId());
            Promotion promotion = new Promotion(promotionDTO.getId(), promotionDTO.getName(),
                    promotionDTO.getDiscount(), promotionDTO.getEndday());
            TourGuideDTO tourGuideDTO = tourGuideService.getTourGuideById(tourDTO.getTourguideId());

            TourGuide tourGuide = new TourGuide(tourGuideDTO.getId(), tourGuideDTO.getName(), tourGuideDTO.getAddress(),
                    tourGuideDTO.getPhone(), tourGuideDTO.getEmail());
            Tour tour = new Tour();
            tour.setId(tourDTO.getId());
            tour.setPromotion(promotion);
            tour.setTourguide(tourGuide);
            tour.setName(tourDTO.getName());
            tour.setImage(tourDTO.getImage());
            tour.setDeparture(tourDTO.getDeparture());
            tour.setDestination(tourDTO.getDestination());
            tour.setStartday(tourDTO.getStartday());
            tour.setNumberofday(tourDTO.getNumberofday());
            tour.setNumberofpeople(tourDTO.getNumberofpeople());
            tour.setSubcriber(tourDTO.getSubcriber());
            tour.setType(tourDTO.getType());
            tour.setPrice(tourDTO.getPrice());
            tour.setCreateat(tourDTO.getCreateat());
            tour.setliked(tourDTO.getLiked());

            TourDetail tourDetail1 = new TourDetail(tourDetailDTO.getDescription(), tourDetailDTO.getTransport(),
                    tourDetailDTO.getStarhotel(), tour);
            tourDetailRepository.save(tourDetail1);
            return 0;
        }
        return 1;
    }

    @Override
    public int updateTourDetail(TourDetailDTO tourDetailDTO) throws InterruptedException, ExecutionException {
        if (tourDetailDTO != null) {
            TourDTO tourDTO = tourService.getTourById(tourDetailDTO.getTourId());
            PromotionDTO promotionDTO = promotionService.getPromotionById(tourDTO.getPromotionId());
            Promotion promotion = new Promotion(promotionDTO.getId(), promotionDTO.getName(),
                    promotionDTO.getDiscount(), promotionDTO.getEndday());
            TourGuideDTO tourGuideDTO = tourGuideService.getTourGuideById(tourDTO.getTourguideId());
            TourGuide tourGuide = new TourGuide(tourGuideDTO.getId(), tourGuideDTO.getName(), tourGuideDTO.getAddress(),
                    tourGuideDTO.getPhone(), tourGuideDTO.getEmail());
            Tour tour = new Tour();
            tour.setId(tourDTO.getId());
            tour.setPromotion(promotion);
            tour.setTourguide(tourGuide);
            tour.setName(tourDTO.getName());
            tour.setImage(tourDTO.getImage());
            tour.setDeparture(tourDTO.getDeparture());
            tour.setDestination(tourDTO.getDestination());
            tour.setStartday(tourDTO.getStartday());
            tour.setNumberofday(tourDTO.getNumberofday());
            tour.setNumberofpeople(tourDTO.getNumberofpeople());
            tour.setSubcriber(tourDTO.getSubcriber());
            tour.setType(tourDTO.getType());
            tour.setPrice(tourDTO.getPrice());
            tour.setCreateat(tourDTO.getCreateat());
            tour.setliked(tourDTO.getLiked());

            TourDetail tourDetail1 = new TourDetail(tourDetailDTO.getId(), tourDetailDTO.getDescription(),
                    tourDetailDTO.getTransport(), tourDetailDTO.getStarhotel(), tour);
            tourDetailRepository.save(tourDetail1);
            return 0;
        }
        return 1;
    }

    @Override
    public String deleteTourDetail(long id) throws InterruptedException, ExecutionException {
        return null;
    }

    @Override
    public TourDetailDTO getTourDetailById(long id) throws InterruptedException, ExecutionException {
        return null;
    }

    @Override
    public TourDetailDTO getTourDetailByTourId(long id) throws InterruptedException, ExecutionException {
        TourDetail tourDetail = tourDetailRepository.findTourDetailByTourId(id);
        TourDetailDTO dto = new TourDetailDTO();
        dto.setId(tourDetail.getId());
        dto.setTourId(tourDetail.getTour().getId());
        dto.setTransport(tourDetail.getTransport());
        dto.setDescription(tourDetail.getDescription());
        dto.setStarhotel(tourDetail.getstarhotel());
        return dto;
    }

}
