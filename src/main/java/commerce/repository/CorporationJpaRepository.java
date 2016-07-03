package commerce.repository;

import commerce.entity.Corporation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author chanwook
 */
public interface CorporationJpaRepository extends JpaRepository<Corporation, Long> {

}
