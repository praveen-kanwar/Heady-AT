# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# To Generate A File Of All Applied Optimization
# -printconfiguration build/outputs/r8/optimization.txt

#### AppCompat Start ####
# Ensure that reflectively-loaded inflater is not obfuscated. This can be
# removed when we stop supporting AAPT1 builds.
#-keepnames class androidx.appcompat.app.AppCompatViewInflater
# aapt is not able to read app::actionViewClass and app:actionProviderClass to produce proguard
# keep rules. Add a commonly used SearchView to the keep list until b/109831488 is resolved.
#-keep class androidx.appcompat.widget.SearchView { <init>(...); }
#### AppCompat End ####

#### Firebase Start ####
-keepattributes Signature
#### Firebase End ####

#### OkHttp Start ####
# OkHttp3
-dontwarn org.conscrypt.**
# JSR 305 annotations are for embedding nullability information.
-dontwarn javax.annotation.**
# A resource is loaded with a relative path so the package of this class must be preserved.
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase
# Animal Sniffer compileOnly dependency to ensure APIs are compatible with older versions of Java.
-dontwarn org.codehaus.mojo.animal_sniffer.*
# OkHttp platform used only on JVM and when Conscrypt dependency is available.
-dontwarn okhttp3.internal.platform.ConscryptPlatform
#### OkHttp End ####

### To Tell Not To Obfuscate (To Disable Shortening The Name Of Classes & Members)
#-dontobfuscate
### To Tell Not To Shrink (To Disable Removing Unused Code)
#-dontshrink
### To Tell Not To Optimize (To Disable More Aggressive Strategies To Further Reduce The Size Of Your Application.)
# -dontoptimize

### Presentation Layer Data Class ###
-keep class * implements com.heady.test.common.models.IModel { *; }

### Domain Layer Data Class ###
-keep class * implements com.heady.test.domain.common.beans.IBean { *; }

#### Realm ####
-keep @interface io.realm.annotations.RealmModule { *; }
-keep class io.realm.annotations.RealmModule { *; }
-keep @io.realm.annotations.RealmModule class *
-keep class io.realm.internal.Keep
-keep @io.realm.internal.Keep class *
-dontwarn javax.**
-keepnames public class * extends io.realm.RealmObject
-keep public class * extends io.realm.RealmObject { *; }
-keep class io.realm.** { *; }
#### Realm End ####