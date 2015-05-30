package com.taydavid.android.intentchecker;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainListFragment extends ListFragment {
    static class AndroidIntentTesterListAdapter extends ArrayAdapter<String> {

        public AndroidIntentTesterListAdapter(Context context) {
            super(context, android.R.layout.simple_list_item_1,
                    AndroidIntentCheckerActivity.CATEGORY_INTENT_TABS);
        }

    }

    private FragmentManager mFragmentManager;

    private Fragment mCurrentFragment;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final ListView listView = getListView();

        final AndroidIntentTesterListAdapter adapter = new AndroidIntentTesterListAdapter(
                getActivity());
        setListAdapter(adapter);

        mFragmentManager = getFragmentManager();
        mCurrentFragment = null;
        hideAllFragments();
        final int index = (int) listView.getSelectedItemId();
        showAndroidIntentTester(index);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        if (position < l.getCount()) {
            showAndroidIntentTester(position);
        }
    }

    private void hideAllFragments() {
        final FragmentTransaction ft = mFragmentManager.beginTransaction();
        for (String tag : AndroidIntentCheckerActivity.CATEGORY_INTENT_TABS) {
            final Fragment fragment = mFragmentManager.findFragmentByTag(tag);
            if (fragment != null) {
                ft.hide(fragment);
            }
        }
        ft.commit();
    }

    private void showAndroidIntentTester(int index) {
        getListView().setItemChecked(index, true);

        final FragmentTransaction ft = mFragmentManager.beginTransaction();
        if (mCurrentFragment != null) {
            ft.hide(mCurrentFragment);
        }
        final Fragment fragment = mFragmentManager
                .findFragmentByTag(AndroidIntentCheckerActivity.CATEGORY_INTENT_TABS[index]);
        if (fragment != null) {
            ft.show(fragment);
        }
        ft.commit();
        mCurrentFragment = fragment;
    }

    public Fragment getCurrentFragment() {
        return mCurrentFragment;
    }

}
