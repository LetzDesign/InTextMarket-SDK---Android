package com.intext.intextmarket2.views.adapters;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.flexbox.FlexboxLayout;
import com.intext.intextmarket2.views.IWebViewFragment;
import com.intext.intextmarket2.R;
import com.intext.intextmarket2.api.pojo.Business;
import com.intext.intextmarket2.api.pojo.IMBusinessResponse;
import com.intext.intextmarket2.api.pojo.SharedBusinessObject;
import com.intext.intextmarket2.permissions.IMarketPermission;

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

public class IMarketsAdapter extends RecyclerView.Adapter<IMarketsAdapter.CategoryViewHolder> {

    private Context context;
    private Activity activity;
    private IMBusinessResponse imBusinessResponse;
    private IMarketAdapterListener iMarketAdapterListener;
    private FragmentManager fragmentManager;

    public IMarketsAdapter(Activity activity, Context context, IMBusinessResponse imBusinessResponse, IMarketAdapterListener iMarketAdapterListener, FragmentManager fragmentManager) {
        this.activity = activity;
        this.context = context;
        this.imBusinessResponse = imBusinessResponse;
        this.iMarketAdapterListener = iMarketAdapterListener;
        this.fragmentManager = fragmentManager;
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

        //Events
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

        viewHolder.phoneEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                IMarketPermission iMarketPermission = new IMarketPermission(activity, context);
                iMarketPermission.permission(Manifest.permission.CALL_PHONE);

                if(!iMarketPermission.hasPermission()) {

                    iMarketPermission.checkPermission();

                }else{
                    //TODO send click top API
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + businessToHolder.getPhone()));
                    context.startActivity(callIntent);
                }
            }
        });

        viewHolder.webEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IWebViewFragment iWebViewFragment = IWebViewFragment.newInstance(businessToHolder.getSiteUrl());
                iWebViewFragment.show(fragmentManager, "IWeb View");
            }
        });

        viewHolder.mapEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO send click top API
                Uri mapUri = Uri.parse("geo:0,0?q=" +
                        businessToHolder.getLocation().getLatitude().toString() + "," +
                        businessToHolder.getLocation().getLongitude().toString() +
                        "("+businessToHolder.getName()+" by InTextWords)"
                );

                Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if(mapIntent.resolveActivity(activity.getPackageManager()) != null)
                    activity.startActivity(mapIntent);
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
        ImageView webEvent, phoneEvent, mapEvent;
        TextView businessName, businessEmail, singleShare;
        FlexboxLayout flexboxLayout;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryImageButton = itemView.findViewById(R.id.icategory_item_id);
            businessEmail = itemView.findViewById(R.id.ibusiness_email_id);
            businessName = itemView.findViewById(R.id.ibusiness_name_id);
            singleShare = itemView.findViewById(R.id.isingle_share);
            flexboxLayout = itemView.findViewById(R.id.iflex_layout_share);

            webEvent = itemView.findViewById(R.id.iweb_event_id);
            phoneEvent = itemView.findViewById(R.id.iweb_phone_id);
            mapEvent = itemView.findViewById(R.id.iweb_map_id);
        }
    }
}
