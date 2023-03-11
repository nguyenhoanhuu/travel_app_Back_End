package fit.iuh.dulichgiare.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import fit.iuh.dulichgiare.entity.Hotel;
/**
 * 
 * @author HOAN HUU
 *
 */
@Service
public interface HotelService {

	public List<Hotel> getAllHotels() throws InterruptedException, ExecutionException;

	public Hotel getHotelById(long id) throws InterruptedException, ExecutionException;

	public Hotel saveHotel(Hotel hotel) throws InterruptedException, ExecutionException;

	public int updateHotel(Hotel hotel) throws InterruptedException, ExecutionException;

	public String deleteHotel(long id) throws InterruptedException, ExecutionException;

	public List<Hotel> getAllHotelByName(String name) throws InterruptedException, ExecutionException;

}
