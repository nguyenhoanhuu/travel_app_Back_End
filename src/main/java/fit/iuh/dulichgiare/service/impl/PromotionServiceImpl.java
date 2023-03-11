package fit.iuh.dulichgiare.service.impl;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fit.iuh.dulichgiare.dto.PromotionDTO;
import fit.iuh.dulichgiare.entity.Promotion;
import fit.iuh.dulichgiare.repository.PromotionRepository;
import fit.iuh.dulichgiare.service.PromotionService;

@Service
public class PromotionServiceImpl implements PromotionService {

	@Autowired
	private PromotionRepository promotionRepository;
	private ModelMapper mapper;

	@Override
	public List<PromotionDTO> getAllPromotions() throws InterruptedException, ExecutionException {
		return promotionRepository.findAll().stream().map(promotion -> {
			PromotionDTO dto = new PromotionDTO();
			dto.setId(promotion.getId());
			dto.setName(promotion.getName());
			dto.setDiscount(promotion.getdiscount());
			dto.setEndday(promotion.getEndday());
			return dto;
		}).collect(Collectors.toList());
	}

	@Override
	public Promotion savePromotion(Promotion promotion) throws InterruptedException, ExecutionException {
		return promotionRepository.save(promotion);
	}

	@Override
	public int updatePromotion(Promotion promotion) throws InterruptedException, ExecutionException {
		if (promotion != null) {
			promotionRepository.save(promotion);
			return 0;
		}
		return 1;
	}

	@Override
	public String deletePromotion(long id) throws InterruptedException, ExecutionException {
		if (id > 0) {
			promotionRepository.deleteById(id);
			return "delete success id - " + id;
		}
		return "Delete failed";
	}

	@Override
	public PromotionDTO getPromotionById(long id) throws InterruptedException, ExecutionException {
		Promotion promotion = promotionRepository.findById(id).get();
		PromotionDTO promotionDTO = new PromotionDTO();
		promotionDTO.setId(promotion.getId());
		promotionDTO.setName(promotion.getName());
		promotionDTO.setDiscount(promotion.getdiscount());
		promotionDTO.setEndday(promotion.getEndday());
		return promotionDTO;
	}

	@Override
	public List<PromotionDTO> getAllPromotionByName(String name) throws InterruptedException, ExecutionException {
		return promotionRepository.findByNameContaining(name).stream().map(promotion -> {
			PromotionDTO dto = new PromotionDTO();
			dto.setId(promotion.getId());
			dto.setName(promotion.getName());
			dto.setDiscount(promotion.getdiscount());
			dto.setEndday(promotion.getEndday());
			return dto;
		}).collect(Collectors.toList());
	}

}
