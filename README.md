Android Switch Preference Backport
===

A backport of the [`SwitchPreference`](http://developer.android.com/reference/android/preference/SwitchPreference.html) component that was introduced on Android 4 (ICS / level 14).

This port works on any Android 2.1+ (Eclair MR1 / level 7).

The current version of this library is `2.0.1`.

IMPORTANT: THIS LIBRARY IS NOW DEPRECATED
---

Since the AppCompat library now includes a **Switch** widget backport ([`SwitchCompat`](http://developer.android.com/reference/android/support/v7/widget/SwitchCompat.html)), and a **SwitchPreference** widget backport ([`SwitchPreferenceCompat`](https://developer.android.com/reference/android/support/v7/preference/SwitchPreferenceCompat.html)), this library is no longer useful.

I was glad to work on this library and to see that it has been used in many projects.


How to use
---

### Adding the library to your project

#### Option 1: Gradle

The aar artifact is available at the **jcenter** repository. Declare the repository and the
dependency in your `build.gradle` file:
```groovy
repositories {
    jcenter()
}
 (...)

dependencies {
    compile 'org.jraf:android-switch-backport:2.0.1'
}
```

#### Option 2: Android library project (ant / Eclipse)

This is an Android library project, you have to add it as a dependency to your project (please
see [this page](http://developer.android.com/guide/developing/projects/projects-eclipse.html#ReferencingLibraryProject)
to know how to do that.)

### Using the SwitchPreference

Once you have done that, have a theme for your application (or Activity), that declares the `asb_switchPreferenceStyle` item
to be the value `@style/asb_Preference.SwitchPreference`.

The simplest way to do that is to create a `themes.xml` file in your project's `res/values` folder with this contents:
```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>

    <style name="Theme.App" parent="Theme.AppCompat">
        <item name="asb_switchPreferenceStyle">@style/asb_Preference.SwitchPreference</item>
    </style>

</resources>
```
And use it in your Application or Activity by updating your `AndroidManifest.xml` file:
```xml
(...)
<application
    android:theme="@style/Theme.App"
(...)
```

or
```xml
(...)
<activity
    android:theme="@style/Theme.App"
(...)
```

Then in your preferences xml file:

```xml
<PreferenceScreen xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto" >

    <org.jraf.android.backport.switchwidget.SwitchPreference
        android:key="testKey"
        android:title="SwitchPreference Test"
        app:asb_summaryOff="@string/summary_off"
        app:asb_summaryOn="@string/summary_on" />

</PreferenceScreen>
```

### Sample app

A sample app is available in the [sample](sample) folder, and also on the Play Store:
[![Get it on Google Play](http://www.android.com/images/brand/get_it_on_play_logo_small.png)](https://play.google.com/store/apps/details?id=org.jraf.android.backport.switchwidget.sample)

Credits
---

The code was copied directly from the Android 5.0 (Lollipop / level 21) source code, then slightly tweaked by myself (BoD@JRAF.org) to make
it run on 2.1+ (Eclair MR1 / level 7).
The v1 SwitchPreference was added by Intrications (intrications.com / github.com/intrications), also by taking code
from Android and tweaking it a bit.  Other people also have contributed tweaks and fixes, please see this page for a detailed
list: https://github.com/BoD/android-switch-backport/graphs/contributors.

Contributing
---

Pull requests are welcome, as long as they are consistent to the original SwitchPreference of the
Android sdk.

Please do not contribute improvements that are not present in the original sdk classes!  I believe it would be
confusing for this backport to have a feature set different than the original sdk classes.
It would also lead to difficult situations if/when stopping using this backport and using the sdk classes instead
(which should happen when dropping support for old platforms).  Thank you very much.

Licence
---

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
