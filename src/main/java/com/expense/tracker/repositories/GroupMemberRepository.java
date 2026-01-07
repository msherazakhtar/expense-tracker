package com.expense.tracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expense.tracker.models.GroupMembersORM;

public interface GroupMemberRepository extends JpaRepository<GroupMembersORM, Long> {

}
