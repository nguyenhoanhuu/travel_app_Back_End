package fit.iuh.dulichgiare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fit.iuh.dulichgiare.entity.Itinerary;

@Repository
public interface ItineraryRepository extends JpaRepository<Itinerary, Long> {

    Itinerary findItineraryByTourId(long tourId);

}
