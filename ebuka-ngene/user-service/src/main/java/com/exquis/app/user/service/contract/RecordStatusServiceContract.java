package com.exquis.app.user.service.contract;

import com.exquis.app.user.entity.RecordStatus;
import com.exquis.app.user.enums.StatusType;

/**
 *
 * @author Chukwuebuka
 */
public interface RecordStatusServiceContract {
    RecordStatus create(StatusType type);
    RecordStatus create(StatusType type, String createdBy);
    RecordStatus update(long recordStatusId, StatusType type);
    RecordStatus update(long recordStatusId, String updatedBy);
    RecordStatus delete(long recordStatusId, String deletedBy);
    RecordStatus exist(long recordStatusId);
}
