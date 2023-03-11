package fit.iuh.dulichgiare.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fit.iuh.dulichgiare.entity.Tour;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {

	@Query(value = "SELECT * FROM tour t  ORDER BY t.liked DESC Limit ?1", nativeQuery = true)
	List<Tour> findByTop8Liked(int number);

	List<Tour> findByNameContaining(@Param("infix") String name);

	// unfinished
	@Query(value = "select ", nativeQuery = true)
	List<Tour> findByDepartureOrDestinationOrNumberofdayOrNumberofpeopleOrpriceOrtype(String departure,
			String destination, int numberofday, int numberofpeople, double price, String type);

	List<Tour> findByDepartureOrDestinationOrPriceBetweenAndType(String departure, String destination,
			double startPrice, double endPrice, String type, Pageable pageable);
}
