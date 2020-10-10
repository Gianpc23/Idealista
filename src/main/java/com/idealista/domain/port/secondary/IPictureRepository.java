package com.idealista.domain.port.secondary;

import com.idealista.domain.model.DomainAd;
import com.idealista.domain.model.DomainPicture;

import java.util.List;
import java.util.Optional;

public interface IPictureRepository {

    Optional<List<DomainPicture>> getDomainPictures();

}
