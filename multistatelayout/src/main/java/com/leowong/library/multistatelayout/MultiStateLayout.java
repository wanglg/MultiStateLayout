package com.leowong.library.multistatelayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;

/**
 * User: wanglg
 * Date: 2016-01-04
 * Time: 14:21
 * FIXME
 */
public class MultiStateLayout extends FrameLayout {

    protected ViewStub mProgress;
    protected ViewStub mEmpty;
    protected ViewStub mError;
    protected ViewStub mContent;

    protected int mEmptyId;
    protected int mProgressId;
    protected int mContentId;
    protected int mErrorId;

    protected int mMainLayoutId;


    private int mState;


    public final static class State {
        public static final int STATE_CONTENT = 0;
        public static final int STATE_PROGRESS = 3;
        public static final int STATE_ERROR = 1;
        public static final int STATE_EMPTY = 2;
    }

    public MultiStateLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
        initView();
    }

    public MultiStateLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs);
        initView();
    }

    protected void initAttrs(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.MultiStateLayout);
        try {
            mMainLayoutId = a.getResourceId(R.styleable.MultiStateLayout_mainLayoutId, R.layout.widget_multi_state_main_layout);
            mContentId = a.getResourceId(R.styleable.MultiStateLayout_multi_state_content, 0);
            mEmptyId = a.getResourceId(R.styleable.MultiStateLayout_multi_state_empty, R.layout.widget_multi_state_empty_layout);
            mProgressId = a.getResourceId(R.styleable.MultiStateLayout_multi_state_progress, R.layout.widget_multi_state_progress_layout);
            mErrorId = a.getResourceId(R.styleable.MultiStateLayout_multi_state_error, R.layout.widget_multi_state_error_layout);
            mState = a.getInt(R.styleable.MultiStateLayout_multi_state_default, State.STATE_CONTENT);
        } finally {
            a.recycle();
        }
    }

    private void initView() {
        if (isInEditMode()) {
            return;
        }
        ViewGroup v = (ViewGroup) LayoutInflater.from(getContext()).inflate(mMainLayoutId, this);
        mContent = (ViewStub) v.findViewById(R.id.multi_state_content);
        if (mContentId == 0 || mContent == null) {
            throw new IllegalStateException("MultiStateLayout must have a content layout");
        }
        mContent.setLayoutResource(mContentId);
        mContent.inflate();
        mProgress = (ViewStub) v.findViewById(R.id.multi_state_progress);
        if (mProgressId != 0) {
            mProgress.setLayoutResource(mProgressId);
            mProgress.inflate();
        }
        mEmpty = (ViewStub) v.findViewById(R.id.multi_state_empty);
        if (mEmptyId != 0) {
            mEmpty.setLayoutResource(mEmptyId);
            mEmpty.inflate();
        }
        mError = (ViewStub) v.findViewById(R.id.multi_state_error);
        if (mErrorId != 0) {
            mError.setLayoutResource(mErrorId);
            mError.inflate();
        }
        switchState(mState);
    }

    public int getState() {
        return mState;
    }

    public void setState(int mState) {
        this.mState = mState;
    }

    public void switchState(int state) {
        switch (state) {
            case State.STATE_CONTENT:
                mProgress.setVisibility(GONE);
                mEmpty.setVisibility(GONE);
                mError.setVisibility(GONE);
                mContent.setVisibility(VISIBLE);
                break;
            case State.STATE_EMPTY:
                mProgress.setVisibility(GONE);
                mEmpty.setVisibility(VISIBLE);
                mError.setVisibility(GONE);
                mContent.setVisibility(GONE);
                break;
            case State.STATE_ERROR:
                mProgress.setVisibility(GONE);
                mEmpty.setVisibility(GONE);
                mError.setVisibility(VISIBLE);
                mContent.setVisibility(GONE);
                break;
            case State.STATE_PROGRESS:
                mProgress.setVisibility(VISIBLE);
                mEmpty.setVisibility(GONE);
                mError.setVisibility(GONE);
                mContent.setVisibility(GONE);
                break;
            default:
                Log.e("MultiStateLayout", "The state is not defined");
                break;
        }
    }
}
