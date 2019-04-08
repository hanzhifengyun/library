package com.hanzhifengyun.library.common.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class CommonAdapter<T> extends BaseAdapter {

    protected Context mContext;
    private List<T> mTList;
    private int mLayoutId;

    public CommonAdapter(Context context, List<T> list, int layoutId) {
        this.mContext = context;
        this.mTList = list;
        this.mLayoutId = layoutId;
    }

    @Override
    public int getCount() {
        return mTList.size();
    }

    @Override
    public T getItem(int position) {
        return mTList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        CommonViewHolder viewHolder = CommonViewHolder.getCommonViewHolder(mContext, convertView, parent, mLayoutId, position);
        if (convertView == null) {
            firstConvert(viewHolder, getItem(position), position);
        }
        convert(viewHolder, getItem(position), position);
        return viewHolder.getConvertView();
    }

    protected void firstConvert(CommonViewHolder viewHolder, T item, int position) {

    }

    public abstract void convert(CommonViewHolder viewHolder, T t, int position);

    public void refresh(List<T> tList) {
        this.mTList = tList;
        notifyDataSetChanged();
    }

    public boolean isLastPosition(int position) {
        return position == mTList.size() - 1;
    }

    public List<T> getList() {
        return mTList;
    }
}
