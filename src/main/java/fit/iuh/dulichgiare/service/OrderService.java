package fit.iuh.dulichgiare.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import fit.iuh.dulichgiare.dto.OrderDTO;

/**
 * 
 * @author HOAN HUU
 *
 */
@Service
public interface OrderService {

	public List<OrderDTO> getAllOrders() throws InterruptedException, ExecutionException;

	public int saveOrder(OrderDTO orderDTO) throws InterruptedException, ExecutionException;

	public int updateOrder(OrderDTO orderDTO) throws InterruptedException, ExecutionException;

	public String deleteOrder(long id) throws InterruptedException, ExecutionException;

	public OrderDTO getOrderById(long id) throws InterruptedException, ExecutionException;

}
