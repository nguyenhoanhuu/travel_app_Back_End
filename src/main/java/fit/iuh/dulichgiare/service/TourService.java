package fit.iuh.dulichgiare.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import fit.iuh.dulichgiare.dto.TourDTO;
import fit.iuh.dulichgiare.dto.TourDTOImages;
import fit.iuh.dulichgiare.dto.TourInformationDTO;

/**
 * 
 * @author HOAN HUU
 *
 */
@Service
public interface TourService {
    public List<TourDTOImages> getAllTours(Integer pageNo, Integer pageSize, String sortBy)
            throws InterruptedException, ExecutionException;

    public int saveTour(TourDTO tourDTOSave) throws InterruptedException, ExecutionException;

    public int updateTour(TourDTO TourDTOSave) throws InterruptedException, ExecutionException;

    public String deleteTour(long id) throws InterruptedException, ExecutionException;

    public TourDTO getTourById(long id) throws InterruptedException, ExecutionException;

    public TourInformationDTO getTourInformationById(long id) throws InterruptedException, ExecutionException;

    public List<TourDTOImages> getTop8FavoriteTours(int number) throws InterruptedException, ExecutionException;

    public List<TourDTOImages> getAllToursByName(String name) throws InterruptedException, ExecutionException;

    public List<TourDTOImages> getAlls(String departure, String destination, double startPrice, double endPrice,
            String type, Integer pageNo, Integer pageSize, String sortBy);

}
