package com.taydavid.android.intentchecker;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class LauncherFragment extends ListFragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                R.layout.launcher_tab,
                getResources().getStringArray(R.array.tc_launcher)));

        ListView listView = getListView();
        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                    long id) {
                switch (position) {
                    case 0:
                        startActivity(IntentCheckerUtils.buildIntent(Intent.ACTION_MAIN, null,
                                null,
                                Intent.CATEGORY_HOME));
                        break;
                    default:
                        break;
                }
            }
        });
    }

}
