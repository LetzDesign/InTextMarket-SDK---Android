package com.intext.intextmarket2.views.adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.intext.intextmarket2.R;
import com.intext.intextmarket2.api.pojo.Business;
import com.intext.intextmarket2.api.pojo.IMBusinessResponse;

/**
 * Created by Ing. Letzer Cartagena Negron
 * InTextChat @2019
 * @author INTEXT SOFTWARE LLC
 *
 * Copyright (C) 2019 INTEXT SOFTWARE LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class IMarketCategoryAdapter extends RecyclerView.Adapter<IMarketCategoryAdapter.CategoryViewHolder>{

    private Context context;
    private IMBusinessResponse imBusinessResponse;

    public IMarketCategoryAdapter(Context context, IMBusinessResponse imBusinessResponse) {
        this.context = context;
        this.imBusinessResponse = imBusinessResponse;
    }

    @NonNull
    @Override
    public IMarketCategoryAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View categoryView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_icategory, viewGroup,false);

        return new CategoryViewHolder(categoryView);
    }

    @Override
    public void onBindViewHolder(@NonNull IMarketCategoryAdapter.CategoryViewHolder viewHolder, int i) {

        final Business businessToHolder = imBusinessResponse.getService().getBusiness().get(i);

        viewHolder.categoryImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, businessToHolder.getName(), Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return imBusinessResponse.getService().getCategories().size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder{

        ImageButton categoryImageButton;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryImageButton = itemView.findViewById(R.id.icategory_item_id);
        }
    }
}
