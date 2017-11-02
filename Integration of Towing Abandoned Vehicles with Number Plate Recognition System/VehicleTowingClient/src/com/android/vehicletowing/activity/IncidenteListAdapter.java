package com.android.vehicletowing.activity;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.vehicletowing.bean.IncidenteBean;

public class IncidenteListAdapter  extends BaseAdapter {
	
	//private final int[] bgColors = new int[] { R.color.list_bg_1, R.color.list_bg_2 };
	private final String TAG = IncidenteListAdapter.class.getCanonicalName();
	private Context mContext;
	private ArrayList<IncidenteBean> incidentBeanList;
	//Constructor
	public  IncidenteListAdapter(Context context,ArrayList<IncidenteBean> incidentBeanList){
		mContext=context;
		this.incidentBeanList = incidentBeanList;
	}
	
	public void updateLocationBeanList(ArrayList<IncidenteBean> productBeanList)
	{
		this.incidentBeanList = productBeanList;
	}
	
	@Override
	public void notifyDataSetChanged() {
		// TODO Auto-generated method stub
		super.notifyDataSetChanged();
		
	}


	@Override
	public void notifyDataSetInvalidated() {
		// TODO Auto-generated method stub
		super.notifyDataSetInvalidated();
	}


	public int getCount() {
		// TODO Auto-generated method stub
		return incidentBeanList.size();
	}

	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	//TODO Mayank use view holderfrom dishADapter

	public View getView(int position, View view, ViewGroup arg2) {
		
		ViewHolder viewHolder;
		if(view == null)
		{
			LayoutInflater layoutInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = layoutInflater.inflate(R.layout.incidente_layout, null);
			
			viewHolder = new ViewHolder();
			viewHolder.fineAmountTextView=(TextView)view.findViewById(R.id.incident_layout_Fine_amount_TextView);
			viewHolder.statusTextView=(TextView)view.findViewById(R.id.incident_layout_status_TextView);
			viewHolder.vehicleNoText=(TextView)view.findViewById(R.id.incident_layout_vehicle_no_TextView);
			viewHolder.IncidentImageView=(ImageView)view.findViewById(R.id.incident_layout_ImageView);
			view.setTag(viewHolder);
        }
        else
        {
        	viewHolder = (ViewHolder)view.getTag();
        }
		
		viewHolder.fineAmountTextView.setText("FineAmount:" + incidentBeanList.get(position).getIncidenteFineAmount());
		if(incidentBeanList.get(position).getIncidentStatus()==0)
		{
		viewHolder.statusTextView.setText("Status:"+"Pending");
		}
		else
		{
			viewHolder.statusTextView.setText("Status:"+"Complete");
		}
		viewHolder.vehicleNoText.setText("VehicleNo:"+incidentBeanList.get(position).getNoPlateText());
		
		//
		Bitmap bitmap = BitmapFactory.decodeByteArray(incidentBeanList.get(position).getIncidenteVehicleNoImage(), 0, incidentBeanList.get(position).getIncidenteVehicleNoImage().length);
		viewHolder.IncidentImageView.setImageBitmap(bitmap);
		return view;
	}

	private static class ViewHolder{

		ImageView IncidentImageView;
		TextView fineAmountTextView, statusTextView, vehicleNoText;
	}

	}