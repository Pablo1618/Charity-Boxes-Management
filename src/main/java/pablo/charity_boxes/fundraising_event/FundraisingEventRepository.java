package pablo.charity_boxes.fundraising_event;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface FundraisingEventRepository extends JpaRepository<FundraisingEvent, Long> {
    Optional<FundraisingEvent> findByName(String name);
}
