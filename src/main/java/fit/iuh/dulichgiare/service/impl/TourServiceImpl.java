package fit.iuh.dulichgiare.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import fit.iuh.dulichgiare.dto.ItineraryDTO;
import fit.iuh.dulichgiare.dto.ItineraryDetailDTO;
import fit.iuh.dulichgiare.dto.PromotionDTO;
import fit.iuh.dulichgiare.dto.TourDTO;
import fit.iuh.dulichgiare.dto.TourDTOImages;
import fit.iuh.dulichgiare.dto.TourDetailDTO;
import fit.iuh.dulichgiare.dto.TourGuideDTO;
import fit.iuh.dulichgiare.dto.TourInformationDTO;
import fit.iuh.dulichgiare.entity.Promotion;
import fit.iuh.dulichgiare.entity.Tour;
import fit.iuh.dulichgiare.entity.TourGuide;
import fit.iuh.dulichgiare.repository.TourRepository;
import fit.iuh.dulichgiare.service.ItineraryDetailService;
import fit.iuh.dulichgiare.service.ItineraryService;
import fit.iuh.dulichgiare.service.PromotionService;
import fit.iuh.dulichgiare.service.TourDetailService;
import fit.iuh.dulichgiare.service.TourGuideService;
import fit.iuh.dulichgiare.service.TourService;
import jakarta.transaction.Transactional;

@Service

public class TourServiceImpl implements TourService {

    @Autowired
    private TourRepository tourRepository;
    @Autowired
    private TourDetailService tourDetailService;
    @Autowired
    private PromotionService promotionService;
    @Autowired
    private TourGuideService tourGuideService;
    @Autowired
    private ItineraryService itineraryService;
    @Autowired
    private ItineraryDetailService itineraryDetailService;

    @Transactional
    @Override
    public List<TourDTOImages> getAllTours(Integer pageNo, Integer pageSize, String sortBy)
            throws InterruptedException, ExecutionException {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending());
        return tourRepository.findAll(pageable).stream().map(tour -> {
            List<String> images = new ArrayList<>();
            TourDTOImages dto = new TourDTOImages();
            dto.setId(tour.getId());
            dto.setPromotionId(tour.getPromotion().getId());
            dto.setTourguideId(tour.getTourguide().getId());
            dto.setName(tour.getName());
            String[] imagesTour = tour.getImage().split(",");
            dto.setImages(getImages(imagesTour));
            dto.setDeparture(tour.getDeparture());
            dto.setDestination(tour.getDestination());
            dto.setStartday(tour.getStartday());
            dto.setNumberofday(tour.getNumberofday());
            dto.setNumberofpeople(tour.getNumberofpeople());
            dto.setSubcriber(tour.getSubcriber());
            dto.setPrice(tour.getPrice());
            dto.setCreateat(tour.getCreateat());
            dto.setLiked(tour.getliked());
            dto.setType(tour.getType());
            return dto;
        }).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public int saveTour(TourDTO tourDTOSave) throws InterruptedException, ExecutionException {
        if (tourDTOSave != null) {
            PromotionDTO promotionDTO = promotionService.getPromotionById(tourDTOSave.getPromotionId());
            Promotion promotion = new Promotion(promotionDTO.getId(), promotionDTO.getName(),
                    promotionDTO.getDiscount(), promotionDTO.getEndday());
            TourGuideDTO tourGuideDTO = tourGuideService.getTourGuideById(tourDTOSave.getTourguideId());

            TourGuide tourGuide = new TourGuide(tourGuideDTO.getId(), tourGuideDTO.getName(), tourGuideDTO.getAddress(),
                    tourGuideDTO.getPhone(), tourGuideDTO.getEmail());
            Tour tour = new Tour(promotion, tourGuide, tourDTOSave.getName(), tourDTOSave.getImage(),
                    tourDTOSave.getDeparture(), tourDTOSave.getDestination(), tourDTOSave.getStartday(),
                    tourDTOSave.getNumberofday(), tourDTOSave.getNumberofpeople(), tourDTOSave.getSubcriber(),
                    tourDTOSave.getType(), tourDTOSave.getPrice(), tourDTOSave.getCreateat(), tourDTOSave.getLiked());
            tourRepository.save(tour);
            return 0;
        }
        return 1;
    }

