package fit.iuh.dulichgiare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fit.iuh.dulichgiare.entity.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

	List<Hotel> findByNameContaining(@Param("infix") String name);

}
