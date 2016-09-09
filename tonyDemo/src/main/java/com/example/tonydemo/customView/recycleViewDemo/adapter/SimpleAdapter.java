/*
 * ******************************************************************************
 *   Copyright (c) 2014 Gabriele Mariotti.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *  *****************************************************************************
 */
package com.example.tonydemo.customView.recycleViewDemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tonydemo.util.LogUtils;
import com.example.tonydemo.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



/**
 * @author Gabriele Mariotti (gabri.mariotti@gmail.com)
 */
public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.SimpleViewHolder>  {
    private static String TAG=SimpleAdapter.class.getSimpleName();

    public static final int LAST_POSITION = -1 ;
    private final Context mContext;
    private List<String> mData;

    public void add(String s,int position) {
        position = position == LAST_POSITION ? getItemCount()  : position;
        mData.add(position,s);
        notifyItemInserted(position);
        //notifyDataSetChanged();  //解决中间插入数据时位置的不同步
    }

    public void remove(int position){
        if (position == LAST_POSITION && getItemCount()>0)
            position = getItemCount() -1 ;

        if (position > LAST_POSITION && position < getItemCount()) {
            mData.remove(position);
            notifyItemRemoved(position);
        }
        //notifyDataSetChanged();   //解决中间插入数据时位置的不同步
    }


    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public TextView title;

        public SimpleViewHolder(View view) {
            super(view);
            LogUtils.i(TAG,"SimpleViewHolder");
            title = (TextView) view.findViewById(R.id.simple_text);
        }
    }

    public SimpleAdapter(Context context, String[] data) {
        mContext = context;
        if (data != null)
            mData = new ArrayList<String>(Arrays.asList(data));
        else mData = new ArrayList<String>();
    }

    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LogUtils.i(TAG,"onCreateViewHolder");
        View view = LayoutInflater.from(mContext).inflate(R.layout.simple_item, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder,final int position) {
        LogUtils.i(TAG,"onBindViewHolder");
        holder.title.setText(mData.get(position));
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Position =" + position, Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public int getItemCount() {
        return mData.size();
    }
}
