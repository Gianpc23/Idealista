package com.idealista.infrastructure.repository;

import com.idealista.domain.model.DomainPicture;
import com.idealista.domain.port.secondary.IPictureRepository;
import com.idealista.infrastructure.persistence.InMemoryPersistence;
import com.idealista.infrastructure.persistence.PictureVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PictureRepository implements IPictureRepository {
    InMemoryPersistence inMemoryPersistence;

    @Override
    public Optional<List<DomainPicture>> getDomainPictures() {
        return Optional.ofNullable(inMemoryPersistence.getPictures()).map(this::adaptToDomainPictureList);
    }

    private List<DomainPicture> adaptToDomainPictureList(List<PictureVO> domainPictureList) {
        return domainPictureList.stream().map(domainPicture -> adaptToDomainPicture(domainPicture)).collect(Collectors.toList());
    }

    private DomainPicture adaptToDomainPicture(PictureVO pictureVO) {
        DomainPicture domainPicture = new DomainPicture(pictureVO.getId(), pictureVO.getUrl(), pictureVO.getQuality());
        return domainPicture;
    }

}
