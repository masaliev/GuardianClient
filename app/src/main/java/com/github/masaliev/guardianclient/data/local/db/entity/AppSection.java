package com.github.masaliev.guardianclient.data.local.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.github.masaliev.guardianclient.data.model.Section;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@SuppressWarnings("NullableProblems")
@Entity
public class AppSection implements Section, Serializable {

    @PrimaryKey
    @NonNull
    @Expose
    public String id;

    @Expose
    @SerializedName("webTitle")
    public String title;

    @NonNull
    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }
}
