package com.example.jesper.pageturner;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;

import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;


public class MainActivity2 extends AppCompatActivity {

    private static final int RightToLeft = 1;
    private static final int LeftToRight = 2;
    private static final int DURATION = 5000;

    private ValueAnimator mCurrentAnimator;
    private final Matrix mMatrix = new Matrix();
    private ImageView mImageView;
    private float mScaleFactor;
    private int mDirection = RightToLeft;
    private RectF mDisplayRect = new RectF();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = (ImageView) findViewById(R.id.longsong);
        mScaleFactor = 1000;
        mImageView.post(new Runnable() {
            public void run(){
                mScaleFactor = (float) mImageView.getHeight()/(float) mImageView.getDrawable().getIntrinsicHeight();
                mMatrix.postScale(mScaleFactor, mScaleFactor);
                mImageView.setImageMatrix(mMatrix);
                animate();
            }
        });
    }

    private void animate(){
        updateDisplayRect();
        if(mDirection == RightToLeft) {
            animate(mDisplayRect.left, mDisplayRect.left-(mDisplayRect.right-mImageView.getWidth()));
        } else{
            animate(mDisplayRect.left, 0.0f);
        }
    }

    private void animate(float from, float to) {
        mCurrentAnimator = ValueAnimator.ofFloat(from, to);
        mCurrentAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();

                mMatrix.reset();
                mMatrix.postScale(mScaleFactor, mScaleFactor);
                mMatrix.postTranslate(value, 0);

                mImageView.setImageMatrix(mMatrix);
            }
        });

        mCurrentAnimator.setDuration(DURATION);
        mCurrentAnimator.addListener(new AnimatorListenerAdapter() {

            public void onAnimationEnd(Animator animation) {
                if (mDirection == RightToLeft) {
                    mDirection = LeftToRight;
                } else {
                    mDirection = RightToLeft;
                }
                animate();
            }
        });
        mCurrentAnimator.start();

    }

    private void updateDisplayRect() {
        mDisplayRect.set(0, 0, mImageView.getDrawable().getIntrinsicWidth(), mImageView.getDrawable().getIntrinsicHeight());
        mMatrix.mapRect(mDisplayRect);
    }


}
