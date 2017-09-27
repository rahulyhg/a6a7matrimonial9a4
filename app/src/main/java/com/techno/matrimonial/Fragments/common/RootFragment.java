
package com.techno.matrimonial.Fragments.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.techno.matrimonial.Listener.OnBackPressListener;
import com.techno.matrimonial.R;


public class RootFragment extends Fragment implements OnBackPressListener {

private Toolbar toolbar;

public TextView tbTitle;
private ImageView tbIvBack;
private ImageView tbIvCross;
private ImageView tbIvFilter;
private ImageView tbIvDeleted;

public boolean isBackClick = true;

public OnFragmentInteractionListener mListener;

@Override
public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getActivity() != null) {

        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        tbTitle = (TextView) toolbar.findViewById(R.id.tbTitle);
        tbIvBack = (ImageView) toolbar.findViewById(R.id.tbIvBack);

    }

}


public void replaceFragment(int containerId, Fragment fragment, String title) {

    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
    transaction.addToBackStack(title);
    transaction.replace(containerId, fragment, title).commit();
}

public void setTitle(String title) {

    if (tbTitle != null)
        tbTitle.setText(title);
}


public ImageView displayCross() {
    tbIvCross.setVisibility(View.VISIBLE);
    return tbIvCross;
}

public void hideCross() {
    tbIvCross.setVisibility(View.GONE);

}

public void displayBack() {
    tbIvBack.setVisibility(View.VISIBLE);
    if (isBackClick) {
        tbIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    if (mListener != null)
                        mListener.onBackClicked();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

public ImageView displayFilter() {
    tbIvFilter.setVisibility(View.VISIBLE);
    return tbIvFilter;
}

public void hideFilter() {
    tbIvFilter.setVisibility(View.GONE);

}

    public ImageView displayDelete() {
        tbIvDeleted.setVisibility(View.VISIBLE);
        return tbIvFilter;
    }

    public void hideDelete() {
        tbIvDeleted.setVisibility(View.GONE);

    }

public void hideBack() {
    tbIvBack.setVisibility(View.GONE);
}

/*public void clearBackStack() {

    Intent intent = new Intent(getActivity(), ServiceListActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intent);
    Global.activityBackTransition(getActivity());


}*/
public void clearBackStack() {

    for (int i = 0; i < 2; i++) {
        tbIvBack.performClick();
    }


}

@Override
public boolean onBackPressed() {
    return false;
}


public interface OnFragmentInteractionListener {
    void onBackClicked();
}


}
