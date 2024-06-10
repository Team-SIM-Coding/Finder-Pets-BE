package inf.saveanimals.repository.posts.sighted;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import inf.saveanimals.domain.animals.common.Breed;
import inf.saveanimals.domain.animals.common.BreedGroup;
import inf.saveanimals.domain.areas.City;
import inf.saveanimals.domain.areas.Districts;
import inf.saveanimals.domain.posts.common.IsMainImg;
import inf.saveanimals.domain.posts.lost.QLostPets;
import inf.saveanimals.domain.posts.sighted.QSightedImg;
import inf.saveanimals.domain.posts.sighted.QSightedPets;
import inf.saveanimals.repository.posts.lost.SearchLostPetsRepository;
import inf.saveanimals.request.posts.SearchCondition;
import inf.saveanimals.response.posts.lostPets.LostPetsThumbnailResponse;
import inf.saveanimals.response.posts.lostPets.QLostPetsThumbnailResponse;
import inf.saveanimals.response.posts.sightedPets.QSightedPetsThumbnailResponse;
import inf.saveanimals.response.posts.sightedPets.SightedPetsThumbnailResponse;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

/**
 * 리포지토리 구현체
 */

public class SearchSightedPetsRepositoryImpl implements SearchSightedPetsRepository {

    private final JPAQueryFactory queryFactory;

    public SearchSightedPetsRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public Page<SightedPetsThumbnailResponse> findAllBySearchCondition(SearchCondition searchCondition, Pageable pageable) {
        QSightedPets sightedPets = QSightedPets.sightedPets;
        QSightedImg sightedImg = QSightedImg.sightedImg;

        QueryResults<SightedPetsThumbnailResponse> results  = queryFactory.select(
                        new QSightedPetsThumbnailResponse(
                                sightedPets.id,
                                sightedPets.category,
                                sightedPets.isCompleted,
                                sightedPets.foundDate,
                                sightedPets.createdAt,
                                sightedPets.foundPlace,
                                sightedPets.city,
                                sightedPets.districts,
                                sightedPets.breedGroup,
                                sightedPets.breed,
                                sightedImg.imgUrl.as("thumbnailImgPath"),
                                sightedPets.views,
                                sightedPets.totalLike
                        ))
                .from(sightedImg)
                .join(sightedImg.sightedPets, sightedPets)
                .where(sightedImg.isMainImg.eq(IsMainImg.Y)
                        .and(eqBreedGroup(searchCondition.getBreedGroup()))
                        .and(eqBreed(searchCondition.getBreed()))
                        .and(eqCity(searchCondition.getCity()))
                        .and(eqDistrict(searchCondition.getDistricts())))
                .orderBy(sightedPets.id.desc()) // 최신순
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(results.getResults(),pageable,results.getTotal());
    }

    @Override
    public Page<SightedPetsThumbnailResponse> findAllByAccount(Long userId, Pageable pageable) {
        QSightedPets sightedPets = QSightedPets.sightedPets;
        QSightedImg sightedImg = QSightedImg.sightedImg;

        QueryResults<SightedPetsThumbnailResponse> results  = queryFactory.select(
                        new QSightedPetsThumbnailResponse(
                                sightedPets.id,
                                sightedPets.category,
                                sightedPets.isCompleted,
                                sightedPets.foundDate,
                                sightedPets.createdAt,
                                sightedPets.foundPlace,
                                sightedPets.city,
                                sightedPets.districts,
                                sightedPets.breedGroup,
                                sightedPets.breed,
                                sightedImg.imgUrl.as("thumbnailImgPath"),
                                sightedPets.views,
                                sightedPets.totalLike
                        ))
                .from(sightedImg)
                .join(sightedImg.sightedPets, sightedPets)
                .where(sightedImg.isMainImg.eq(IsMainImg.Y)
                        .and(sightedPets.user.id.eq(userId)))
                .orderBy(sightedPets.id.desc()) // 최신순
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(results.getResults(),pageable,results.getTotal());
    }

    private BooleanExpression eqBreedGroup(BreedGroup breedGroup) {
        return breedGroup != null ? QSightedPets.sightedPets.breedGroup.eq(breedGroup) : null;
    }

    private BooleanExpression eqBreed(Breed breed) {
        return breed != null ? QSightedPets.sightedPets.breed.eq(breed) : null;
    }

    private BooleanExpression eqCity(City city) {
        return city != null ? QSightedPets.sightedPets.city.eq(city) : null;
    }

    private BooleanExpression eqDistrict(Districts districts) {
        return districts != null ? QSightedPets.sightedPets.districts.eq(districts) : null;
    }
}
