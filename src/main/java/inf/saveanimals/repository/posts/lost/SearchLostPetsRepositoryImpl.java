package inf.saveanimals.repository.posts.lost;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import inf.saveanimals.domain.posts.lost.LostPets;
import inf.saveanimals.domain.posts.lost.QLostComments;

import inf.saveanimals.domain.posts.lost.QLostPets;
import inf.saveanimals.domain.users.QUser;
import inf.saveanimals.controller.dto.SearchCondition;
import inf.saveanimals.response.posts.lostPets.LostPetCommentDto;
import inf.saveanimals.response.posts.lostPets.LostPetsThumbnailResponse;

import inf.saveanimals.response.posts.lostPets.QLostPetCommentDto;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import java.util.List;


public class SearchLostPetsRepositoryImpl implements SearchLostPetsRepository {

    private final JPAQueryFactory queryFactory;

    public SearchLostPetsRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<LostPetsThumbnailResponse> findAllBySearchCondition(SearchCondition searchCondition, Pageable pageable) {
        QLostPets lostPet = QLostPets.lostPets;

        // whereClause
        BooleanBuilder whereClause = buildSearchCondition(searchCondition, lostPet);

        List<LostPets> lostPetsList = queryFactory.selectFrom(lostPet)
                .distinct()
                .where(whereClause)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory.select(lostPet.count())
                .from(lostPet)
                .where(whereClause)
                .fetchOne();

        // Map LostPets(Entity) to Dto
        List<LostPetsThumbnailResponse> finalLostPets = lostPetsList.stream()
                .map(this::convertDto)
                .toList();

        return new PageImpl<>(finalLostPets, pageable, total);

    }


    @Override
    public List<LostPetCommentDto> findCommentByPetId(Long lostPetsId) {
        QLostComments lostComments = QLostComments.lostComments;
        QLostPets lostPets = QLostPets.lostPets;
        QUser user = QUser.user;

        return queryFactory.select(new QLostPetCommentDto(
                        lostPets.id.as("id"),
                        user.id.as("userId"),
                        user.nickname.as("user_nickname"),
                        user.img.as("user_profile_image"),
                        lostComments.content,
                        lostComments.create_at.as("createdAt")
                ))
                .from(lostComments)
                .join(lostComments.lostPets, lostPets)
                .join(lostComments.user, user)
                .where(lostComments.lostPets.id.eq(lostPetsId))
                .fetch();
    }

    private static BooleanBuilder buildSearchCondition(SearchCondition condition, QLostPets lostPets) {
        BooleanBuilder whereClause = new BooleanBuilder();

        // 품종
        if (condition.getAnimal() != null) {
            whereClause.and(lostPets.breed.eq(condition.getAnimal()));
        }

        // 동물 분류
        if (condition.getKind() != null) {
            whereClause.and(lostPets.breedGroup.eq(condition.getKind()));
        }

        if (condition.getCity() != null) {
            whereClause.and(lostPets.city.eq(condition.getCity()));
        }

        if (condition.getDistrict() != null) {
            whereClause.and(lostPets.district.eq(condition.getDistrict()));
        }

        return whereClause;
    }

    private LostPetsThumbnailResponse convertDto(LostPets lostPets) {
       return LostPetsThumbnailResponse.fromEntity(lostPets);
    }

}
