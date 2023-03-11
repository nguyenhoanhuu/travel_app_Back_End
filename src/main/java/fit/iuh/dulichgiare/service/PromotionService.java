package fit.iuh.dulichgiare.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import fit.iuh.dulichgiare.dto.PromotionDTO;
import fit.iuh.dulichgiare.entity.Promotion;
/**
 * 
 * @author HOAN HUU
 *
 */
@Service
public interface PromotionService {
	public List<PromotionDTO> getAllPromotions() throws InterruptedException, ExecutionException;

	public Promotion savePromotion(Promotion promotion) throws InterruptedException, ExecutionException;

	public int updatePromotion(Promotion promotion) throws InterruptedException, ExecutionException;

	public String deletePromotion(long id) throws InterruptedException, ExecutionException;

	public PromotionDTO getPromotionById(long id) throws InterruptedException, ExecutionException;

	public List<PromotionDTO> getAllPromotionByName(String name) throws InterruptedException, ExecutionException;
}
