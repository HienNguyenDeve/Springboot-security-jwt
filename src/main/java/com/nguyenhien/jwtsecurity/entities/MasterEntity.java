package com.nguyenhien.jwtsecurity.entities;

import java.time.ZonedDateTime;

import org.hibernate.annotations.TimeZoneStorage;
import org.hibernate.annotations.TimeZoneStorageType;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class MasterEntity extends BaseEntity {
    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    @Column(name = "inserted_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP")
    private ZonedDateTime insertedAt;

    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    @Column(columnDefinition = "TIMESTAMP")
    private ZonedDateTime updatedAt;

    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    @Column(columnDefinition = "TIMESTAMP")
    private ZonedDateTime deletedAt;

    @PrePersist
    public void prePersist() {
        this.insertedAt = ZonedDateTime.now();
    }

    @PrePersist
    public void preUpdate() {
        this.updatedAt = ZonedDateTime.now();
    }

}
