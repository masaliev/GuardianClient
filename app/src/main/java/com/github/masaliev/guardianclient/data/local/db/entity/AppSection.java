package com.github.masaliev.guardianclient.data.local.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.github.masaliev.guardianclient.data.model.Section;

import java.io.Serializable;

@Entity
public class AppSection implements Section, Serializable {

    @PrimaryKey
    @NonNull
    public String id;

    public String title;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }
}
