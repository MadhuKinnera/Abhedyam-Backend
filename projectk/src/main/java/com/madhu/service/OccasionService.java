package com.madhu.service;

import com.madhu.dto.OccasionDTO;
import com.madhu.entity.Occasion;
import com.madhu.exception.OccasionException;

import java.util.List;

public interface OccasionService {

    Occasion addOccasion(OccasionDTO occasionDTO);

    Occasion getOccasion(Integer occasionI) throws OccasionException;

    Occasion deleteOccasion(Integer occasionId) throws OccasionException;

    List<Occasion> getOccasions() throws OccasionException;

    List<Occasion> getFutureOccasions() throws OccasionException;
}
