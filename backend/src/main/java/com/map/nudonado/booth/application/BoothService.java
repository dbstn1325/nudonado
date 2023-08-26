package com.map.nudonado.booth.application;

import com.map.nudonado.booth.domain.*;
import com.map.nudonado.booth.dto.request.BoothCreateRequest;
import com.map.nudonado.booth.dto.response.BoothIdResponse;
import com.map.nudonado.common.utils.GeometryUtil;
import com.map.nudonado.member.domain.Member;
import com.map.nudonado.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;

import org.locationtech.jts.geom.Point;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;


@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class BoothService {

    private final BoothRepository boothRepository;
    private final MemberRepository memberRepository;
    private final EntityManager em;

    private static final Double SEARCH_MAX_DISTANCE = 10.0;

    @Transactional
    public BoothIdResponse save(final Long memberId, final BoothCreateRequest request) throws ParseException {
        Member member = memberRepository.getById(memberId);

        Point point = convertRequestToPoint(request);
        Booth booth = request.toBooth(member, point);

        return new BoothIdResponse(boothRepository.save(booth));
    }

    public List<BoothDetail> findBoothsNearLocation(Double x, Double y) {
        Location northEast = GeometryUtil.calculate(x, y, SEARCH_MAX_DISTANCE, Direction.NORTHEAST.getBearing());
        Location southWest = GeometryUtil.calculate(x, y, SEARCH_MAX_DISTANCE, Direction.SOUTHWEST.getBearing());

        List<Booth> booths = searchBoothsWithinBounds(northEast, southWest);

        return convertBoothsToDTOs(booths);
    }


    public List<BoothDetail> findBoothsNearLocationByCategory(Double x, Double y, String category) {
        Location northEast = GeometryUtil.calculate(x, y, SEARCH_MAX_DISTANCE, Direction.NORTHEAST.getBearing());
        Location southWest = GeometryUtil.calculate(x, y, SEARCH_MAX_DISTANCE, Direction.SOUTHWEST.getBearing());

        List<Booth> booths = searchBoothsWithinBoundsByCategory(northEast, southWest, category);

        return convertBoothsToDTOs(booths);
    }


    public List<Booth> searchBoothsWithinBoundsByCategory(Location northEast, Location southWest, String category) {
        String sql = "SELECT * FROM booths AS b WHERE b.category = '" + Category.from(category) + "' AND MBRContains(ST_Envelope(ST_GeomFromText('LINESTRING("
                + northEast.getLatitude() + " " + northEast.getLongitude() + ", "
                + southWest.getLatitude() + " " + southWest.getLongitude() + ")')), b.point)";

        Query query = em.createNativeQuery(sql, Booth.class)
                .setMaxResults(10);

        return query.getResultList();
    }





    public Point convertRequestToPoint(BoothCreateRequest request) throws ParseException {
        return convertLocationToPoint(request.getLocation());
    }

    public List<Booth> searchBoothsWithinBounds(Location northEast, Location southWest) {
        String pointFormat = String.format(
                "'LINESTRING(%f %f, %f %f)'",
                northEast.getLatitude(), northEast.getLongitude(), southWest.getLatitude(), southWest.getLongitude()
        );

        Query query = em.createNativeQuery(
                "" +
                        "SELECT * FROM booths AS b WHERE MBRContains(ST_LINESTRINGFROMTEXT(" + pointFormat + "), b.point)",
                Booth.class
        ).setMaxResults(10);

        return query.getResultList();
    }

    public List<BoothDetail> convertBoothsToDTOs(List<Booth> booths) {
        return booths.stream()
                .map(BoothDetail::fromEntity)
                .collect(Collectors.toList());
    }

    public Point convertLocationToPoint(Location location) throws ParseException {
        if (location != null) {
            return createPointFromCoordinates(location.getLatitude(), location.getLongitude());
        }
        return null;
    }

    public Point createPointFromCoordinates(Double latitude, Double longitude) throws ParseException {
        String pointWKT = String.format("POINT(%s %s)", latitude, longitude);
        return (Point) new WKTReader().read(pointWKT);
    }

}
