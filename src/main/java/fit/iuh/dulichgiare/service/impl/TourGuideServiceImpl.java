package fit.iuh.dulichgiare.service.impl;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fit.iuh.dulichgiare.dto.TourGuideDTO;
import fit.iuh.dulichgiare.entity.TourGuide;
import fit.iuh.dulichgiare.repository.TourGuideRepository;
import fit.iuh.dulichgiare.service.TourGuideService;

@Service
public class TourGuideServiceImpl implements TourGuideService {

    @Autowired
    private TourGuideRepository tourGuideRepository;
    private Mapper mapper;

    @Override
    public List<TourGuideDTO> getAllTourGuides() throws InterruptedException, ExecutionException {

        return tourGuideRepository.findAll().stream().map(tourGuide -> {
            TourGuideDTO dto = new TourGuideDTO();
            dto.setId(tourGuide.getId());
            dto.setName(tourGuide.getName());
            dto.setAddress(tourGuide.getAddress());
            dto.setPhone(tourGuide.getPhone());
            dto.setEmail(tourGuide.getEmail());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public TourGuide saveTourGuide(TourGuide tourGuide) throws InterruptedException, ExecutionException {

        return tourGuideRepository.save(tourGuide);
    }

    @Override
    public int updateTourGuide(TourGuide tourGuide) throws InterruptedException, ExecutionException {

        if (tourGuide != null) {
            tourGuideRepository.save(tourGuide);
            return 0;
        }
        return 1;
    }

    @Override
    public String deleteTourGuide(long id) throws InterruptedException, ExecutionException {
        if (id > 0) {
            tourGuideRepository.deleteById(id);
            return "delete success id - " + id;
        }
        return "Delete failed";
    }

    @Override
    public TourGuideDTO getTourGuideById(long id) throws InterruptedException, ExecutionException {
        TourGuide tourGuide = tourGuideRepository.findById(id).get();
        TourGuideDTO tourGuideDTO = new TourGuideDTO();
        tourGuideDTO.setId(tourGuide.getId());
        tourGuideDTO.setName(tourGuide.getName());
        tourGuideDTO.setAddress(tourGuide.getAddress());
        tourGuideDTO.setPhone(tourGuide.getPhone());
        tourGuideDTO.setEmail(tourGuide.getEmail());
        return tourGuideDTO;
    }

}
