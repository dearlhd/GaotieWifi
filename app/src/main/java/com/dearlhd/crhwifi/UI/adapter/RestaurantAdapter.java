package com.dearlhd.crhwifi.UI.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dearlhd.crhwifi.R;
import com.dearlhd.crhwifi.SDK.bean.Restaurant;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by dearlhd on 2017/12/25.
 */
public class RestaurantAdapter extends BaseAdapter {
    private Context mContext;
    private List<Restaurant> mRestaurant;

    public RestaurantAdapter(Context context, List<Restaurant> restaurants) {
        mContext = context;
        mRestaurant = restaurants;
    }

    @Override
    public int getCount() {
        return mRestaurant.size();
    }

    @Override
    public Object getItem(int i) {
        return mRestaurant.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_recommend, null);
            holder = new ViewHolder();
            holder.ivThumbnail = (ImageView) view.findViewById(R.id.iv_recommend_thumbnail);
            holder.tvName = (TextView) view.findViewById(R.id.tv_recommend_name);
            holder.ivStar1 = (ImageView) view.findViewById(R.id.iv_star1);
            holder.ivStar2 = (ImageView) view.findViewById(R.id.iv_star2);
            holder.ivStar3 = (ImageView) view.findViewById(R.id.iv_star3);
            holder.ivStar4 = (ImageView) view.findViewById(R.id.iv_star4);
            holder.ivStar5 = (ImageView) view.findViewById(R.id.iv_star5);
            holder.tvCost = (TextView) view.findViewById(R.id.tv_cost_avg);
            holder.tvArea = (TextView) view.findViewById(R.id.tv_recommend_area);
            holder.tvAddress = (TextView) view.findViewById(R.id.tv_recommend_address);
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }

        Restaurant restaurant = mRestaurant.get(i);
        BitmapManager.INSTANCE.loadBitmap(restaurant.getPhotos().get(0).url, holder.ivThumbnail, 300, 300);
        holder.tvName.setText(restaurant.getName());
        int rating = (int) restaurant.getInfo().rating;
        switch (rating) {
            case 5:
                holder.ivStar5.setImageResource(R.drawable.ic_star_full);
            case 4:
                holder.ivStar4.setImageResource(R.drawable.ic_star_full);
            case 3:
                holder.ivStar3.setImageResource(R.drawable.ic_star_full);
            case 2:
                holder.ivStar2.setImageResource(R.drawable.ic_star_full);
            case 1:
                holder.ivStar1.setImageResource(R.drawable.ic_star_full);
            default:
                break;
        }
        holder.tvCost.setText("¥" + restaurant.getInfo().cost + "/人");
        holder.tvArea.setText(restaurant.getArea());
        holder.tvAddress.setText(restaurant.getAddress());

        return view;
    }

    //加载图片
    public Bitmap getURLImage(String url) {
        Bitmap bmp = null;
        try {
            URL myurl = new URL(url);
            // 获得连接
            HttpURLConnection conn = (HttpURLConnection) myurl.openConnection();
            conn.setConnectTimeout(6000);//设置超时
            conn.setDoInput(true);
            conn.setUseCaches(false);//不缓存
            conn.connect();
            InputStream is = conn.getInputStream();//获得图片的数据流
            bmp = BitmapFactory.decodeStream(is);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bmp;
    }

    class ViewHolder {
        ImageView ivThumbnail;
        TextView tvName;
        ImageView ivStar1;
        ImageView ivStar2;
        ImageView ivStar3;
        ImageView ivStar4;
        ImageView ivStar5;
        TextView tvCost;
        TextView tvArea;
        TextView tvAddress;
    }
}
