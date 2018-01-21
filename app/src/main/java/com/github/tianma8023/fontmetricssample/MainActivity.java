package com.github.tianma8023.fontmetricssample;

import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.github.tianma8023.fontmetricssample.view.FontMetricsView;

public class MainActivity extends AppCompatActivity {

    private FontMetricsView mFontMetricsView;
    private EditText mTextEditText;
    private EditText mTextSizeEditText;
    private AppCompatButton mUpdateButton;
    private AppCompatCheckBox mTopCheckBox;
    private AppCompatCheckBox mAscentCheckBox;
    private AppCompatCheckBox mBaselineCheckBox;
    private AppCompatCheckBox mDescentCheckBox;
    private AppCompatCheckBox mBottomCheckBox;
    private RadioGroup mTextAlignRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initListener();
        initData();
    }

    private void initViews() {
        mFontMetricsView = findViewById(R.id.fontMetricsView);

        mTextEditText = findViewById(R.id.textEditText);
        mTextSizeEditText = findViewById(R.id.textSizeEditText);
        mUpdateButton = findViewById(R.id.updateButton);

        mTopCheckBox = findViewById(R.id.topCheckBox);
        mAscentCheckBox = findViewById(R.id.ascentCheckBox);
        mBaselineCheckBox = findViewById(R.id.baseLineCheckBox);
        mDescentCheckBox = findViewById(R.id.descentCheckBox);
        mBottomCheckBox = findViewById(R.id.bottomCheckBox);

        mTextAlignRadioGroup = findViewById(R.id.textAlignRadioGroup);
    }

    private void initListener() {
        CheckedChangeListenerImpl listener = new CheckedChangeListenerImpl();
        mTopCheckBox.setOnCheckedChangeListener(listener);
        mAscentCheckBox.setOnCheckedChangeListener(listener);
        mBaselineCheckBox.setOnCheckedChangeListener(listener);
        mDescentCheckBox.setOnCheckedChangeListener(listener);
        mBottomCheckBox.setOnCheckedChangeListener(listener);

        mUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFontMetricsView.setText(mTextEditText.getText().toString());
                try {
                    mFontMetricsView.setTextSize(Float.valueOf(mTextSizeEditText.getText().toString()));
                } catch (NumberFormatException e) {
                    mFontMetricsView.setTextSize(FontMetricsView.DEFAULT_TEXT_SIZE_PX);
                }
            }
        });

        mTextAlignRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Paint.Align align = Paint.Align.LEFT;
                switch (checkedId) {
                    case R.id.centerRadioButton:
                        align = Paint.Align.CENTER;
                        break;
                    case R.id.rightRadioButton:
                        align = Paint.Align.RIGHT;
                        break;
                }
                mFontMetricsView.setTextAlign(align);
            }
        });
    }

    private class CheckedChangeListenerImpl implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            switch (buttonView.getId()) {
                case R.id.topCheckBox:
                    mFontMetricsView.setTopLineVisible(isChecked);
                    break;
                case R.id.ascentCheckBox:
                    mFontMetricsView.setAscentLineVisible(isChecked);
                    break;
                case R.id.baseLineCheckBox:
                    mFontMetricsView.setBaseLineVisible(isChecked);
                    break;
                case R.id.descentCheckBox:
                    mFontMetricsView.setDescentLineVisible(isChecked);
                    break;
                case R.id.bottomCheckBox:
                    mFontMetricsView.setBottomLineVisible(isChecked);
                    break;
            }
        }
    }

    private void initData() {
        String text = "fghijkl pq QuQ quq";
        int textSize = 100;
        mTextEditText.setText(text);
        mTextSizeEditText.setText(textSize + "");
        mFontMetricsView.setTextSize(textSize);
        mFontMetricsView.setText(text);
        mTextEditText.setSelection(mTextEditText.getText().length());
    }

}
