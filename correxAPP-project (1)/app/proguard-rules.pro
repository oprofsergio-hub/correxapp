# Proguard rules for correxAPP

# Keep annotations used by libraries
-keepattributes *Annotation*

# Hilt
-keep class dagger.hilt.internal.aggregatedroot.codegen.*
-keep class com.correxapp.Hilt_CorrexApplication
-keep class hilt_aggregated_deps.*
-dontwarn dagger.hilt.processor.internal.aggregateddeps.AggregatedDeps

# Room
-keep class androidx.room.** { *; }
-keep class com.correxapp.data.database.entity.** { *; }

# Kotlinx Serialization
-keepclassmembers class com.correxapp.data.database.entity.** {
    <fields>;
    *** get*();
    void set*(***);
}
-keep class kotlinx.serialization.internal.** { *; }

# OpenCV
-keepclasseswithmembernames class * {
    native <methods>;
}
-keep class org.opencv.** { *; }

# ML Kit
-keep class com.google.android.gms.internal.mlkit_vision_text_common.** {*;}
-keep class com.google.mlkit.vision.text.** {*;}

# Coroutines
-keepclassmembers class kotlinx.coroutines.internal.MainDispatcherFactory {
    kotlin.coroutines.MainCoroutineDispatcher createDispatcher(java.util.List);
}
