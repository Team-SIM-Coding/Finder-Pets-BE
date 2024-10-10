package inf.saveanimals.repository.posts.sighted;

import com.querydsl.jpa.impl.JPAQueryFactory;
import inf.saveanimals.domain.posts.sighted.QSightedPets;
import inf.saveanimals.controller.dto.SearchCondition;

import inf.saveanimals.response.posts.sightedPets.SightedPetsThumbnailResponse;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
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
       return null;
    }


}
