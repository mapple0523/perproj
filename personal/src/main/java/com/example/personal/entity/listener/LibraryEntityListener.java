package com.example.personal.entity.listener;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Slf4j
public class LibraryEntityListener {

    @PrePersist
    public void prePersist(Object o) {
        log.info("prepersist-------------------------------------");
        if(o instanceof DateListener) {
            ((DateListener)o).setCreatedAt(LocalDateTime.now());
            ((DateListener)o).setUpdatedAt(LocalDateTime.now());
        }
    }

    @PreUpdate
    public void preUpdate(Object o){

        if(o instanceof DateListener) {
            log.info("preupdate---------------------------------");
            ((DateListener)o).setCreatedAt(LocalDateTime.now());
            ((DateListener)o).setUpdatedAt(LocalDateTime.now());
        }


    }

}
