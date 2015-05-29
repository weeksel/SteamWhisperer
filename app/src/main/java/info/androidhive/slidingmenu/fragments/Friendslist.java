package info.androidhive.slidingmenu.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.websitepipeline.edwardweeks.steamwhisperer.R;

/**
 * Created by kbrown on 5/29/2015.
 */
public class Friendslist extends Fragment {

    public Friendslist(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_friends_list, container, false);

        return rootView;
    }
}

