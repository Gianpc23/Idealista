package com.idealista.infrastructure.repository;

import com.idealista.domain.model.DomainAd;
import com.idealista.domain.model.DomainCompleteAd;
import com.idealista.domain.model.DomainPicture;
import com.idealista.domain.port.secondary.IAdRepository;
import com.idealista.infrastructure.model.CompleteAd;
import com.idealista.infrastructure.persistence.AdVO;
import com.idealista.infrastructure.persistence.InMemoryPersistence;
import com.idealista.infrastructure.persistence.PictureVO;
import org.springframework.stereotype.Repository;

import java.util.Collections;
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

    @Override
    public Optional<List<DomainCompleteAd>> getCompleteAds() {
        return Optional.ofNullable(inMemoryPersistence.completeAdList()).map(this::adaptToDomainCompleteAdList);
    }

    private List<DomainCompleteAd> adaptToDomainCompleteAdList(List<CompleteAd> completeAds) {
        return completeAds.stream().map(this::adaptToDomainCompleteAd).collect(Collectors.toList());
    }

    private List<DomainAd> adaptToDomainAdList(List<AdVO> adVO) {
        return adVO.stream().map(adVO1 -> adaptToDomainAd(adVO1)).collect(Collectors.toList());
    }

    private DomainCompleteAd adaptToDomainCompleteAd(CompleteAd completeAd) {
        DomainCompleteAd domainCompleteAd = new DomainCompleteAd();
        domainCompleteAd.setId(completeAd.getId());
        domainCompleteAd.setTypology(completeAd.getTypology());
        domainCompleteAd.setDescription(completeAd.getDescription());
        domainCompleteAd.setPictures(adaptPicturesToDomainPictures(completeAd.getPictures()));
        domainCompleteAd.setHouseSize(completeAd.getHouseSize());
        domainCompleteAd.setGardenSize(completeAd.getGardenSize());
        domainCompleteAd.setScore(completeAd.getScore());
        domainCompleteAd.setIrrelevantSince(completeAd.getIrrelevantSince());
        return domainCompleteAd;
    }

    private List<DomainPicture> adaptPicturesToDomainPictures(List<PictureVO> pictureVOList) {
        List<DomainPicture> domainPictureList = Collections.emptyList();
        DomainPicture domainPictureAux = null;
        for(PictureVO pictureVO : pictureVOList) {
            domainPictureAux.setId(pictureVO.getId());
            domainPictureAux.setQuality(pictureVO.getQuality());
            domainPictureAux.setUrl(pictureVO.getUrl());

            domainPictureList.add(domainPictureAux);
        }
        return domainPictureList;
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
