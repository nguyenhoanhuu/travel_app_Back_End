package fit.iuh.dulichgiare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fit.iuh.dulichgiare.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

	
}
