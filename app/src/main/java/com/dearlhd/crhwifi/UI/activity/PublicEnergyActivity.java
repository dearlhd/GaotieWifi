package com.dearlhd.crhwifi.UI.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.dearlhd.crhwifi.R;
import com.dearlhd.crhwifi.SDK.bean.Energy;
import com.dearlhd.crhwifi.UI.adapter.EnergyAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by dearlhd on 2017/12/24.
 */
public class PublicEnergyActivity extends Activity{

    private List<Energy> mEnergyList;
    private ListView mLvPublicEnergy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pubic_energy);
        initView();
    }

    private void initView() {
        mLvPublicEnergy = (ListView) findViewById(R.id.lv_public_energy);

        generateEnergyList();
        EnergyAdapter adapter = new EnergyAdapter(PublicEnergyActivity.this, mEnergyList);
        mLvPublicEnergy.setAdapter(adapter);

    }

    private void generateEnergyList () {
        mEnergyList = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            Energy energy = new Energy();

            Random random = new Random();

            int name = random.nextInt(1000000);
            energy.setUsername("用户" + name);
            energy.setEnergy(random.nextInt(10));
            mEnergyList.add(energy);
        }
    }
}
