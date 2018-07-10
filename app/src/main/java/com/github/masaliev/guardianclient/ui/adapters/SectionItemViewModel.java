package com.github.masaliev.guardianclient.ui.adapters;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import com.github.masaliev.guardianclient.data.model.Section;

public class SectionItemViewModel {

    public ObservableField<Section> section = new ObservableField<>();
    public ObservableBoolean isSelected = new ObservableBoolean(false);

    public SectionItemViewModel(Section section, boolean isSelected) {
        this.section.set(section);
        this.isSelected.set(isSelected);
    }
}
