#include <android/log.h>
#include <jni.h>

#define  LOG_TAG    "nativeprint"
#define  LOGI(...)  __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)
#define LOGD(...)  __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)
#define LOGE(...)  __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, __VA_ARGS__

JNIEXPORT jstring JNICALL
Java_com_jafir_jnilibrary_Hello_sayHello(JNIEnv *env, jobject instance) {
    //调用java的
    jclass hello = (*env)->GetObjectClass(env, instance);
//    jfieldID  property = (*env)->GetFieldID(env, hello,"property","I");
    jmethodID function = (*env)->GetMethodID(env, hello, "function", "(ILjava/util/Date;[I)I");
    (*env)->CallIntMethod(env, instance, function, 0L, NULL, NULL);
    return (*env)->NewStringUTF(env, "hello world!!!");
}

JNIEXPORT jstring JNICALL
Java_com_jafir_jnilibrary_Hello_staticSayHello(JNIEnv *env, jclass hello) {
    //调用java的static
    jmethodID function = (*env)->GetStaticMethodID(env, hello, "staticFunction",
                                                   "(ILjava/util/Date;[I)I");
    (*env)->CallStaticIntMethod(env, hello, function, 0L, NULL, NULL);
    return (*env)->NewStringUTF(env, "static hello world");
}

JNIEXPORT jobject JNICALL
Java_com_jafir_jnilibrary_Hello_getPerson(JNIEnv *env, jobject instance) {
    //创建有参数的对象 http://longpo.iteye.com/blog/2207788
    // 由于c++的字符字节数和java不一样，java是unicode(UTF-16) 是2个字节，而c++不是 所以这里需要用NewStringUTF
    jclass person = (*env)->FindClass(env, "com/jafir/jnilibrary/Person");
    jmethodID construct = (*env)->GetMethodID(env, person, "<init>", "(Ljava/lang/String;I)V");
    return (*env)->NewObject(env, person, construct, (*env)->NewStringUTF(env, "Jafir韩建飞"), 1);
}

JNIEXPORT void JNICALL
Java_com_jafir_jnilibrary_Hello_callArray(JNIEnv *env, jobject instance) {
    jfieldID arraysF = (*env)->GetFieldID(env, (*env)->GetObjectClass(env, instance), "arrays",
                                          "[I");
    jfieldID personsF = (*env)->GetFieldID(env, (*env)->GetObjectClass(env, instance), "persons",
                                           "[com/jafir/jnilibrary/Person");
    jintArray arrays = (*env)->GetObjectField(env, instance, arraysF);
    //获取数组对象指针
    jint *int_arr = (*env)->GetIntArrayElements(env, arrays, NULL);
    for (int i = 0; i < (*env)->GetArrayLength(env, arrays); ++i) {
        LOGD("%d", int_arr[i]);
    }
}