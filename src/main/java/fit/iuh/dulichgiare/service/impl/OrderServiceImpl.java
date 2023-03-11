package fit.iuh.dulichgiare.service.impl;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fit.iuh.dulichgiare.dto.AccountCustomerDTO;
import fit.iuh.dulichgiare.dto.OrderDTO;
import fit.iuh.dulichgiare.dto.PromotionDTO;
import fit.iuh.dulichgiare.dto.TourDTO;
import fit.iuh.dulichgiare.dto.TourGuideDTO;
import fit.iuh.dulichgiare.entity.Account;
import fit.iuh.dulichgiare.entity.Customer;
import fit.iuh.dulichgiare.entity.Order;
import fit.iuh.dulichgiare.entity.Promotion;
import fit.iuh.dulichgiare.entity.Tour;
import fit.iuh.dulichgiare.entity.TourGuide;
import fit.iuh.dulichgiare.repository.OrderRepository;
import fit.iuh.dulichgiare.service.CustomerService;
import fit.iuh.dulichgiare.service.OrderService;
import fit.iuh.dulichgiare.service.PromotionService;
import fit.iuh.dulichgiare.service.TourGuideService;
import fit.iuh.dulichgiare.service.TourService;
import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private TourService tourService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private PromotionService promotionService;
    @Autowired
    private TourGuideService tourGuideService;

    @Transactional
    @Override
    public List<OrderDTO> getAllOrders() throws InterruptedException, ExecutionException {

        return orderRepository.findAll().stream().map(order -> {
            OrderDTO dto = new OrderDTO();
            dto.setId(order.getId());
            dto.setCustomerId(order.getCustomer().getId());
            dto.setTourId(order.getTour().getId());
            dto.setNote(order.getNote());
            dto.setNumberofadbult(order.getNumberofadbult());
            dto.setNumberofchildren(order.getNumberofchildren());
            dto.setCreateat(order.getCreateat());
            dto.setTotal(order.getTotal());
            dto.setStatus(order.getStatus());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public int saveOrder(OrderDTO orderDTO) throws InterruptedException, ExecutionException {
        if (orderDTO != null) {
            AccountCustomerDTO customerDTO = null;
            TourDTO tourDTO = tourService.getTourById(orderDTO.getTourId());
            PromotionDTO promotionDTO = promotionService.getPromotionById(tourDTO.getPromotionId());
            Promotion promotion = new Promotion(promotionDTO.getId(), promotionDTO.getName(),
                    promotionDTO.getDiscount(), promotionDTO.getEndday());
            TourGuideDTO tourGuideDTO = tourGuideService.getTourGuideById(tourDTO.getTourguideId());

            TourGuide tourGuide = new TourGuide(tourGuideDTO.getId(), tourGuideDTO.getName(), tourGuideDTO.getAddress(),
                    tourGuideDTO.getPhone(), tourGuideDTO.getEmail());
            Tour tour = new Tour(tourDTO.getId(), promotion, tourGuide, tourDTO.getName(), tourDTO.getImage(),
                    tourDTO.getDeparture(), tourDTO.getDestination(), tourDTO.getStartday(), tourDTO.getNumberofday(),
                    tourDTO.getNumberofpeople(), tourDTO.getSubcriber(), tourDTO.getType(), tourDTO.getPrice(),
                    tourDTO.getCreateat(), tourDTO.getLiked());
            // check user existed
            if (orderDTO.getCustomerId() == 0) {
                System.out.println("null");
            } else {
                System.out.println("existed");
                customerDTO = customerService.getCustomerById(orderDTO.getCustomerId());
            }
            Account account = new Account();
            account.setId(customerDTO.getAccount().getId());
            account.setUsername(customerDTO.getAccount().getUsername());
            account.setPassword(customerDTO.getAccount().getPassword());
            account.setAccountType(customerDTO.getAccount().getAccountType());
            Customer customer = new Customer();
            customer.setId(customerDTO.getId());
            customer.setAccount(account);
            customer.setName(customerDTO.getName());
            customer.setAddress(customerDTO.getAddress());
            customer.setEmail(customerDTO.getEmail());
            customer.setPhone(customerDTO.getPhone());
            Order order = new Order();
            order.setCustomer(customer);
            order.setTour(tour);
            order.setNote(orderDTO.getNote());
            order.setNumberofadbult(orderDTO.getNumberofadbult());
            order.setNumberofchildren(orderDTO.getNumberofchildren());
            order.setCreateat(orderDTO.getCreateat());
            order.setTotal(orderDTO.getTotal());
            order.setStatus(orderDTO.getStatus());
            orderRepository.save(order);
            return 0;
        }
        return 1;
    }

    @Override
    public int updateOrder(OrderDTO orderDTO) throws InterruptedException, ExecutionException {
        if (orderDTO != null) {
            TourDTO tourDTO = tourService.getTourById(orderDTO.getTourId());
            PromotionDTO promotionDTO = promotionService.getPromotionById(tourDTO.getPromotionId());
            Promotion promotion = new Promotion(promotionDTO.getId(), promotionDTO.getName(),
                    promotionDTO.getDiscount(), promotionDTO.getEndday());
            TourGuideDTO tourGuideDTO = tourGuideService.getTourGuideById(tourDTO.getTourguideId());

            TourGuide tourGuide = new TourGuide(tourGuideDTO.getId(), tourGuideDTO.getName(), tourGuideDTO.getAddress(),
                    tourGuideDTO.getPhone(), tourGuideDTO.getEmail());
            Tour tour = new Tour(tourDTO.getId(), promotion, tourGuide, tourDTO.getName(), tourDTO.getImage(),
                    tourDTO.getDeparture(), tourDTO.getDestination(), tourDTO.getStartday(), tourDTO.getNumberofday(),
                    tourDTO.getNumberofpeople(), tourDTO.getSubcriber(), tourDTO.getType(), tourDTO.getPrice(),
                    tourDTO.getCreateat(), tourDTO.getLiked());
            AccountCustomerDTO customerDTO = customerService.getCustomerById(orderDTO.getCustomerId());
            Account account = new Account();
            account.setId(customerDTO.getAccount().getId());
            account.setUsername(customerDTO.getAccount().getUsername());
            account.setPassword(customerDTO.getAccount().getPassword());
            account.setAccountType(customerDTO.getAccount().getAccountType());
            Customer customer = new Customer();
            customer.setId(customerDTO.getId());
            customer.setAccount(account);
            customer.setName(customerDTO.getName());
            customer.setAddress(customerDTO.getAddress());
            customer.setEmail(customerDTO.getEmail());
            customer.setPhone(customerDTO.getPhone());
            Order order = new Order();
            order.setId(orderDTO.getId());
            order.setCustomer(customer);
            order.setTour(tour);
            order.setNote(orderDTO.getNote());
            order.setNumberofadbult(orderDTO.getNumberofadbult());
            order.setNumberofchildren(orderDTO.getNumberofchildren());
            order.setCreateat(orderDTO.getCreateat());
            order.setTotal(orderDTO.getTotal());
            order.setStatus(orderDTO.getStatus());
            orderRepository.save(order);
            return 0;
        }
        return 1;
    }

    @Override
    public String deleteOrder(long id) throws InterruptedException, ExecutionException {
        if (id > 0) {
            orderRepository.deleteById(id);
            return "delete success id - " + id;
        }
        return "Delete failed";
    }

    @Override
    public OrderDTO getOrderById(long id) throws InterruptedException, ExecutionException {
        Order order = orderRepository.findById(id).orElse(null);
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setCustomerId(order.getCustomer().getId());
        orderDTO.setTourId(order.getTour().getId());
        orderDTO.setNote(order.getNote());
        orderDTO.setNumberofadbult(order.getNumberofadbult());
        orderDTO.setNumberofchildren(order.getNumberofchildren());
        orderDTO.setCreateat(order.getCreateat());
        orderDTO.setTotal(order.getTotal());
        orderDTO.setStatus(order.getStatus());
        return orderDTO;
    }

}
