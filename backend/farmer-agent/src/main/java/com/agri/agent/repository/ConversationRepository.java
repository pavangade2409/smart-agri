package com.agri.agent.repository;
import com.agri.agent.memory.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ConversationRepository extends JpaRepository<Conversation,Long> {
    Optional<Conversation> findByConversationId(String conversationId);
}
