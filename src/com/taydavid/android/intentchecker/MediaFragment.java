package com.taydavid.android.intentchecker;

import android.app.ListFragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import java.io.File;

public class MediaFragment extends ListFragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                R.layout.media_tab,
                getResources().getStringArray(R.array.tc_media)));

        ListView listView = getListView();
        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                    long id) {
                FileDialog fileDialog = null;

                switch (position) {
                    case 0:
                        fileDialog = new FileDialog(getActivity(), new File("/mnt"));
                        fileDialog.setFileMatches(".*\\.(mp3|m4a|ogg|wma|aac)$");
                        fileDialog.addFileListener(new FileDialog.FileSelectedListener() {
                            public void fileSelected(File file) {
                                startActivity(IntentCheckerUtils
                                        .buildIntent(
                                                Intent.ACTION_VIEW,
                                                Uri.fromFile(file).toString(),
                                                "audio/*", null));
                            }
                        });
                        fileDialog.showDialog();

                        break;
                    case 1:
                        fileDialog = new FileDialog(getActivity(), new File("/mnt"));
                        fileDialog.setFileMatches(".*\\.(jpg|png|gif|bmp)$");
                        fileDialog.addFileListener(new FileDialog.FileSelectedListener() {
                            public void fileSelected(File file) {
                                startActivity(IntentCheckerUtils
                                        .buildIntent(
                                                Intent.ACTION_VIEW,
                                                Uri.fromFile(file).toString(),
                                                "image/*", null));
                            }
                        });
                        fileDialog.showDialog();

                        break;
                    case 2:
                        fileDialog = new FileDialog(getActivity(), new File("/mnt"));
                        fileDialog.setFileMatches(".*\\.(mpg|mpeg|ts|mp4|mkv|avi|3gpp|asf)$");
                        fileDialog.addFileListener(new FileDialog.FileSelectedListener() {
                            public void fileSelected(File file) {
                                startActivity(IntentCheckerUtils
                                        .buildIntent(
                                                Intent.ACTION_VIEW,
                                                Uri.fromFile(file).toString(),
                                                "video/*", null));
                            }
                        });
                        fileDialog.showDialog();

                        break;
                    case 3:
                        startActivity(IntentCheckerUtils
                                .buildIntent(
                                        Intent.ACTION_VIEW,
                                        "http://data.tomonaga.webfactional.com/static/ri_web/work/concert-testpattern-2.jpg",
                                        "image/*", null));
                        break;
                    case 4:
                        startActivity(IntentCheckerUtils
                                .buildIntent(
                                        Intent.ACTION_VIEW,
                                        "http://www.auby.no/files/video_tests/h264_720p_mp_3.1_3mbps_aac_shrinkage.mp4",
                                        "video/*", null));
                        break;
                    case 5:
                        startActivity(IntentCheckerUtils.buildIntent(Intent.ACTION_VIEW,
                                "http://www.noiseaddicts.com/samples/3826.mp3",
                                "audio/*", null));
                        break;
                    case 6:
                        startActivity(IntentCheckerUtils
                                .buildIntent(
                                        Intent.ACTION_VIEW,
                                        "https://dl.dropboxusercontent.com/s/49wfrd8psf487jg/Cityscape_2.JPG",
                                        "image/*", null));
                        break;
                    case 7:
                        startActivity(IntentCheckerUtils
                                .buildIntent(
                                        Intent.ACTION_VIEW,
                                        "https://dl.dropboxusercontent.com/s/jrbphdosshu2xgw/test_video_aac_h264.mp4",
                                        "video/*", null));
                        break;
                    case 8:
                        startActivity(IntentCheckerUtils
                                .buildIntent(
                                        Intent.ACTION_VIEW,
                                        "https://dl.dropboxusercontent.com/s/zvjpyz441qubwt9/test_audio_44k_128kbps.mp3",
                                        "audio/*", null));
                        break;
                    case 9:
                        startActivity(IntentCheckerUtils.buildIntent(Intent.ACTION_MAIN, null,
                                null, Intent.CATEGORY_APP_MUSIC));
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