    @Transactional
    @Override
    public int updateTour(TourDTO tourDTOSave) throws InterruptedException, ExecutionException {
        if (tourDTOSave != null) {
            PromotionDTO promotionDTO = promotionService.getPromotionById(tourDTOSave.getPromotionId());
            Promotion promotion = new Promotion(promotionDTO.getId(), promotionDTO.getName(),
                    promotionDTO.getDiscount(), promotionDTO.getEndday());
            TourGuideDTO tourGuideDTO = tourGuideService.getTourGuideById(tourDTOSave.getTourguideId());
            TourGuide tourGuide = new TourGuide(tourGuideDTO.getId(), tourGuideDTO.getName(), tourGuideDTO.getAddress(),
                    tourGuideDTO.getPhone(), tourGuideDTO.getEmail());
            Tour tour = new Tour(tourDTOSave.getId(), promotion, tourGuide, tourDTOSave.getName(),
                    tourDTOSave.getImage(), tourDTOSave.getDeparture(), tourDTOSave.getDestination(),
                    tourDTOSave.getStartday(), tourDTOSave.getNumberofday(), tourDTOSave.getNumberofpeople(),
                    tourDTOSave.getSubcriber(), tourDTOSave.getType(), tourDTOSave.getPrice(),
                    tourDTOSave.getCreateat(), tourDTOSave.getLiked());
            tourRepository.save(tour);
            return 0;
        }
        return 1;
    }

    @Transactional
    @Override
    public String deleteTour(long id) throws InterruptedException, ExecutionException {

        if (id > 0) {
            tourRepository.deleteById(id);
            return "delete success id - " + id;
        }
        return "Delete failed";
    }

    @Override
    public TourDTO getTourById(long id) throws InterruptedException, ExecutionException {
        Tour tour = tourRepository.findById(id).orElse(null);
        return new TourDTO(tour.getId(), tour.getPromotion().getId(), tour.getTourguide().getId(), tour.getName(),
                tour.getImage(), tour.getDeparture(), tour.getDestination(), tour.getStartday(), tour.getNumberofday(),
                tour.getNumberofpeople(), tour.getSubcriber(), tour.getType(), tour.getPrice(), tour.getCreateat(),
                tour.getliked());
    }

    @Override
    public List<TourDTOImages> getTop8FavoriteTours(int number) throws InterruptedException, ExecutionException {
        return tourRepository.findByTop8Liked(number).stream().map(tour -> {
            TourDTOImages dto = new TourDTOImages();
            dto.setId(tour.getId());
            dto.setPromotionId(tour.getPromotion().getId());
            dto.setTourguideId(tour.getTourguide().getId());
            dto.setName(tour.getName());
            String[] imagesTour = tour.getImage().split(",");
            dto.setImages(getImages(imagesTour));
            dto.setDeparture(tour.getDeparture());
            dto.setDestination(tour.getDestination());
            dto.setStartday(tour.getStartday());
            dto.setNumberofday(tour.getNumberofday());
            dto.setNumberofpeople(tour.getNumberofpeople());
            dto.setSubcriber(tour.getSubcriber());
            dto.setPrice(tour.getPrice());
            dto.setCreateat(tour.getCreateat());
            dto.setLiked(tour.getliked());
            dto.setType(tour.getType());
            return dto;
        }).collect(Collectors.toList());

    }

