package fit.iuh.dulichgiare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fit.iuh.dulichgiare.entity.ItineraryDetail;

@Repository
public interface ItineraryDetailRepository extends JpaRepository<ItineraryDetail, Long> {

	List<ItineraryDetail> findItineraryDetailByItineraryId(long id);
}
