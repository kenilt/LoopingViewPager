# LoopingViewPager
[ ![Download](https://api.bintray.com/packages/kenilt/LoopingViewPager/com.kenilt.loopingviewpager/images/download.svg) ](https://bintray.com/kenilt/LoopingViewPager/com.kenilt.loopingviewpager/_latestVersion)

A ViewPager that supports infinite looping effect, smart auto-scroll, compatible with any indicators and easy to use. It especially uses it as banners of application with a simple item page.

Looping Viewpager can:
- Plug and play, easy to use
- Infinite Looping items
- Auto scroll items, allow config, auto resume/pause when activity/fragment resume/pause
- Won't scroll or loop if it has only 1 item
- Compatible with many indicators

## Demo app effect
<table>
  <tr>
    <td>Simple pager</td>
    <td>Pager with indicators</td>
  </tr>
  <tr>
    <td><img src="/screenshot/simple-screenshot.gif" width="300"></td>
    <td><img src="/screenshot/indicators-screenshot.gif" width="300"></td>
  </tr>
 </table>


# Sample app
You can download the apk for the sample app of this library [at this link](./app/apk).  
[<img src="http://www.installads.net/buton/download-apk.png" height="70" title="Download APK" />](./app/apk)

The code of the sample app is available [at this link](./app/).

Having the sample apps installed is a good way to be notified of new releases. Although watching this repository will allow GitHub to email you whenever a new release is published.


# Download
The Gradle dependency is available via [jCenter](https://bintray.com/kenilt/LoopingViewPager). jCenter is the default Maven repository used by Android Studio.

The minimum API level supported by this library is API 16.

##### AndroidX
The latest version here [ ![Download](https://api.bintray.com/packages/kenilt/LoopingViewPager/com.kenilt.loopingviewpager/images/download.svg) ](https://bintray.com/kenilt/LoopingViewPager/com.kenilt.loopingviewpager/_latestVersion)
```
dependencies {
    implementation 'com.kenilt.loopingviewpager:loopingviewpager:1.0.0'
}
```


# Usage

##### Simple usage
Just replace your ViewPager with LoopingViewPager and use it as normal

```kotlin
// Just set adapter and indicator as a normal view pager
vpPager.adapter = adapter
indicator.setViewPager(vpPager)
```


##### XML Properties

```xml
<com.kenilt.loopingviewpager.widget.LoopingViewPager
	android:id="@+id/vpPager"
	android:layout_width="match_parent"
	android:layout_height="match_parent"
	android:overScrollMode="never"/>
```

##### Advanced usage

```kotlin
// set adapter for view pager
vpPager.adapter = adapter

// when your adapter is FragmentPagerAdapter or FragmentStatePagerAdapter,
// use this function for better performance
vpPager.setAdapter(adapter, supportFragmentManager)

// set view pager for indicator
indicator.setViewPager(vpPager)

// active auto scroll
val autoScroller = AutoScroller(vpPager, lifecycle, 3000)
// pause auto scroll
autoScroller.isAutoScroll = false
```

If your adapter is instance of FragmentPagerAdapter or FragmentStatePagerAdapter, you should to use `vpPager.setAdapter(adapter, supportFragmentManager)` to get better performance, if not, LoopingViewPager will use reflection to get fragmentManager from your adapter to do its job.  
By default, LoopingViewPager will force use behavior of FragmentPagerAdapter and FragmentStatePager adapter to `BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT`, because `BEHAVIOR_SET_USER_VISIBLE_HINT` is deprecated now.

AutoScroller is a separate class, so if you want your pager/banners auto-scroll, just create an `AutoScroller(vpPager)` and it will automatically scroll your pager/banners every `scrollInterval` (default is 5000 milliseconds).  
If you want `AutoScroller` auto resume when Activity/Fragment resumed, and auto pause when Activity/Fragment paused, pass `lifecyle` as a parameter when created it `AutoScroller(vpPager, lifecycle)`.  
To change interval time, just call `autoScroller.scrollInterval = value`.  
To resume/pause auto scroller, just call `autoScroller.isAutoScroll = true/false`

# Credits

 * Thanks [Pierfrancesco Soffritti][1] - Author of [android-youtube-player][2] - for awesome sample app library and lifecyle idea.
 * Thanks [SanyuZhang][3] - Author of [CircularViewPager][4] - for awesome internal adapter and listener idea.
 * Thanks [Sira][5] - Author of [LoopingViewPager][6] - for awesome library and auto scroll idea.
 * Thanks [TobiasBuchholz][7] - Author of [CircularViewPager][8] - for looping idea.




 [1]: https://github.com/PierfrancescoSoffritti
 [2]: https://github.com/PierfrancescoSoffritti/android-youtube-player
 [3]: https://github.com/sanyuzhang
 [4]: https://github.com/sanyuzhang/CircularViewPager
 [5]: https://github.com/siralam
 [6]: https://github.com/siralam/LoopingViewPager
 [7]: https://github.com/TobiasBuchholz
 [8]: https://github.com/TobiasBuchholz/CircularViewPager

# License

```
The MIT License

Copyright (c) 2020 Kenilt Nguyen

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
```