    @Override
    public List<TourDTOImages> getAllToursByName(String name) throws InterruptedException, ExecutionException {
        return tourRepository.findByNameContaining(name).stream().map(tour -> {
            TourDTOImages dto = new TourDTOImages();
            dto.setId(tour.getId());
            dto.setPromotionId(tour.getPromotion().getId());
            dto.setTourguideId(tour.getTourguide().getId());
            dto.setName(tour.getName());
            String[] imagesTour = tour.getImage().split(",");
            dto.setImages(getImages(imagesTour));
            dto.setDeparture(tour.getDeparture());
            dto.setDestination(tour.getDestination());
            dto.setStartday(tour.getStartday());
            dto.setNumberofday(tour.getNumberofday());
            dto.setNumberofpeople(tour.getNumberofpeople());
            dto.setSubcriber(tour.getSubcriber());
            dto.setPrice(tour.getPrice());
            dto.setCreateat(tour.getCreateat());
            dto.setLiked(tour.getliked());
            dto.setType(tour.getType());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<TourDTOImages> getAlls(String departure, String destination, double startPrice, double endPrice,
            String type, Integer pageNo, Integer pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending());
        return tourRepository.findByDepartureOrDestinationOrPriceBetweenAndType(departure, destination, startPrice,
                endPrice, type, pageable).stream().map(tour -> {
                    TourDTOImages dto = new TourDTOImages();
                    dto.setId(tour.getId());
                    dto.setPromotionId(tour.getPromotion().getId());
                    dto.setTourguideId(tour.getTourguide().getId());
                    dto.setName(tour.getName());
                    String[] imagesTour = tour.getImage().split(",");
                    dto.setImages(getImages(imagesTour));
                    dto.setDeparture(tour.getDeparture());
                    dto.setDestination(tour.getDestination());
                    dto.setStartday(tour.getStartday());
                    dto.setNumberofday(tour.getNumberofday());
                    dto.setNumberofpeople(tour.getNumberofpeople());
                    dto.setSubcriber(tour.getSubcriber());
                    dto.setPrice(tour.getPrice());
                    dto.setCreateat(tour.getCreateat());
                    dto.setLiked(tour.getliked());
                    dto.setType(tour.getType());
                    return dto;
                }).collect(Collectors.toList());
    }

    public List<String> getImages(String[] imagesTour) {
        List<String> images = new ArrayList<>();
        for (String image : imagesTour) {
            images.add(image);
        }
        return images;
    }

    @Override
    public TourInformationDTO getTourInformationById(long id) throws InterruptedException, ExecutionException {
        List<String> images = new ArrayList<>();
        List<ItineraryDetailDTO> itineraryDetails = null;
        TourDetailDTO tourDetailDTO = null;
        Tour tour = tourRepository.findById(id).get();
        PromotionDTO promotion = promotionService.getPromotionById(tour.getPromotion().getId());

        ItineraryDTO itineraryDTO = itineraryService.getItineraryByTourId(tour.getId());
        System.out.println(itineraryDTO);

        if (itineraryDTO.getId() > 0) {
            itineraryDetails = itineraryDetailService.findItineraryDetailByItineraryId(itineraryDTO.getId());
        }

        TourGuideDTO tourGuideDTO = tourGuideService.getTourGuideById(tour.getTourguide().getId());
        if (tour.getId() > 0) {
            tourDetailDTO = tourDetailService.getTourDetailByTourId(tour.getId());
        }
        String[] imagesTour = tour.getImage().split(",");
        for (String image : imagesTour) {
            images.add(image);
        }
        TourInformationDTO dto = new TourInformationDTO();
        dto.setId(tour.getId());
        dto.setNameTour(tour.getName());
        dto.setImages(images);
        dto.setDeparture(tour.getDeparture());
        dto.setDestination(tour.getDestination());
        dto.setStartday(tour.getStartday());
        dto.setNumberofday(tour.getNumberofday());
        dto.setNumberofpeople(tour.getNumberofpeople());
        dto.setSubcriber(tour.getSubcriber());
        dto.setType(tour.getType());
        dto.setPrice(tour.getPrice());
        dto.setCreateat(tour.getCreateat());
        dto.setLiked(tour.getliked());
        dto.setDiscount(promotion.getDiscount());
        dto.setEndDayPromotion(promotion.getEndday());
        dto.setTourGuide(tourGuideDTO);
        dto.setDescription(tourDetailDTO.getDescription());
        dto.setTransport(tourDetailDTO.getTransport());
        dto.setStarhotel(tourDetailDTO.getStarhotel());
        dto.setItineraryDetail(itineraryDetails);

        return dto;
    }

}
