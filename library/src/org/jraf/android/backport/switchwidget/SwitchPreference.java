/*
 * This source is part of the
 *      _____  ___   ____
 *  __ / / _ \/ _ | / __/___  _______ _
 * / // / , _/ __ |/ _/_/ _ \/ __/ _ `/
 * \___/_/|_/_/ |_/_/ (_)___/_/  \_, /
 *                              /___/
 * repository.
 *
 * Copyright (C) 2015 Benoit 'BoD' Lubek (BoD@JRAF.org)
 * Copyright (C) 2012 Intrications (intrications.com)
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jraf.android.backport.switchwidget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.SwitchCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Checkable;
import android.widget.CompoundButton;

/**
 * A {@link android.preference.Preference} that provides a two-state toggleable option.
 * <p>
 * This preference will store a boolean into the SharedPreferences.
 *
 * @attr ref android.R.styleable#SwitchPreference_summaryOff
 * @attr ref android.R.styleable#SwitchPreference_summaryOn
 * @attr ref android.R.styleable#SwitchPreference_switchTextOff
 * @attr ref android.R.styleable#SwitchPreference_switchTextOn
 * @attr ref android.R.styleable#SwitchPreference_disableDependentsState
 */
public class SwitchPreference extends TwoStatePreference {
    private final Listener mListener = new Listener();

    // Switch text for on and off states
    private CharSequence mSwitchOn;
    private CharSequence mSwitchOff;

    private class Listener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (!callChangeListener(isChecked)) {
                // Listener didn't like it, change it back.
                // CompoundButton will make sure we don't recurse.
                buttonView.setChecked(!isChecked);
                return;
            }

            SwitchPreference.this.setChecked(isChecked);
        }
    }

    /**
     * Construct a new SwitchPreference with the given style options.
     *
     * @param context The Context that will style this preference
     * @param attrs Style attributes that differ from the default
     * @param defStyleAttr An attribute in the current theme that contains a
     *        reference to a style resource that supplies default values for
     *        the view. Can be 0 to not look for defaults.
     */
    public SwitchPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.asb_SwitchPreference, defStyleAttr, 0);
        setSummaryOn(a.getString(R.styleable.asb_SwitchPreference_asb_summaryOn));
        setSummaryOff(a.getString(R.styleable.asb_SwitchPreference_asb_summaryOff));
        setSwitchTextOn(a.getString(
                R.styleable.asb_SwitchPreference_asb_switchTextOn));
        setSwitchTextOff(a.getString(
                R.styleable.asb_SwitchPreference_asb_switchTextOff));
        setDisableDependentsState(a.getBoolean(
                R.styleable.asb_SwitchPreference_asb_disableDependentsState, false));
        a.recycle();
    }

    /**
     * Construct a new SwitchPreference with the given style options.
     *
     * @param context The Context that will style this preference
     * @param attrs Style attributes that differ from the default
     */
    public SwitchPreference(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.asb_switchPreferenceStyle);
    }

    /**
     * Construct a new SwitchPreference with default style options.
     *
     * @param context The Context that will style this preference
     */
    public SwitchPreference(Context context) {
        this(context, null);
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);

        View checkableView = view.findViewById(R.id.switchWidget);
        if (checkableView != null && checkableView instanceof Checkable) {
            if (checkableView instanceof SwitchCompat) {
                final SwitchCompat switchView = (SwitchCompat) checkableView;
                switchView.setOnCheckedChangeListener(null);
            }

            ((Checkable) checkableView).setChecked(mChecked);

            if (checkableView instanceof SwitchCompat) {
                final SwitchCompat switchView = (SwitchCompat) checkableView;
                switchView.setTextOn(mSwitchOn);
                switchView.setTextOff(mSwitchOff);
                switchView.setOnCheckedChangeListener(mListener);
            }
        }

        syncSummaryView(view);

        final View imageFrame = view.findViewById(R.id.icon_frame);
        if (imageFrame != null) {
            imageFrame.setVisibility(getIconCompat() != null ? View.VISIBLE : View.GONE);
        }
    }

    public Drawable getIconCompat() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) return getIconHoneycomb();
        return null;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public Drawable getIconHoneycomb() {
        return super.getIcon();
    }

    /**
     * Set the text displayed on the switch widget in the on state.
     * This should be a very short string; one word if possible.
     *
     * @param onText Text to display in the on state
     */
    public void setSwitchTextOn(CharSequence onText) {
        mSwitchOn = onText;
        notifyChanged();
    }

    /**
     * Set the text displayed on the switch widget in the off state.
     * This should be a very short string; one word if possible.
     *
     * @param offText Text to display in the off state
     */
    public void setSwitchTextOff(CharSequence offText) {
        mSwitchOff = offText;
        notifyChanged();
    }

    /**
     * Set the text displayed on the switch widget in the on state.
     * This should be a very short string; one word if possible.
     *
     * @param resId The text as a string resource ID
     */
    public void setSwitchTextOn(int resId) {
        setSwitchTextOn(getContext().getString(resId));
    }

    /**
     * Set the text displayed on the switch widget in the off state.
     * This should be a very short string; one word if possible.
     *
     * @param resId The text as a string resource ID
     */
    public void setSwitchTextOff(int resId) {
        setSwitchTextOff(getContext().getString(resId));
    }

    /**
     * @return The text that will be displayed on the switch widget in the on state
     */
    public CharSequence getSwitchTextOn() {
        return mSwitchOn;
    }

    /**
     * @return The text that will be displayed on the switch widget in the off state
     */
    public CharSequence getSwitchTextOff() {
        return mSwitchOff;
    }
}