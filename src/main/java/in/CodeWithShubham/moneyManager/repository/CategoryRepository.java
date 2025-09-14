package in.CodeWithShubham.moneyManager.repository;

import in.CodeWithShubham.moneyManager.dto.CategoryDTO;
import in.CodeWithShubham.moneyManager.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    // to find all the category created by user
    List<CategoryEntity> findByProfileId(Long profileId);

    // to check the category is created by the curr user or not;
    Optional<CategoryEntity> findByIdAndProfileId(Long id, Long profileId);

    List<CategoryEntity> findByTypeAndProfileId(String type, Long profileId);

    Boolean existsByNameAndProfileId(String name, Long profileId);


}
