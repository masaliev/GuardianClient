package com.github.masaliev.guardianclient.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.github.masaliev.guardianclient.data.model.Section;
import com.github.masaliev.guardianclient.databinding.ListItemSectionBinding;
import com.github.masaliev.guardianclient.ui.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class SectionAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private final List<Section> items = new ArrayList<>();
    private OnSectionClickListener mListener;

    private Section mSelectedSection;

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SectionViewHolder(ListItemSectionBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setListener(OnSectionClickListener listener) {
        mListener = listener;
    }

    public void setItems(List<? extends Section> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public void setSelectedSection(Section section){
        int oldSelectedSectionPosition = -1;
        if(mSelectedSection != null) {
            oldSelectedSectionPosition = items.indexOf(mSelectedSection);
        }
        mSelectedSection = section;

        if(oldSelectedSectionPosition >= 0){
            notifyItemChanged(oldSelectedSectionPosition);
        }

        int position = items.indexOf(section);
        if(position >= 0){
            notifyItemChanged(position);
        }
    }

    public class SectionViewHolder extends BaseViewHolder{

        ListItemSectionBinding mBinding;

        public SectionViewHolder(ListItemSectionBinding binding) {
            super(binding.getRoot());

            mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            Section item = items.get(position);
            mBinding.setViewModel(new SectionItemViewModel(item, item.equals(mSelectedSection)));
            // Immediate Binding
            // When a variable or observable changes, the binding will be scheduled to change before
            // the next frame. There are times, however, when binding must be executed immediately.
            // To force execution, use the executePendingBindings() method.
            mBinding.executePendingBindings();

            mBinding.getRoot().setOnClickListener(v -> {
                setSelectedSection(item);
                if(mListener != null){
                    mListener.onSectionClick(item);
                }
            });
        }
    }

    public interface OnSectionClickListener{
        void onSectionClick(Section section);
    }
}
