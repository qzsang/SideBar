package com.example.sidebar;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.halfbit.pinnedsection.PinnedSectionListView;

/**
 * Created by little on 2017/4/20 0020.
 */

public class ListAdapter extends BaseAdapter implements PinnedSectionListView.PinnedSectionListAdapter {
    private Context mContext;
    private List<DataBean> list;

    public ListAdapter(Context mContext, List<DataBean> list) {
        this.mContext = mContext;
        this.list = list;
        if (this.list == null) {
            this.list = new ArrayList<>();
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public DataBean getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            if (isItemViewTypePinned(getItemViewType(position))) {//标题
                convertView = View.inflate(mContext,R.layout.item_title,null);
            } else {
                convertView = View.inflate(mContext,R.layout.item_content,null);
            }
        }

        TextView textView = (TextView) convertView.findViewById(R.id.tv_text);
        textView.setText(getItem(position).getName() + "-name");

        return convertView;
    }


    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getType() == 1 ? 1 : 0;
    }


    @Override
    public boolean isItemViewTypePinned(int viewType) {
        return viewType == 1;
    }
}
