package com.techno.matrimonial.Listener;

/**
 * Created by arbaz on 19/7/16.
 */
public interface OnApiCallListener {
    public void onSuccess(int responseCode, String responseString, String url);

    public void onFailure(String errorMessage);

}
