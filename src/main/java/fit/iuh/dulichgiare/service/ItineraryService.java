package fit.iuh.dulichgiare.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import fit.iuh.dulichgiare.dto.ItineraryDTO;

@Service
public interface ItineraryService {
    public List<ItineraryDTO> getAllItinerarys() throws InterruptedException, ExecutionException;

    public int saveItinerary(ItineraryDTO itineraryDTO) throws InterruptedException, ExecutionException;

    public int updateItinerary(ItineraryDTO itineraryDTO) throws InterruptedException, ExecutionException;

    public String deleteItinerary(long id) throws InterruptedException, ExecutionException;

    public ItineraryDTO getItineraryById(long id) throws InterruptedException, ExecutionException;

    public ItineraryDTO getItineraryByTourId(long tourId) throws InterruptedException, ExecutionException;
}
