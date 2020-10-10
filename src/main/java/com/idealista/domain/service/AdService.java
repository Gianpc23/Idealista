package com.idealista.domain.service;

import com.idealista.domain.model.DomainAd;
import com.idealista.domain.model.DomainPicture;
import com.idealista.domain.model.DomainPublicAd;
import com.idealista.domain.port.primary.IAdService;
import com.idealista.domain.port.secondary.IAdRepository;
import com.idealista.domain.port.secondary.IPictureRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdService implements IAdService {

    @NonNull
    IAdRepository iAdRepository;

    @NonNull
    IPictureRepository iPictureRepository;

    @Override
    public List<DomainPublicAd> getPublicAds() {
        Optional<List<DomainAd>> domainAds = getDomainAds();
        Optional<List<DomainPicture>> domainPictures = getDomainPictures();
        if(!domainAds.isPresent()) return Collections.emptyList();
        return transformToDomainPublicAdsList(domainAds.get(), domainPictures.get());
    }

    private Optional<List<DomainAd>> getDomainAds() {
        return iAdRepository.getDomainAds();
    }

    private Optional<List<DomainPicture>> getDomainPictures() {
        return iPictureRepository.getDomainPictures();
    }

    private List<DomainPublicAd> transformToDomainPublicAdsList(List<DomainAd> domainAdsList,
                                                                List<DomainPicture> domainPictureList) {
        List<DomainPublicAd> domainPublicAds = Collections.emptyList();
        domainAdsList.stream().forEach(ad ->
                domainPublicAds.add(new DomainPublicAd(ad.getId(),ad.getTypology(),
                        ad.getDescription(), Collections.singletonList(
                                domainPictureList.stream().filter(pic ->
                                        pic.getId().equals(ad.getId())).findAny().orElse(null).getUrl()),
                        ad.getHouseSize(),ad.getGardenSize()))
        );
        return domainPublicAds;
    }

}
