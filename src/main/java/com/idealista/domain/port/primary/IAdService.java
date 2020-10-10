package com.idealista.domain.port.primary;

import com.idealista.domain.model.DomainAd;
import com.idealista.domain.model.DomainCompleteAd;
import com.idealista.domain.model.DomainPublicAd;

import java.util.List;

public interface IAdService {
    List<DomainPublicAd> getPublicAds();
    List<DomainCompleteAd> getCompleteAds();
}
