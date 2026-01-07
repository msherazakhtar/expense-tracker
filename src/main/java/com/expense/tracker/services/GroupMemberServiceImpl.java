package com.expense.tracker.services;

import org.springframework.stereotype.Service;

import com.expense.tracker.dtos.GroupMembersRecord;
import com.expense.tracker.models.GroupMembersORM;
import com.expense.tracker.repositories.GroupMemberRepository;
import com.expense.tracker.utilities.MappingUtility;

@Service
public class GroupMemberServiceImpl implements GroupMembersService {
    private GroupMemberRepository groupMemberRepository;

    public GroupMemberServiceImpl(GroupMemberRepository groupMemberRepository) {
        this.groupMemberRepository = groupMemberRepository;
    }

    @Override
    public GroupMembersRecord saveGroupMembers(GroupMembersRecord groupMembersRecord) {
        GroupMembersORM groupMemberToSave = groupMemberRepository
                .save(MappingUtility.groupMemebrRecordToORM(groupMembersRecord));
        return MappingUtility.groupMemberORMToRecord(groupMemberToSave);
    }

}
