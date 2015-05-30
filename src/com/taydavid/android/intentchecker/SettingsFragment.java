package com.taydavid.android.intentchecker;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class SettingsFragment extends ListFragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                R.layout.settings_tab,
                getResources().getStringArray(R.array.tc_settings)));

        ListView listView = getListView();
        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                    long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS));
                        break;
                    case 1:
                        startActivity(new Intent(Settings.ACTION_ADD_ACCOUNT));
                        break;
                    case 2:
                        startActivity(IntentCheckerUtils.buildIntent(
                                Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                "package:com.android.settings",
                                null, null));
                        break;
                    case 3:
                        startActivity(new Intent(Settings.ACTION_APPLICATION_SETTINGS));
                        break;
                    case 4:
                        startActivity(new Intent(Settings.ACTION_BLUETOOTH_SETTINGS));
                        break;
                    case 5:
                        startActivity(new Intent(Settings.ACTION_DATE_SETTINGS));
                        break;
                    case 6:
                        startActivity(new Intent(Settings.ACTION_DEVICE_INFO_SETTINGS));
                        break;
                    case 7:
                        startActivity(new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS));
                        break;
                    case 8:
                        startActivity(new Intent(Settings.ACTION_INPUT_METHOD_SUBTYPE_SETTINGS));
                        break;
                    case 9:
                        startActivity(new Intent(Settings.ACTION_INTERNAL_STORAGE_SETTINGS));
                        break;
                    case 10:
                        startActivity(new Intent(Settings.ACTION_LOCALE_SETTINGS));
                        break;
                    case 11:
                        startActivity(new Intent(Settings.ACTION_MANAGE_ALL_APPLICATIONS_SETTINGS));
                        break;
                    case 12:
                        startActivity(new Intent(Settings.ACTION_MEMORY_CARD_SETTINGS));
                        break;
                    case 13:
                        startActivity(new Intent(Settings.ACTION_PRIVACY_SETTINGS));
                        break;
                    case 14:
                        startActivity(new Intent(Settings.ACTION_SEARCH_SETTINGS));
                        break;
                    case 15:
                        startActivity(new Intent(Settings.ACTION_SECURITY_SETTINGS));
                        break;
                    case 16:
                        startActivity(new Intent(Settings.ACTION_SETTINGS));
                        break;
                    case 17:
                        startActivity(new Intent(Settings.ACTION_WIFI_IP_SETTINGS));
                        break;
                    case 18:
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                        break;
                    case 19:
                        startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
                        break;
                    case 20:
                        startActivity(new Intent(Settings.ACTION_SOUND_SETTINGS));
                        break;
                    case 21:
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
