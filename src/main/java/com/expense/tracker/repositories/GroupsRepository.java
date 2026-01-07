package com.expense.tracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.expense.tracker.dtos.GroupRecord;
import com.expense.tracker.models.GroupsORM;

public interface GroupsRepository extends JpaRepository<GroupsORM, Long> {
    @Query("""
                SELECT new com.expense.tracker.dtos.GroupRecord(
                    g.groupId,
                    g.name,
                    g.userId,
                    g.createdAt,
                    g.createdBy,
                    g.modifiedAt,
                    g.modifiedBy
                )
                FROM GroupsORM g
                WHERE g.userId = :userId
                  AND g.isDeleted = false
                ORDER BY g.createdAt DESC
            """)
    List<GroupRecord> findGroupsByUserId(Long userId);

}
