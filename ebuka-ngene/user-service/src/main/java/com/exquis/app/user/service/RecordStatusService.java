package com.exquis.app.user.service;

import com.exquis.app.user.constant.Generic;
import com.exquis.app.user.entity.RecordStatus;
import com.exquis.app.user.enums.StatusType;
import com.exquis.app.user.repository.RecordStatusRepository;
import com.exquis.app.user.service.contract.RecordStatusServiceContract;
import com.exquis.app.user.utility.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
public class RecordStatusService implements RecordStatusServiceContract {

    @Autowired
    private RecordStatusRepository recordStatusRepository;

    @Override
    @Transactional
    public RecordStatus create(StatusType type) {
        //new
        RecordStatus newRecordStatus = new RecordStatus();
        newRecordStatus.setCreatedAt(LocalDateTime.now());
        newRecordStatus.setCreatedBy(Generic.ISSUER); // created_by should be issuer for now
        newRecordStatus.setStatus(type);

        return recordStatusRepository.save(newRecordStatus); // call repo to save data
    }

    @Override
    public RecordStatus create(StatusType type, String createdBy) {
        //new
        RecordStatus newRecordStatus = new RecordStatus();
        newRecordStatus.setCreatedAt(LocalDateTime.now());
        newRecordStatus.setCreatedBy(createdBy);
        newRecordStatus.setStatus(type);

        return recordStatusRepository.save(newRecordStatus); // call repo to save data
    }

    @Override
    @Transactional
    public RecordStatus update(long recordStatusId, StatusType type) {
        RecordStatus recordStatus = exist(recordStatusId);

        if(Helper.isNotEmpty(recordStatus))
        {
            recordStatus.setUpdatedAt(LocalDateTime.now());
            recordStatus.setUpdatedBy(Generic.ISSUER);
            recordStatus.setStatus(type);

            recordStatusRepository.save(recordStatus);
        }
        return recordStatus;
    }

    @Override
    @Transactional
    public RecordStatus update(long recordStatusId, String updatedBy) {
        RecordStatus recordStatus = exist(recordStatusId);

        if(Helper.isNotEmpty(recordStatus))
        {
            recordStatus.setUpdatedAt(LocalDateTime.now());
            recordStatus.setUpdatedBy(updatedBy);

            recordStatusRepository.save(recordStatus);
        }
        return recordStatus;
    }

    @Override
    @Transactional
    public RecordStatus delete(long recordStatusId, String deletedBy) {
        RecordStatus recordStatus = exist(recordStatusId);

        if(Helper.isNotEmpty(recordStatus))
        {
            recordStatus.setDeletedAt(LocalDateTime.now());
            recordStatus.setDeletedBy(deletedBy);
            recordStatus.setStatus(StatusType.DELETED); // partial delete

            recordStatusRepository.save(recordStatus);
        }
        return recordStatus;
    }

    @Override
    public RecordStatus exist(long recordStatusId) {
        return recordStatusRepository.findById(recordStatusId).orElse(null);
    }
}
