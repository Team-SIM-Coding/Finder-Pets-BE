package inf.saveanimals.repository.posts.lost;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import inf.saveanimals.domain.posts.lost.LostComments;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

        List<LostComments> allComments = queryFactory
                .selectFrom(QLostComments.lostComments)
                .where(QLostComments.lostComments.lostPets.id.eq(lostPetsId))
                .fetch();

        // 댓글을 부모-자식 구조로 변환
        return convertToCommentDto(allComments);
    }

    private List<LostPetCommentDto> convertToCommentDto(List<LostComments> allComments) {

        Map<Long, LostPetCommentDto> commentDtoMap = new HashMap<>();
        List<LostPetCommentDto> rootComments = new ArrayList<>();

        // 1. 최상위 댓글을 필터링하고 DTO로 변환
        allComments.forEach(comment -> {
            LostPetCommentDto commentDto = toDto(comment);
            commentDtoMap.put(commentDto.getId(), commentDto);

            // 부모 댓글이 없는 경우 최상위 댓글로 추가
            if (comment.getLostParent() == null) {
                rootComments.add(commentDto);
            }
        });

// 2. 자식 댓글을 부모 댓글에 추가
        allComments.forEach(comment -> {
            if (comment.getLostParent() != null) {
                Long parentId = comment.getLostParent().getId();
                LostPetCommentDto parentDto = commentDtoMap.get(parentId);

                if (parentDto != null) {
                    parentDto.getChildren().add(commentDtoMap.get(comment.getId()));
                }
            }
        });

        return rootComments;
    }

    private LostPetCommentDto toDto(LostComments comment) {
        return new LostPetCommentDto(
                comment.getId(),
                comment.getUser().getId(),
                comment.getUser().getNickname(),
                comment.getUser().getImg(),
                comment.getContent(),
                comment.getCreate_at()
        );
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
