package com.idealista.infrastructure.repository;

import com.idealista.domain.model.DomainAd;
import com.idealista.domain.port.secondary.IAdRepository;
import com.idealista.infrastructure.persistence.AdVO;
import com.idealista.infrastructure.persistence.InMemoryPersistence;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class AdRepository implements IAdRepository {

    InMemoryPersistence inMemoryPersistence;

    @Override
    public Optional<List<DomainAd>> getDomainAds() {
        return Optional.ofNullable(inMemoryPersistence.getAds()).map(this::adaptToDomainAdList);
    }

    private List<DomainAd> adaptToDomainAdList(List<AdVO> adVO) {
        return adVO.stream().map(adVO1 -> adaptToDomainAd(adVO1)).collect(Collectors.toList());
    }

    private DomainAd adaptToDomainAd(AdVO adVO) {
        DomainAd domainAd = new DomainAd();
        domainAd.setDescription(adVO.getDescription());
        domainAd.setGardenSize(adVO.getGardenSize());
        domainAd.setHouseSize(adVO.getHouseSize());
        domainAd.setId(adVO.getId());
        domainAd.setIrrelevantSince(adVO.getIrrelevantSince());
        domainAd.setPictures(adVO.getPictures());
        domainAd.setScore(adVO.getScore());
        domainAd.setTypology(adVO.getTypology());
        return domainAd;
    }

}
