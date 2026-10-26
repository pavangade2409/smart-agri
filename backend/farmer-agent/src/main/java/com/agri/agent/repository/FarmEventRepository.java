package com.agri.agent.repository;
import com.agri.agent.memory.FarmEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FarmEventRepository extends JpaRepository<FarmEvent,Long> {
    List<FarmEvent> findTop20ByFarmIdOrderByEventTimeDesc(Long farmId);
}
