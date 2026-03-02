package com.js4.js4.Repository;

import com.js4.js4.Entity.TicketComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketCommentRepo extends JpaRepository<TicketComment,Long> {
}
