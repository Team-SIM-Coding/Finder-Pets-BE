package inf.saveanimals.repository.posts.sighted;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import inf.saveanimals.domain.posts.lost.LostComments;
import inf.saveanimals.domain.posts.sighted.QSightedComments;
import inf.saveanimals.domain.posts.sighted.QSightedPets;
import inf.saveanimals.controller.dto.SearchCondition;
import inf.saveanimals.domain.posts.sighted.SightedComments;
import inf.saveanimals.domain.posts.sighted.SightedPets;
import inf.saveanimals.response.posts.sightedPets.SightedPetCommentDto;
import inf.saveanimals.response.posts.sightedPets.SightedPetsThumbnailResponse;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        QSightedPets sightedPet = QSightedPets.sightedPets;

        // whereClause
        BooleanBuilder whereClause = buildSearchCondition(searchCondition, sightedPet);

        List<SightedPets> sightedPetsList = queryFactory.selectFrom(sightedPet)
                .distinct()
                .where(whereClause)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory.select(sightedPet.count())
                .from(sightedPet)
                .where(whereClause)
                .fetchOne();

        // Map SightedPets(Entity) to Dto
        List<SightedPetsThumbnailResponse> finalSightedPetsPets = sightedPetsList.stream()
                .map(this::convertDto)
                .toList();

        return new PageImpl<>(finalSightedPetsPets, pageable, total);
    }

    @Override
    public List<SightedPetCommentDto> findCommentByPetId(Long sightedPetsId) {
        List<SightedComments> allComments = queryFactory
                .selectFrom(QSightedComments.sightedComments)
                .where(QSightedComments.sightedComments.sightedPets.id.eq(sightedPetsId))
                .fetch();

        // dto 리스트로 변환
        return convertToCommentDto(allComments);
    }

    private List<SightedPetCommentDto> convertToCommentDto(List<SightedComments> allComments) {

        Map<Long, SightedPetCommentDto> commentDtoMap = new HashMap<>();
        List<SightedPetCommentDto> rootComments = new ArrayList<>();

        // 상위 댓글
        allComments.forEach(comment -> {
            SightedPetCommentDto commentDto = toDto(comment);
            commentDtoMap.put(commentDto.getId(), commentDto);

            // 부모 댓글이 없는 경우, 상위 댓글로 추가한다.
            if (comment.getSightedParent() == null) {
                rootComments.add(commentDto);
            }
        });

        // 자식 댓글이 있을 경우, 리스트에 추가한다.
        allComments.forEach(comment -> {
            if (comment.getSightedParent() != null) {
                Long parentId = comment.getSightedParent().getId();
                SightedPetCommentDto parentDto = commentDtoMap.get(parentId);

                if (parentDto != null) {
                    parentDto.getChildren().add(commentDtoMap.get(comment.getId()));
                }
            }
        });

        return rootComments;
    }

    private SightedPetCommentDto toDto(SightedComments comment) {
        return new SightedPetCommentDto(
                comment.getId(),
                comment.getUser().getId(),
                comment.getUser().getNickname(),
                comment.getUser().getImg(),
                comment.getContent(),
                comment.getCreate_at()
        );
    }

    private static BooleanBuilder buildSearchCondition(SearchCondition condition, QSightedPets sightedPets) {
        BooleanBuilder whereClause = new BooleanBuilder();

        // 품종
        if (condition.getAnimal() != null) {
            whereClause.and(sightedPets.breed.eq(condition.getAnimal()));
        }

        // 동물 분류
        if (condition.getKind() != null) {
            whereClause.and(sightedPets.breedGroup.eq(condition.getKind()));
        }

        if (condition.getCity() != null) {
            whereClause.and(sightedPets.city.eq(condition.getCity()));
        }

        if (condition.getDistrict() != null) {
            whereClause.and(sightedPets.district.eq(condition.getDistrict()));
        }

        return whereClause;
    }

    private SightedPetsThumbnailResponse convertDto(SightedPets sightedPets) {
        return SightedPetsThumbnailResponse.fromEntity(sightedPets);
    }

}
