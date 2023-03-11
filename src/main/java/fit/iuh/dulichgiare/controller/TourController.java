package fit.iuh.dulichgiare.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fit.iuh.dulichgiare.dto.TourDTO;
import fit.iuh.dulichgiare.dto.TourDTOImages;
import fit.iuh.dulichgiare.dto.TourInformationDTO;
import fit.iuh.dulichgiare.service.TourService;

@RestController
@RequestMapping("/tours")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TourController {

    @Autowired
    private TourService tourService;

    @GetMapping(value = { "", "/" })
    public ResponseEntity<List<TourDTOImages>> getAllTours(@RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "100") Integer pageSize, @RequestParam String sortBy)
            throws InterruptedException, ExecutionException {
        return new ResponseEntity<List<TourDTOImages>>(tourService.getAllTours(pageNo, pageSize, sortBy),
                HttpStatus.OK);
    }

    @PostMapping(value = { "", "/save" })
    public int saveTour(@RequestBody TourDTO tourDTOSave) throws InterruptedException, ExecutionException {
        return tourService.saveTour(tourDTOSave);
    }

    @PostMapping(value = { "", "/update" })
    public int updateTour(@RequestBody TourDTO tourDTOSave) throws InterruptedException, ExecutionException {
        return tourService.updateTour(tourDTOSave);
    }

    @GetMapping("/{id}")
    public TourDTO getTourById(@PathVariable long id) throws InterruptedException, ExecutionException {
        return tourService.getTourById(id);
    }

    @GetMapping(value = { "/toptours/{number}" })
    public List<TourDTOImages> getTop8FavoriteTours(@PathVariable int number)
            throws InterruptedException, ExecutionException {
        return tourService.getTop8FavoriteTours(number);
    }

    @GetMapping("/search/{name}")
    public List<TourDTOImages> getAllToursByName(@PathVariable String name)
            throws InterruptedException, ExecutionException {
        return tourService.getAllToursByName(name);
    }

    @GetMapping("/departureordestinationorpriceortype")
    public ResponseEntity<List<TourDTOImages>> findByDepartureOrDestinationOrPriceOrType(@RequestParam String departure,
            @RequestParam String destination, @RequestParam double startPrice, double endPrice,
            @RequestParam String type, @RequestParam Integer pageNo, @RequestParam Integer pageSize,
            @RequestParam String sortBy) {
        return new ResponseEntity<>(
                tourService.getAlls(departure, destination, startPrice, endPrice, type, pageNo, pageSize, sortBy),
                HttpStatus.OK);
    }

    @GetMapping("/info/{id}")
    public TourInformationDTO getTourInformationById(@PathVariable long id)
            throws InterruptedException, ExecutionException {
        return tourService.getTourInformationById(id);
    }
}
