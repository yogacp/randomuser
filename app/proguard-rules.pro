### greenDAO 3
-keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
    public static java.lang.String TABLENAME;
 }
 -keep class **$Properties

# If you do not use SQLCipher:
-dontwarn org.greenrobot.greendao.database.**
# If you do not use RxJava:
-dontwarn rx.**

# Platform calls Class.forName on types which do not exist on Android to determine platform.
-dontnote retrofit2.Platform
# Platform used when running on Java 8 VMs. Will not be used at runtime.
-dontwarn retrofit2.Platform$Java8
# Retain generic type information for use by reflection by converters and adapters.
-keepattributes Signature
# Retain declared checked exceptions for use by a Proxy instance.
-keepattributes Exceptions

# Data binding
-dontwarn android.databinding.**
-keep class android.databinding.** { *; }

# AVI Indicator Loading
-keep class com.wang.avi.** { *; }
-keep class com.wang.avi.indicators.** { *; }

#Exclude vo images class for firebase requirement
-keepclassmembers class app.randomuser.tabsquare.vo.db.** { *; }
-keepclassmembers class app.randomuser.tabsquare.dev.vo.db.** { *; }
-keepclassmembers class app.randomuser.tabsquare.vo.api.** { *; }
-keepclassmembers class app.randomuser.tabsquare.dev.vo.api.** { *; }

-keepattributes Signature
-keepattributes *Annotation*
-keepattributes EnclosingMethod
-keepattributes InnerClasses

-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-dontwarn okhttp3.**
-dontwarn okio.**

-dontwarn javax.annotation.Nullable
-dontwarn javax.annotation.ParametersAreNonnullByDefault
-dontwarn javax.annotation.GuardedBy

