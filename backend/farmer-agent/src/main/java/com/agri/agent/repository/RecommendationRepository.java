package com.agri.agent.repository;
import com.agri.agent.memory.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
public interface RecommendationRepository extends JpaRepository<Recommendation,Long> {}
