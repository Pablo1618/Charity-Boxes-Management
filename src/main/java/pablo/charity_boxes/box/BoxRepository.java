package pablo.charity_boxes.box;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BoxRepository extends JpaRepository<Box, Long> {
    Optional<Box> findByName(String name);
}
