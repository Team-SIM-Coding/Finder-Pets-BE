package inf.saveanimals.repository.posts.lost;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import inf.saveanimals.domain.animals.common.Breed;
import inf.saveanimals.domain.animals.common.BreedGroup;
import inf.saveanimals.domain.areas.City;
import inf.saveanimals.domain.areas.Districts;
import inf.saveanimals.domain.posts.common.IsMainImg;
import inf.saveanimals.domain.posts.lost.QLostImg;
import inf.saveanimals.domain.posts.lost.QLostPets;
import inf.saveanimals.request.posts.SearchCondition;
import inf.saveanimals.response.posts.lostPets.LostPetsThumbnailResponse;
import inf.saveanimals.response.posts.lostPets.QLostPetsThumbnailResponse;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class SearchLostPetsRepositoryImpl implements SearchLostPetsRepository {

    private final JPAQueryFactory queryFactory;

    public SearchLostPetsRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<LostPetsThumbnailResponse> findAllBySearchCondition(SearchCondition searchCondition, Pageable pageable) {
        QLostPets lostPets = QLostPets.lostPets;
        QLostImg lostImg = QLostImg.lostImg;

        QueryResults<LostPetsThumbnailResponse> results  = queryFactory.select(
                        new QLostPetsThumbnailResponse(
                                lostPets.id,
                                lostPets.category,
                                lostPets.isCompleted,
                                lostPets.breed,
                                lostPets.breedGroup,
                                lostPets.gender,
                                lostPets.weight,
                                lostPets.specialMark,
                                lostPets.happenPlace,
                                lostImg.imgUrl.as("thumbnailImgPath"),
                                lostPets.views,
                                lostPets.totalLike
                        ))
                .from(lostImg)
                .join(lostImg.lostPets, lostPets)
                .where(lostImg.isMainImg.eq(IsMainImg.Y)
                        .and(eqBreedGroup(searchCondition.getBreedGroup()))
                        .and(eqBreed(searchCondition.getBreed()))
                        .and(eqCity(searchCondition.getCity()))
                        .and(eqDistrict(searchCondition.getDistricts())))
                .orderBy(lostPets.id.desc()) // 최신순
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(results.getResults(),pageable,results.getTotal());
    }

    @Override
    public Page<LostPetsThumbnailResponse> findAllByAccount(Long userId, Pageable pageable) {
        QLostPets lostPets = QLostPets.lostPets;
        QLostImg lostImg = QLostImg.lostImg;

        QueryResults<LostPetsThumbnailResponse> results  = queryFactory.select(
                        new QLostPetsThumbnailResponse(
                                lostPets.id,
                                lostPets.category,
                                lostPets.isCompleted,
                                lostPets.breed,
                                lostPets.breedGroup,
                                lostPets.gender,
                                lostPets.weight,
                                lostPets.specialMark,
                                lostPets.happenPlace,
                                lostImg.imgUrl.as("thumbnailImgPath"),
                                lostPets.views,
                                lostPets.totalLike
                        ))
                .from(lostImg)
                .join(lostImg.lostPets, lostPets)
                .where(lostImg.isMainImg.eq(IsMainImg.Y)
                        .and(lostPets.user.id.eq(userId)))
                .orderBy(lostPets.id.desc()) // 최신순
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(results.getResults(),pageable,results.getTotal());
    }

    private BooleanExpression eqBreedGroup(BreedGroup breedGroup) {
        return breedGroup != null ? QLostPets.lostPets.breedGroup.eq(breedGroup) : null;
    }

    private BooleanExpression eqBreed(Breed breed) {
        return breed != null ? QLostPets.lostPets.breed.eq(breed) : null;
    }

    private BooleanExpression eqCity(City city) {
        return city != null ? QLostPets.lostPets.city.eq(city) : null;
    }

    private BooleanExpression eqDistrict(Districts districts) {
        return districts != null ? QLostPets.lostPets.districts.eq(districts) : null;
    }
}
