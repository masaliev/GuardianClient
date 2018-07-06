package com.github.masaliev.guardianclient.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.masaliev.guardianclient.R;
import com.github.masaliev.guardianclient.data.model.News;
import com.github.masaliev.guardianclient.databinding.ListItemNewsBinding;
import com.github.masaliev.guardianclient.ui.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private static final int ITEM_TYPE_LOADING = 1;
    private static final int ITEM_TYPE_NEWS = 2;


    private final List<News> items = new ArrayList<>();

    private boolean mIsLoading;

    private OnNewsClickListener mListener;

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == ITEM_TYPE_LOADING){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_loading, parent, false);
            return new LoadingViewHolder(view);
        }else {
            ListItemNewsBinding binding = ListItemNewsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new NewsViewHolder(binding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemViewType(int position) {
        if(position == items.size()){
            return ITEM_TYPE_LOADING;
        }else{
            return ITEM_TYPE_NEWS;
        }
    }

    @Override
    public int getItemCount() {
        return mIsLoading ? (items.size() + 1) : items.size();
    }

    public void setItems(List<? extends News> items){
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public void addItems(List<? extends News> items){
        int start = this.items.size();
        int itemCount = items.size();
        this.items.addAll(items);
        notifyItemRangeInserted(start, itemCount);
    }

    public void setListener(OnNewsClickListener listener) {
        mListener = listener;
    }

    public void setLoading(boolean loading) {
        mIsLoading = loading;
        if(mIsLoading){
            notifyItemInserted(items.size());
        }else {
            notifyItemRemoved(items.size());
        }
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

    public class LoadingViewHolder extends BaseViewHolder{

        LoadingViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBind(int position) {

        }
    }

    public interface OnNewsClickListener{
        void onNewsClick(News news);
    }
}
