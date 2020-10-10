package com.idealista.infrastructure.adapter;

import com.idealista.domain.model.DomainPublicAd;
import com.idealista.domain.port.primary.IAdService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class AdAdapter {

    IAdService iAdService;

    public List<DomainPublicAd> getPublicAds() {
        return iAdService.getPublicAds();
    }

}
