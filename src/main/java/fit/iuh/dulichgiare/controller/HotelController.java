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

import fit.iuh.dulichgiare.entity.Hotel;
import fit.iuh.dulichgiare.service.HotelService;

@RestController
@RequestMapping("/hotels")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HotelController {

	@Autowired
	private HotelService hotelService;

	@GetMapping(value = { "", "/" })
	public List<Hotel> getAllHotels() throws InterruptedException, ExecutionException {
		return hotelService.getAllHotels();
	}

	@PostMapping(value = { "", "/save" })
	public Hotel saveHotel(@RequestBody Hotel hotel) throws InterruptedException, ExecutionException {
		return hotelService.saveHotel(hotel);
	}

	@PostMapping(value = { "", "/update" })
	public int updateHotel(@RequestBody Hotel hotel) throws InterruptedException, ExecutionException {
		return hotelService.updateHotel(hotel);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteHotel(@PathVariable int id) throws InterruptedException, ExecutionException {
		return hotelService.deleteHotel(id);
	}

	@GetMapping("/{id}")
	public Hotel getHotelById(@PathVariable long id) throws InterruptedException, ExecutionException {
		return hotelService.getHotelById(id);
	}

	@GetMapping("/search/{name}")
	public List<Hotel> getAllHotelByName(@PathVariable String name) throws InterruptedException, ExecutionException {
		return hotelService.getAllHotelByName(name);
	}
}
