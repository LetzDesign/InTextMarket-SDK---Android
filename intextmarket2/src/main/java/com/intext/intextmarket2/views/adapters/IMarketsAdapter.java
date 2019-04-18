package com.intext.intextmarket2.views.adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.flexbox.FlexboxLayout;
import com.intext.intextmarket2.R;
import com.intext.intextmarket2.api.pojo.Business;
import com.intext.intextmarket2.api.pojo.IMBusinessResponse;
import com.intext.intextmarket2.api.pojo.SharedBusinessObject;

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

public class IMarketsAdapter extends RecyclerView.Adapter<IMarketsAdapter.CategoryViewHolder>{

    private Context context;
    private IMBusinessResponse imBusinessResponse;
    private IMarketAdapterListener iMarketAdapterListener;

    public IMarketsAdapter(Context context, IMBusinessResponse imBusinessResponse, IMarketAdapterListener iMarketAdapterListener) {
        this.context = context;
        this.imBusinessResponse = imBusinessResponse;
        this.iMarketAdapterListener = iMarketAdapterListener;
    }

    public interface IMarketAdapterListener{
        void onSingleShareClick(SharedBusinessObject market);
    }

    @NonNull
    @Override
    public IMarketsAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View businessView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_icategory, viewGroup,false);

        return new CategoryViewHolder(businessView);
    }

    @Override
    public void onBindViewHolder(@NonNull IMarketsAdapter.CategoryViewHolder viewHolder, int i) {

        final Business businessToHolder = imBusinessResponse.getService().getBusiness().get(i);

        viewHolder.businessName.setText(businessToHolder.getName());
        viewHolder.businessEmail.setText(businessToHolder.getEmail());

        switch (businessToHolder.getCategoryId()){
            case 2:
                viewHolder.categoryImageButton.setImageResource(R.drawable.ic_department_itx);
                break;
            case 3://hospital
                viewHolder.categoryImageButton.setImageResource(R.drawable.ic_department_itx);
                break;
            case 4:
                viewHolder.categoryImageButton.setImageResource(R.drawable.ic_bank_itx);
                break;
            case 5://service
                viewHolder.categoryImageButton.setImageResource(R.drawable.ic_department_itx);
                break;
            default:
                viewHolder.categoryImageButton.setImageResource(R.drawable.ic_restaurant_itx);
                break;
        }

        viewHolder.categoryImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, businessToHolder.getName(), Toast.LENGTH_LONG).show();
            }
        });

        viewHolder.singleShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createSingleShareBusinessObject(businessToHolder);
            }
        });

        viewHolder.flexboxLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createSingleShareBusinessObject(businessToHolder);
            }
        });
    }

    @Override
    public int getItemCount() {
        return imBusinessResponse.getService().getBusiness().size();
    }

    private void createSingleShareBusinessObject(Business businessToHolder){
        if(businessToHolder != null){
            SharedBusinessObject sharedBusinessObject = new SharedBusinessObject();

            sharedBusinessObject.setBusinessName(businessToHolder.getName());
            sharedBusinessObject.setBusinessAddress(businessToHolder.getAddress());
            sharedBusinessObject.setBusinessPhone(businessToHolder.getPhone());
            sharedBusinessObject.setBusinessEmail(businessToHolder.getEmail());
            sharedBusinessObject.setLongitude(businessToHolder.getLocation().getLatitude());
            sharedBusinessObject.setLongitude(businessToHolder.getLocation().getLongitude());

            iMarketAdapterListener.onSingleShareClick(sharedBusinessObject);
        }
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder{

        ImageButton categoryImageButton;
        TextView businessName, businessEmail, singleShare;
        FlexboxLayout flexboxLayout;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryImageButton = itemView.findViewById(R.id.icategory_item_id);
            businessEmail = itemView.findViewById(R.id.ibusiness_email_id);
            businessName = itemView.findViewById(R.id.ibusiness_name_id);
            singleShare = itemView.findViewById(R.id.isingle_share);
            flexboxLayout = itemView.findViewById(R.id.iflex_layout_share);
        }
    }
}
