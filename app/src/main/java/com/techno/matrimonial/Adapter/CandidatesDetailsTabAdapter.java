package com.techno.matrimonial.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.techno.matrimonial.Fragments.ContactInfoFragmentTab;
import com.techno.matrimonial.Fragments.PersonalInfoFragmentTab;
import com.techno.matrimonial.Model.boys_girls.CandidateListMain;
import com.techno.matrimonial.R;

/**
 * Created by arbaz on 3/2/17.
 */

public class CandidatesDetailsTabAdapter extends FragmentStatePagerAdapter {

    int tabCount;
    Context context;
    CandidateListMain candidateListMain;
    public CandidatesDetailsTabAdapter(Context context, FragmentManager fm, int tabCount,CandidateListMain candidateListMain) {
        super(fm);
        this.tabCount = tabCount;
        this.context = context;
        this.candidateListMain=candidateListMain;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = " ";
        switch (position) {
            case 0:
                title = context.getString(R.string.tab_personal_info);
                break;
            case 1:
                title = context.getString(R.string.tab_contact_info);
                break;
            default:
                break;
        }

        return title;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
//                PersonalInfoFragmentTab personalInfoFragmentTab =PersonalInfoFragmentTab.newInstance(candidateListMain);
                return PersonalInfoFragmentTab.newInstance(candidateListMain);
            case 1:
//                ContactInfoFragmentTab contactInfoFragmentTab = new ContactInfoFragmentTab();
                return ContactInfoFragmentTab.newInstance(candidateListMain);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
