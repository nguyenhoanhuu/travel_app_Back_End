package fit.iuh.dulichgiare.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fit.iuh.dulichgiare.dto.PromotionDTO;
import fit.iuh.dulichgiare.entity.Promotion;
import fit.iuh.dulichgiare.service.PromotionService;

@RestController
@RequestMapping("/promotions")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PromotionController {

	@Autowired
	private PromotionService promotionService;

	@GetMapping(value = { "", "/" })
	public List<PromotionDTO> getAllPromotions() throws InterruptedException, ExecutionException {
		return promotionService.getAllPromotions();
	}

	@GetMapping("/search")
	public List<PromotionDTO> getAllPromotionByName(@RequestParam String name)
			throws InterruptedException, ExecutionException {
		return promotionService.getAllPromotionByName(name);
	}

	@GetMapping("/{id}")
	public PromotionDTO getPromotionById(@PathVariable int id) throws InterruptedException, ExecutionException {
		return promotionService.getPromotionById(id);
	}

	@PostMapping(value = { "", "/save" })
	public Promotion savePromotion(@RequestBody Promotion promotion) throws InterruptedException, ExecutionException {
		return promotionService.savePromotion(promotion);
	}

	@PostMapping(value = { "", "/update" })
	public int updatePromotion(@RequestBody Promotion promotion) throws InterruptedException, ExecutionException {
		return promotionService.updatePromotion(promotion);
	}

	@DeleteMapping("/delete/{id}")
	public String deletePromotion(@PathVariable int id) throws InterruptedException, ExecutionException {
		return promotionService.deletePromotion(id);
	}
}
