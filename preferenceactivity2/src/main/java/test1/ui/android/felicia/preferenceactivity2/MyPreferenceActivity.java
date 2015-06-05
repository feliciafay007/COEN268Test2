package test1.ui.android.felicia.preferenceactivity2;

import android.preference.PreferenceActivity;


import java.util.List;


public class MyPreferenceActivity extends PreferenceActivity
{
    @Override
    public void onBuildHeaders(List<Header> target)
    {
        loadHeadersFromResource(R.xml.headers_preference, target);
    }

    @Override
    protected boolean isValidFragment(String fragmentName)
    {

        return MyPreferenceFragment.class.getName().equals(fragmentName);
    }
}