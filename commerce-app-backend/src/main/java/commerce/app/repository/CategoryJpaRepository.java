package commerce.app.repository;

import commerce.app.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author chanwook
 */
public interface CategoryJpaRepository extends JpaRepository<Category, Long> {
}
