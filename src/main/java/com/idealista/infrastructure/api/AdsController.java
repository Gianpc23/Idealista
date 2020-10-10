package com.idealista.infrastructure.api;

import java.util.List;
import java.util.Optional;

import com.idealista.domain.model.DomainPublicAd;
import com.idealista.infrastructure.adapter.AdAdapter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@AllArgsConstructor
public class AdsController {

    private AdAdapter adAdapter;

    @GetMapping(value="/ads", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<T>> adsListing(@RequestParam Optional<String> userRole) {
        //TODO rellena el cuerpo del método
        adAdapter.getPublicAds();
        return ResponseEntity.ok(adAdapter.getPublicAds());
        //return ResponseEntity.notFound().build();
    }

    /*
    //TODO añade url del endpoint
    @GetMapping(value="/ads", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PublicAd>> publicListing(@RequestParam Optional<String> userRole) {
        //TODO rellena el cuerpo del método
        return ResponseEntity.notFound().build();
    }
    */

    //TODO añade url del endpoint
    @GetMapping()
    public ResponseEntity<Void> calculateScore() {
        //TODO rellena el cuerpo del método
        return ResponseEntity.notFound().build();
    }

}
