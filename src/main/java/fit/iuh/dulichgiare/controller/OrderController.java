package fit.iuh.dulichgiare.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fit.iuh.dulichgiare.dto.OrderDTO;
import fit.iuh.dulichgiare.service.OrderService;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrderController {
	@Autowired
	private OrderService orderService;

	@GetMapping("")
	public List<OrderDTO> getAllOrders() throws InterruptedException, ExecutionException {
		return orderService.getAllOrders();
	}

	@PostMapping(value = { "", "/save" })
	public int saveOrder(@RequestBody OrderDTO orderDTO) throws InterruptedException, ExecutionException {
		return orderService.saveOrder(orderDTO);
	}


	@PostMapping(value = { "", "/update" })
	public int updateOrder(@RequestBody OrderDTO orderDTO) throws InterruptedException, ExecutionException {
		return orderService.updateOrder(orderDTO);
	}

	@GetMapping("/{id}")
	public OrderDTO getOrderById(@PathVariable long id) throws InterruptedException, ExecutionException {
		return orderService.getOrderById(id);
	}
}
