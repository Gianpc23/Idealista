package com.idealista.domain.port.secondary;

import com.idealista.domain.model.DomainAd;

import java.util.List;
import java.util.Optional;

public interface IAdRepository {

    Optional<List<DomainAd>> getDomainAds();

}
