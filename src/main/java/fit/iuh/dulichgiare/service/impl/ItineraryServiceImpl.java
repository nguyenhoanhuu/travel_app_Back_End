package fit.iuh.dulichgiare.service.impl;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fit.iuh.dulichgiare.dto.ItineraryDTO;
import fit.iuh.dulichgiare.entity.Itinerary;
import fit.iuh.dulichgiare.entity.Promotion;
import fit.iuh.dulichgiare.entity.Tour;
import fit.iuh.dulichgiare.entity.TourGuide;
import fit.iuh.dulichgiare.repository.ItineraryRepository;
import fit.iuh.dulichgiare.repository.PromotionRepository;
import fit.iuh.dulichgiare.repository.TourGuideRepository;
import fit.iuh.dulichgiare.repository.TourRepository;
import fit.iuh.dulichgiare.service.ItineraryDetailService;
import fit.iuh.dulichgiare.service.ItineraryService;
import jakarta.transaction.Transactional;

@Service
public class ItineraryServiceImpl implements ItineraryService {

    @Autowired
    private ItineraryRepository itineraryRepository;
    @Autowired
    private TourRepository tourRepository;
    @Autowired
    private PromotionRepository promotionRepository;
    @Autowired
    private TourGuideRepository tourGuideRepository;

    @Transactional
    @Override
    public List<ItineraryDTO> getAllItinerarys() throws InterruptedException, ExecutionException {
        return itineraryRepository.findAll().stream().map(itinerary -> {
            ItineraryDTO dto = new ItineraryDTO();
            dto.setId(itinerary.getId());
            dto.setTourId(itinerary.getTour().getId());
            dto.setDescription(itinerary.getDescription());
            return dto;
        }).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public int saveItinerary(ItineraryDTO itineraryDTO) throws InterruptedException, ExecutionException {
        if (itineraryDTO != null) {
            Tour tour = tourRepository.findById(itineraryDTO.getTourId()).get();
            Promotion promotion = promotionRepository.findById(tour.getPromotion().getId()).get();
            TourGuide tourGuide = tourGuideRepository.findById(tour.getTourguide().getId()).get();
            Tour tour2 = new Tour(itineraryDTO.getTourId(), promotion, tourGuide, tour.getName(), tour.getImage(),
                    tour.getDeparture(), tour.getDestination(), tour.getStartday(), tour.getNumberofday(),
                    tour.getNumberofpeople(), tour.getSubcriber(), tour.getType(), tour.getPrice(), tour.getCreateat(),
                    tour.getliked());

            Itinerary itinerary = new Itinerary(tour2, itineraryDTO.getDescription());
            itineraryRepository.save(itinerary);
            return 0;
        }
        return 1;
    }

    @Transactional
    @Override
    public int updateItinerary(ItineraryDTO itineraryDTO) throws InterruptedException, ExecutionException {
        if (itineraryDTO != null) {
            Tour tour = tourRepository.findById(itineraryDTO.getTourId()).get();
            Promotion promotion = promotionRepository.findById(tour.getPromotion().getId()).get();
            TourGuide tourGuide = tourGuideRepository.findById(tour.getTourguide().getId()).get();
            Tour tour2 = new Tour(itineraryDTO.getTourId(), promotion, tourGuide, tour.getName(), tour.getImage(),
                    tour.getDeparture(), tour.getDestination(), tour.getStartday(), tour.getNumberofday(),
                    tour.getNumberofpeople(), tour.getSubcriber(), tour.getType(), tour.getPrice(), tour.getCreateat(),
                    tour.getliked());

            Itinerary itinerary = new Itinerary(itineraryDTO.getId(), tour2, itineraryDTO.getDescription());
            itineraryRepository.save(itinerary);
            return 0;
        }
        return 1;
    }

    @Transactional
    @Override
    public String deleteItinerary(long id) throws InterruptedException, ExecutionException {
        return null;
    }

    @Transactional
    @Override
    public ItineraryDTO getItineraryById(long id) throws InterruptedException, ExecutionException {
        Itinerary itinerary = itineraryRepository.findById(id).orElse(null);
        try {
            return new ItineraryDTO(itinerary.getId(), itinerary.getTour().getId(), itinerary.getDescription());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public ItineraryDTO getItineraryByTourId(long tourId) throws InterruptedException, ExecutionException {
        Itinerary itinerary = itineraryRepository.findItineraryByTourId(tourId);
        ItineraryDTO dto = new ItineraryDTO();
        dto.setId(itinerary.getId());
        dto.setTourId(itinerary.getTour().getId());
        dto.setDescription(itinerary.getDescription());
        return dto;
    }

}
