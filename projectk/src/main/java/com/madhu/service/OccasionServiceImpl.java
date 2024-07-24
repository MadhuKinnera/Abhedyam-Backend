package com.madhu.service;

import com.madhu.dto.OccasionDTO;
import com.madhu.entity.Occasion;
import com.madhu.exception.OccasionException;
import com.madhu.exception.UserException;
import com.madhu.repository.OccasionRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class OccasionServiceImpl implements OccasionService {

    private final OccasionRepo occasionRepo;

    @Override
    public Occasion addOccasion(OccasionDTO occasionDTO) {
        var occasion = new Occasion();
        occasion.setOccasionName(occasionDTO.getOccasionName());
        occasion.setOccasionDate(occasionDTO.getOccasionDate());
        return occasionRepo.save(occasion);
    }

    @Override
    public Occasion getOccasion(Integer occasionId) throws OccasionException {
        return occasionRepo.findById(occasionId)
                .orElseThrow(() -> new OccasionException("Occasion Not Found with Id " + occasionId));
    }

    @Override
    public Occasion deleteOccasion(Integer occasionId) throws OccasionException {
        var occasion = getOccasion(occasionId);
        occasionRepo.delete(occasion);
        return occasion;
    }

    @Override
    public List<Occasion> getOccasions() throws OccasionException {
        var occasions = occasionRepo.findAll();
        if (occasions.isEmpty())
            throw new OccasionException("No Occasions Found ");
        return occasions;
    }

    @Override
    public List<Occasion> getFutureOccasions() throws OccasionException {
        var occasions = occasionRepo.findAll();
        if (occasions.isEmpty())
            throw new OccasionException("No Occasions Found ");
        return occasions.stream().filter(o -> o.getOccasionDate().isAfter(LocalDate.now())).toList();
    }
}
