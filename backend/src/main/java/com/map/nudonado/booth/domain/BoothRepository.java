package com.map.nudonado.booth.domain;

import com.map.nudonado.booth.domain.exception.BoothNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoothRepository extends JpaRepository<Booth, Long> {

    @Query(value = "SELECT b FROM Booth b WHERE b.location.longitude BETWEEN :minLongitude AND :maxLongitude AND b.location.latitude BETWEEN :minLatitude AND :maxLatitude")
    List<Booth> findNearbyBooths(@Param("minLongitude") Double minLongitude,
                                 @Param("maxLongitude") Double maxLongitude,
                                 @Param("minLatitude") Double minLatitude,
                                 @Param("maxLatitude") Double maxLatitude);

    default Booth getById(final Long id) {
        return this.findById(id)
                .orElseThrow(BoothNotFoundException::new);
    }
}
