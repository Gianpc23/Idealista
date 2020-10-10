package com.idealista.infrastructure.adapter;

import com.idealista.domain.model.DomainCompleteAd;
import com.idealista.domain.model.DomainPicture;
import com.idealista.domain.model.DomainPublicAd;
import com.idealista.domain.port.primary.IAdService;
import com.idealista.infrastructure.api.IdealistaAd;
import com.idealista.infrastructure.api.PublicAd;
import com.idealista.infrastructure.api.QualityAd;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class AdAdapter {

    IAdService iAdService;

    public List<DomainPublicAd> getPublicAds() {
        return iAdService.getPublicAds();
    }

    public List<IdealistaAd> getIdealistaAds(String userRole) {
        List<DomainCompleteAd> domainCompleteAds = iAdService.getCompleteAds();
        if(userRole.equals("userRole")) {
            return transformFromDomainCompleteAdsListToPublicAdList(domainCompleteAds);
        } else if(userRole.equals("qualityRole")) {
            return transformFromDomainCompleteAdsListToQualityAdList(domainCompleteAds);
        }
        return null;
    }

    private List<IdealistaAd> transformFromDomainCompleteAdsListToPublicAdList(List<DomainCompleteAd> domainCompleteAds) {
        List<DomainCompleteAd> relevantAdList = removeIrrelevantsAds(sortCompleteAds(domainCompleteAds));
        List<IdealistaAd> idealistaAdListAux = Collections.emptyList();
        PublicAd publicAd = new PublicAd();
        for(DomainCompleteAd domainCompleteAd : relevantAdList) {
            publicAd.setId(domainCompleteAd.getId());
            publicAd.setDescription(domainCompleteAd.getDescription());
            publicAd.setPictureUrls(getPicturesUrlFromDomainPictures(domainCompleteAd.getPictures()));
            publicAd.setTypology(domainCompleteAd.getTypology());
            publicAd.setHouseSize(domainCompleteAd.getHouseSize());
            publicAd.setGardenSize(domainCompleteAd.getGardenSize());
            idealistaAdListAux.add(publicAd);
        }
        return idealistaAdListAux;
    }

    private List<DomainCompleteAd> sortCompleteAds(List<DomainCompleteAd> domainCompleteAds) {
        return domainCompleteAds.stream()
                .sorted(Comparator.comparingInt(DomainCompleteAd::getScore)).collect(Collectors.toList());
    }

    private List<DomainCompleteAd> removeIrrelevantsAds(List<DomainCompleteAd> sortedList) {
        return sortedList.stream().filter(domainCompleteAd -> domainCompleteAd.getScore() > 40)
                .collect(Collectors.toList());
    }

    private List<String> getPicturesUrlFromDomainPictures(List<DomainPicture> domainPictures) {
        List<String> picturesUrl = Collections.emptyList();
        for(DomainPicture domainPicture : domainPictures) {
            picturesUrl.add(domainPicture.getUrl());
        }
        return picturesUrl;
    }

    private List<IdealistaAd> transformFromDomainCompleteAdsListToQualityAdList(List<DomainCompleteAd> domainCompleteAds) {
        List<DomainCompleteAd> irrelevantAds = getIrrelevantAds(domainCompleteAds);
        List<IdealistaAd> idealistaAdListAux = Collections.emptyList();
        QualityAd qualityAd = new QualityAd();
        for(DomainCompleteAd domainCompleteAd : irrelevantAds) {
            qualityAd.setId(domainCompleteAd.getId());
            qualityAd.setDescription(domainCompleteAd.getDescription());
            qualityAd.setGardenSize(domainCompleteAd.getGardenSize());
            qualityAd.setHouseSize(domainCompleteAd.getHouseSize());
            qualityAd.setPictureUrls(getPicturesUrlFromDomainPictures(domainCompleteAd.getPictures()));
            qualityAd.setTypology(domainCompleteAd.getTypology());
            qualityAd.setScore(domainCompleteAd.getScore());
            qualityAd.setIrrelevantSince(domainCompleteAd.getIrrelevantSince());
            idealistaAdListAux.add(qualityAd);
        }
        return idealistaAdListAux;
    }

    private List<DomainCompleteAd> getIrrelevantAds(List<DomainCompleteAd> domainCompleteAds) {
        return domainCompleteAds.stream().filter(domainCompleteAd -> domainCompleteAd.getScore() < 40)
                .collect(Collectors.toList());
    }

}
