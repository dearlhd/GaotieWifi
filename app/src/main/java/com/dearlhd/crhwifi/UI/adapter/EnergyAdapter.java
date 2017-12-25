package com.dearlhd.crhwifi.UI.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dearlhd.crhwifi.R;
import com.dearlhd.crhwifi.SDK.bean.Energy;

import java.util.List;

/**
 * Created by dearlhd on 2017/12/25.
 */
public class EnergyAdapter extends BaseAdapter {
    private Context mContext;
    private List<Energy> mEnergyList;

    public EnergyAdapter (Context context, List<Energy> energyList) {
        mContext = context;
        mEnergyList = energyList;
    }

    @Override
    public int getCount() {
        return mEnergyList.size();
    }

    @Override
    public Object getItem(int i) {
        return mEnergyList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_public_energy, null);
            holder = new ViewHolder();
            holder.tvUsername = (TextView) view.findViewById(R.id.tv_energy_user);
            holder.tvEnergy = (TextView) view.findViewById(R.id.tv_gain_energy);
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }

        Energy energy = mEnergyList.get(i);
        holder.tvUsername.setText(energy.getUsername());
        holder.tvEnergy.setText("抢" + energy.getEnergy() + "g");
        holder.tvEnergy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.tvEnergy.setText("已抢");
            }
        });

        return view;
    }

    class ViewHolder {
        TextView tvUsername;
        TextView tvEnergy;
    }
}
