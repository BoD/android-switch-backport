Android Switch Widget Backport
==============================

A backport of the Switch widget (http://developer.android.com/reference/android/widget/Switch.html)
that was introduced on Android 4.

This port works on Android 2.2+.

How to use
----------

This is an Android library project, you have to add it as a dependency to your project (please
see http://developer.android.com/guide/developing/projects/projects-eclipse.html#ReferencingLibraryProject to
know how to do that.)

Once you have done that, have a theme for your application (or Activity), that declares the `switchStyle` item
to be one of the two possible themes: either `Widget.Holo.CompoundButton.Switch` (dark) or `Widget.Holo.Light.CompoundButton.Switch`
(light).

The simplest way to do that is to create a `themes.xml` file in your project's `res/values` folder with this contents:

        <?xml version="1.0" encoding="utf-8"?>
        <resources>

            <style name="Theme" parent="@android:Theme">
                <item name="switchStyle">@style/Widget.Holo.CompoundButton.Switch</item>
            </style>

        </resources>

Credits
-------

The code was copied directly from the Android 4.0.3 (API 15) source code, then slightly tweaked by myself (BoD@JRAF.org) to make
it run on 2.2+.  The few modifications I made are documented on the code (look for 'XXX' comments).

Licence
-------

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
