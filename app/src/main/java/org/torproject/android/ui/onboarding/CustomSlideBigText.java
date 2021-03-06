package org.torproject.android.ui.onboarding;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.torproject.android.R;

public class CustomSlideBigText extends Fragment {

    private static final String ARG_LAYOUT_RES_ID = "layoutResId";
    private int layoutResId;
    private String mTitle;
    private String mButtonText;
    private String mSubTitle;
    private View.OnClickListener mButtonListener;
    private TextView bigTextSub, title;
    private Button button;

    private static final String BUNDLE_KEY_TITLE = "Title";
    private static final String BUNDLE_KEY_SUBTITLE = "Subtitle";
    private static final String BUNDLE_KEY_BUTTON_TEXT = "ButtonText";


    public static CustomSlideBigText newInstance(int layoutResId) {
        CustomSlideBigText sampleSlide = new CustomSlideBigText();

        Bundle args = new Bundle();
        args.putInt(ARG_LAYOUT_RES_ID, layoutResId);
        sampleSlide.setArguments(args);

        return sampleSlide;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setSubTitle(String subTitle) {
        mSubTitle = subTitle;
    }

    public void showButton(String buttonText, View.OnClickListener buttonListener) {
        mButtonText = buttonText;
        mButtonListener = buttonListener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null && getArguments().containsKey(ARG_LAYOUT_RES_ID)) {
            layoutResId = getArguments().getInt(ARG_LAYOUT_RES_ID);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(layoutResId, container, false);
        title = view.findViewById(R.id.custom_slide_big_text);
        title.setText(mTitle);
        bigTextSub = view.findViewById(R.id.custom_slide_big_text_sub);
        if (!TextUtils.isEmpty(mSubTitle)) {
            bigTextSub.setText(mSubTitle);
            bigTextSub.setVisibility(View.VISIBLE);
        }

        if (mButtonText != null) {
            button = view.findViewById(R.id.custom_slide_button);
            button.setVisibility(View.VISIBLE);
            button.setText(mButtonText);
            button.setOnClickListener(mButtonListener);
        }
        return view;
    }

    //Restoring the data
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            title.setText(savedInstanceState.getString(BUNDLE_KEY_TITLE));
            bigTextSub.setText(BUNDLE_KEY_SUBTITLE);
            if (mButtonText != null) {
                button.setText(savedInstanceState.getString(BUNDLE_KEY_BUTTON_TEXT));
            }

        }
    }

    //Saving the data
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(BUNDLE_KEY_TITLE, mTitle);
        outState.putString(BUNDLE_KEY_SUBTITLE, mSubTitle);
        if (mButtonText != null) {
            outState.putString(BUNDLE_KEY_BUTTON_TEXT, mButtonText);
        }
    }

}
