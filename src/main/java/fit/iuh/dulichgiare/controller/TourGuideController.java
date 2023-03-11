package fit.iuh.dulichgiare.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fit.iuh.dulichgiare.dto.TourGuideDTO;
import fit.iuh.dulichgiare.entity.TourGuide;
import fit.iuh.dulichgiare.service.TourGuideService;

@RestController
@RequestMapping("/tourguides")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TourGuideController {

	@Autowired
	private TourGuideService tourGuideService;

	@GetMapping(value = { "", "/" })
	public List<TourGuideDTO> getAllTourGuides() throws InterruptedException, ExecutionException {
		return tourGuideService.getAllTourGuides();
	}

	@PostMapping(value = { "", "/save" })
	public TourGuide saveTourGuide(@RequestBody TourGuide tourGuide) throws InterruptedException, ExecutionException {
		return tourGuideService.saveTourGuide(tourGuide);
	}

	@PostMapping(value = { "", "/update" })
	public int updateTourGuide(@RequestBody TourGuide tourGuide) throws InterruptedException, ExecutionException {
		return tourGuideService.updateTourGuide(tourGuide);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteTourGuide(@PathVariable int id) throws InterruptedException, ExecutionException {
		return tourGuideService.deleteTourGuide(id);
	}

	@GetMapping("/{id}")
	public TourGuideDTO getTourGuideById(@PathVariable long id) throws InterruptedException, ExecutionException {
		return tourGuideService.getTourGuideById(id);
	}

}
