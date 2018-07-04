package com.github.masaliev.guardianclient.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.masaliev.guardianclient.data.model.News;
import com.github.masaliev.guardianclient.databinding.ListItemNewsBinding;
import com.github.masaliev.guardianclient.ui.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<? extends News> items = new ArrayList<>();

    private OnNewsClickListener mListener;

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemNewsBinding binding = ListItemNewsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new NewsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public void setItems(List<? extends News> items){
        this.items = items;
        notifyDataSetChanged();
    }

    public void setListener(OnNewsClickListener listener) {
        mListener = listener;
    }

    public class NewsViewHolder extends BaseViewHolder{

        private ListItemNewsBinding mBinding;

        NewsViewHolder(ListItemNewsBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            News item = items.get(position);
            mBinding.setViewModel(new NewsItemViewModel(item));
            // Immediate Binding
            // When a variable or observable changes, the binding will be scheduled to change before
            // the next frame. There are times, however, when binding must be executed immediately.
            // To force execution, use the executePendingBindings() method.
            mBinding.executePendingBindings();

            mBinding.getRoot().setOnClickListener(v -> {
                if(mListener != null){
                    mListener.onNewsClick(item);
                }
            });

        }
    }

    public interface OnNewsClickListener{
        void onNewsClick(News news);
    }
}
