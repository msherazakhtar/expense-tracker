package com.expense.tracker.services;

import java.util.List;

import com.expense.tracker.dtos.GroupRecord;

public interface GroupsService {

    GroupRecord createGroup(GroupRecord groupRecord);

    List<GroupRecord> getGroupByUserId(Long userId);

    String deleteGroup(Long groupId);

}
