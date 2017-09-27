package com.techno.matrimonial.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.facebook.drawee.view.SimpleDraweeView;
import com.techno.matrimonial.Fragments.common.RootFragment;
import com.techno.matrimonial.R;

/**
 * Created by arbaz on 2/7/16.
 */
public class Send_BioDataFragment extends RootFragment {
    SimpleDraweeView circleImage;
    WebView pdf_wv;


    public Send_BioDataFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_send_biodata, container, false);


        pdf_wv = (WebView) rootview.findViewById(R.id.pdf_wv);
        pdf_wv.getSettings().setJavaScriptEnabled(true);
        pdf_wv.getSettings().setPluginState(WebSettings.PluginState.ON);

        /*you need this to prevent the webview from launching another browser when a url redirection occurs*/
        pdf_wv.setWebViewClient(new Callback());
        String pdfURL = "http://dl.dropboxusercontent.com/u/37098169/Course%20Brochures/AND101.pdf";
        pdf_wv.loadUrl("http://docs.google.com/gview?embedded=true&url=" + pdfURL);

        return rootview;
    }

    private class Callback extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(
                WebView view, String url) {
            return (false);
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        setTitle(getString(R.string.nav_biodata));
    }
}
