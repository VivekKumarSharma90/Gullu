package Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vivek.android2.gullu.R;

/**
 * Created by android2 on 30/4/16.
 */
public class Fragment3 extends Fragment {
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View rootView = layoutInflater.inflate(R.layout.fragment3, viewGroup, false);
        return rootView;
    }
}
