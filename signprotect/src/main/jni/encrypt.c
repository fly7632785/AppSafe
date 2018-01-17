#include <android/log.h>
#include <jni.h>
#include <stdlib.h>
#include <string.h>

#define  LOG_TAG    "nativeprint"
#define  LOGI(...)  __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)
#define LOGD(...)  __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)
#define LOGE(...)  __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, __VA_ARGS__


int equal_sign(JNIEnv *pInterface);

const char *sign = "eda117e3da33ce601d3643397547ef8b";

JNIEXPORT int equal_sign(JNIEnv *env) {
    jclass utilClazz = (*env)->FindClass(env, "com/jafir/signprotect/Utils");
    if (utilClazz == NULL) {
        LOGD("not found class ");
        return 1;
    }

    LOGD("class name:%p",utilClazz);
    jmethodID method = (*env)->GetStaticMethodID(env,utilClazz,"getSignMd5","()Ljava/lang/String;");
    if(method == NULL){
        LOGD("no method");
        return 1;
    }

    jstring  obj = (*env)->CallStaticObjectMethod(env,utilClazz,method);
    if(obj == NULL){
        LOGD("no obj");
        return 1;
    }

    const  char * signMd5 =(*env)->GetStringUTFChars(env,obj,0);
    int result = strcmp(signMd5,sign);

    if(result == 0 ){
        return 1;
    }
    (*env)->ReleaseStringUTFChars(env,obj,signMd5);
    return 0;
}

jint JNICALL JNI_OnLoad(JavaVM *vm, void *reserved) {
    LOGD("onload");
    JNIEnv *env = NULL;
    if ((*vm)->GetEnv(vm, (
            void **) &env, JNI_VERSION_1_4) != JNI_OK) {
        return -1;
    }
    int check_sign = equal_sign(env);
    if (check_sign == 0) {
        abort();
    }
    LOGD("sign is correct");
    return JNI_VERSION_1_4;
}

JNIEXPORT void JNI_OnUnload(JavaVM *vm, void *reserved) {
    LOGD("onunload");
}