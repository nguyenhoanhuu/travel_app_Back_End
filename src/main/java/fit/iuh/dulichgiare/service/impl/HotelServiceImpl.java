package fit.iuh.dulichgiare.service.impl;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fit.iuh.dulichgiare.entity.Hotel;
import fit.iuh.dulichgiare.repository.HotelRepository;
import fit.iuh.dulichgiare.service.HotelService;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	private HotelRepository hotelRepository;

	@Override
	public List<Hotel> getAllHotels() throws InterruptedException, ExecutionException {
		return hotelRepository.findAll();

	}

	@Override
	public Hotel saveHotel(Hotel hotel) throws InterruptedException, ExecutionException {
		return hotelRepository.save(hotel);
	}

	@Override
	public int updateHotel(Hotel hotel) throws InterruptedException, ExecutionException {
		if (hotel != null) {
			hotelRepository.save(hotel);
			return 0;
		}
		return 1;
	}

	@Override
	public String deleteHotel(long id) throws InterruptedException, ExecutionException {
		if (id > 0) {
			hotelRepository.deleteById(id);
			return "delete success id - " + id;
		}
		return "Delete failed";

	}

	@Override
	public Hotel getHotelById(long id) throws InterruptedException, ExecutionException {
		return hotelRepository.findById(id).get();
	}

	@Override
	public List<Hotel> getAllHotelByName(String name) throws InterruptedException, ExecutionException {
		return hotelRepository.findByNameContaining(name);
	}

}
