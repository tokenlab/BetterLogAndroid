### Better Log Android

A simple yet useful lib to print better Logcat messages.

To install, simple add jitpack in your root build.gradle:
```
    repositories {
        [...]
        maven { url "https://jitpack.io" }
    }
```

And in your module build.gradle:
```
    implementation 'com.github.tokenlab:better-log-android:x.y.z'
```

To use, simple change to `BetterLog.*` instead of `Log.*`.

```kotlin
    BetterLog.d("TAG", "Log message", error)
```

This log will have the method name that printed the log and a link to that line on your Logcat window:

[Logcat image example](sample.png)

You can include `typealias Logs = BetterLog` in your project if you prefer shorter names
