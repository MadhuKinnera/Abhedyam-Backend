package com.madhu.controller;

import com.madhu.dto.GeneralResponse;
import com.madhu.dto.OccasionDTO;
import com.madhu.exception.OccasionException;
import com.madhu.exception.UserException;
import com.madhu.service.OccasionService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SecurityRequirement(name = "scheme1")
@RestController
@RequestMapping("/occasion")
@AllArgsConstructor
public class OccasionController {


    private final OccasionService occasionService;

    @PostMapping
    ResponseEntity<GeneralResponse> addOccasion(@RequestBody OccasionDTO occasionDTO) {

        var generalResponse = new GeneralResponse();

        generalResponse.setMessage("Occasion Added with Name " + occasionDTO.getOccasionName());
        generalResponse.setData(occasionService.addOccasion(occasionDTO));

        return ResponseEntity.ok(generalResponse);
    }

    @GetMapping("/{occasionId}")
    ResponseEntity<GeneralResponse> getOccasion(@PathVariable Integer occasionId) throws OccasionException {
        var generalResponse = new GeneralResponse();

        generalResponse.setMessage(" Occasion Found with id  " + occasionId);
        generalResponse.setData(occasionService.getOccasion(occasionId));

        return ResponseEntity.ok(generalResponse);
    }

    @DeleteMapping("/{occasionId}")
    ResponseEntity<GeneralResponse> deleteOccasion(Integer occasionId) throws OccasionException {
        var generalResponse = new GeneralResponse();

        generalResponse.setMessage("Occasion Deleted With Id  " + occasionId);
        generalResponse.setData(occasionService.deleteOccasion(occasionId));

        return ResponseEntity.ok(generalResponse);
    }

    @GetMapping
    ResponseEntity<GeneralResponse> getOccasions() throws OccasionException {
        var generalResponse = new GeneralResponse();

        generalResponse.setMessage("Occasions Found  ");
        generalResponse.setData(occasionService.getOccasions());

        return ResponseEntity.ok(generalResponse);
    }

    @GetMapping("/futureOccasions")
    ResponseEntity<GeneralResponse> getFutureOccasions() throws OccasionException {
        var generalResponse = new GeneralResponse();

        generalResponse.setMessage("Future Occasions Found ");
        generalResponse.setData(occasionService.getFutureOccasions());

        return ResponseEntity.ok(generalResponse);
    }


}
