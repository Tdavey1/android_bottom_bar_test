package com.example.android.sliding3;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private BottomSheetBehavior mBottomSheetBehavior;
    private TextView mTextViewState;
    private FragmentManager fm;
    private Base base;
    private SignalSummary signal_summary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm = getSupportFragmentManager();
        base = (Base) getSupportFragmentManager().findFragmentById(R.id.base_fragment);
        signal_summary = (SignalSummary) getSupportFragmentManager().findFragmentById(R.id.signal_summary_fragment);


        View bottomSheet = findViewById(R.id.bottom_sheet);

        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        mTextViewState = findViewById(R.id.text_view_state);

        Button buttonExpand = findViewById(R.id.button_expand);
        Button buttonCollapse = findViewById(R.id.button_collapse);

        buttonExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });

        buttonCollapse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });

        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        Log.i("plop", mTextViewState.getText().toString());
                        mTextViewState.setText("collapsed");
                        fm.beginTransaction()
                                .show(base)
                                .hide(signal_summary)
                                .commit();
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        mTextViewState.setText("expanded");
                        fm.beginTransaction()
                                .hide(base)
                                .show(signal_summary)
                                .commit();
                        break;
                    default:
                        Log.i("plop", Integer.toString(newState));
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

    }
}
