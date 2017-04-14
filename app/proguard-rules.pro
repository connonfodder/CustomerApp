# Add project specific ProGuard rules here.
# By pic_default, the flags in this file are appended to flags specified
# in E:\androidTools\android-sdk-windows/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# http://blog.csdn.net/banketree/article/details/41928175
# http://blog.csdn.net/ljd2038/article/details/51308768

# 忽略警告，避免打包时某些警告出现
-ignorewarnings
# 指定代码的压缩级别
-optimizationpasses 5
# 是否使用大小写混合
-dontusemixedcaseclassnames
# 是否混淆第三方jar
-dontskipnonpubliclibraryclasses
# 混淆时是否做预校验
-dontpreverify
 # 混淆时是否记录日志
-verbose
# 混淆时所采用的算法
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
#保留Annotation不混淆
-keepattributes *Annotation*,InnerClasses
#避免混淆泛型
-keepattributes Signature
#抛出异常时保留代码行号
-keepattributes SourceFile,LineNumberTable

#保留我们使用的四大组件，自定义的Application等等这些类不被混淆
#因为这些子类都有可能被外部调用
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Appliction
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
-keep public class * extends android.widget
-keep public class com.android.vending.licensing.ILicensingService

#保留support下的所有类及其内部类
-keep class android.support.** {*;}
-keepattributes *Annotation*

#保留R下面的资源
-keep class **.R$* {*;}

#保留本地native方法不被混淆
-keepclasseswithmembernames class * {
    native <methods>;
}
#保持类成员
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}
# 保持 Parcelable 不被混淆
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
#保留枚举类不被混淆
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
#保持自定义控件类不被混淆
-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
#保留Serializable序列化的类不被混淆
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
#对于带有回调函数的onXXEvent的，不能被混淆
-keepclassmembers class * {
    void *(**On*Event);
}
#######################我们自己的app##############################

# Keep all the ACRA classes
-keep class org.acra.** { *; }
#skip external json
# Add the gson class
-keep class com.google.gson.** { *; }
# Add any classes the interact with gson
-keep public class com.aadhk.customer.bean.** {
    public void set*(***);
    public *** get*();
    public *** is*();
}
#WebViwe
-keepclassmembers class fqcn.of.javascript.interface.for.webview {
   public *;
}
-keepclassmembers class * extends android.webkit.webViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclassmembers class * extends android.webkit.webViewClient {
    public void *(android.webkit.webView, jav.lang.String);
}
#rxjava
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
 long producerIndex;
 long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
 rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
 rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

# 保持自己定义的类不被混淆
-keep class * extends com.aadhk.customer.widget

#sublimepickerlibrary
-dontwarn com.appeaser.sublimepickerlibrary.**
-keep class com.appeaser.sublimepickerlibrary.**  { *; }

#rx
-dontwarn rx.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    long producerNode;
    long consumerNode;
}
-keep class rx.internal.util.unsafe { *;}
-keep class rx.schedulers.Schedulers {
    public static <methods>;
}
-keep class rx.schedulers.ImmediateScheduler {
    public <methods>;
}
-keep class rx.schedulers.TestScheduler {
    public <methods>;
}
-keep class rx.schedulers.Schedulers {
    public static ** test();
}

#ButterKnife
-dontwarn butterknife.internal.**
-keep class **$$ViewInjector { *; }
-keepnames class * { @butterknife.InjectView *;}
-keep @interface butterknife.*

-keepclasseswithmembers class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembers class * {
    @butterknife.* <methods>;
}

-keepclasseswithmembers class * {
    @butterknife.On* <methods>;
}

-keep class **$$ViewInjector {
    public static void inject(...);
    public static void reset(...);
}

-keep class **$$ViewBinder {
    public static void bind(...);
    public static void unbind(...);
}
#glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
    **[] $VALUES;
    public *;
}

#retroift
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Exceptions

-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}

-assumenosideeffects class android.util.Log {
    public static *** v(...);
    public static *** i(...);
    public static *** d(...);
    public static *** w(...);
    public static *** e(...);
}
#不但要避免泛型, 抽象类, 接口不被混淆, 还要保持其子类不被混淆
-dontwarn com.aadhk.library.**
-keep class * implements com.aadhk.library.ui.BaseModel {
     <methods>;
}
-keep class * implements com.aadhk.library.ui.BaseView {
     <methods>;
}
-keep class * extends com.aadhk.library.ui.BasePresenter {
     <methods>;
}
-keep class * extends com.aadhk.library.ui.BaseFragment {
     <methods>;
}
-keep class * extends com.aadhk.library.ui.BaseActivity {
     <methods>;
}
-keep class * extends com.aadhk.library.ui.AppActivity {
     <methods>;
}
#自己的数据接口
-keep public interface com.aadhk.customer.data.*

#第三方
-dontwarn com.gc.materialdesign.**
-keep class com.gc.materialdesign.** { *; }

-dontwarn com.rengwuxian.materialedittext.**
-keep class com.rengwuxian.materialedittext.** { *; }

-dontwarn com.github.bumptech.glide.**
-keep class com.github.bumptech.glide.** { *; }

-dontwarn com.github.franmontiel.**
-keep class com.github.franmontiel.** { *; }

-dontwarn com.squareup.okhttp3.**
-keep class com.squareup.okhttp3.** { *; }

-dontwarn pub.devrel.**
-keep class pub.devrel.** { *; }

-dontwarn com.flipboard.**
-keep class com.flipboard.** { *; }

-dontwarn com.flyco.tablayout.**
-keep class com.flyco.tablayout.** { *; }

-dontwarn com.jude.easyrecyclerview.**
-keep class com.jude.easyrecyclerview.** { *; }

-dontwarn me.dm7.barcodescanner.**
-keep class me.dm7.barcodescanner.** { *; }

#网络架构
-dontwarn retrofit.**
-keep class retrofit.** { *; }
-dontwarn sun.misc.Unsafe
-dontwarn com.octo.android.robospice.retrofit.RetrofitJackson**
-dontwarn retrofit.appengine.UrlFetchClient
-keepattributes Signature
-keepattributes Exceptions
-keepclasseswithmembers class * {
    @retrofit.http.* <methods>;
}
-keep class com.google.gson.** { *; }
-keep class com.google.inject.** { *; }
-keep class org.apache.http.** { *; }
-keep class org.apache.james.mime4j.** { *; }
-keep class javax.inject.** { *; }
-keep class retrofit.** { *; }
-dontwarn org.apache.http.**
-dontwarn android.net.http.AndroidHttpClient
-dontwarn retrofit.**

-dontwarn sun.misc.**

-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
   long producerIndex;
   long consumerIndex;
}

-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
   long producerNode;
   long consumerNode;
}

-keep class com.aadhk.customer.bean.** { *; }
-keep class com.aadhk.library.rx.** { *; }
