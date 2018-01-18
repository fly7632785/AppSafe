#include <android/log.h>
#include <jni.h>
#include <stdlib.h>
#include <string.h>
#include <pthread.h>
#include <sys/ptrace.h>
#include <unistd.h>

#define  LOG_TAG    "nativeprint"
#define  LOGI(...)  __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)
#define LOGD(...)  __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)
#define LOGE(...)  __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, __VA_ARGS__


int equal_sign(JNIEnv *pInterface);

void create_thread_check_trace();

const char *sign = "eda117e3da33ce601d3643397547ef8b";

JNIEXPORT int equal_sign(JNIEnv *env) {
    jclass utilClazz = (*env)->FindClass(env, "com/jafir/signprotect/Utils");
    if (utilClazz == NULL) {
        LOGD("not found class ");
        return 1;
    }

    LOGD("class name:%p", utilClazz);
    jmethodID method = (*env)->GetStaticMethodID(env, utilClazz, "getSignMd5",
                                                 "()Ljava/lang/String;");
    if (method == NULL) {
        LOGD("no method");
        return 1;
    }

    jstring obj = (*env)->CallStaticObjectMethod(env, utilClazz, method);
    if (obj == NULL) {
        LOGD("no obj");
        return 1;
    }

    const char *signMd5 = (*env)->GetStringUTFChars(env, obj, 0);
    int result = strcmp(signMd5, sign);

    if (result == 0) {
        return 1;
    }
    (*env)->ReleaseStringUTFChars(env, obj, signMd5);
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

//    create_thread_check_trace();
    return JNI_VERSION_1_4;
}


void thread_function() {
    int pid = getpid();
    char file_name[20] = {'\0'};
    sprintf(file_name, "/proc/%d/status", pid);
    char linestr[256];
    int i = 0, traceid;
    FILE *fp;
    while (1) {
        int i = 0;
        fp = fopen(file_name, "r");
        if (fp == NULL) {
            break;
        }
        while (!feof(fp)) {
            fgets(linestr, 256, fp);
            if (i == 5) {
                traceid = getnumberfor_str(linestr);
                if (traceid > 0) {
                    LOGD("I was be traced by %d", traceid);
                    abort();
                }
                break;
            }
            i++;
        }
        fclose(fp);
        sleep(5);
        LOGD("I was be traced by %d", traceid);
    }
}

void create_thread_check_trace() {
    pthread_t thread1;
    int err = pthread_create(&thread1, NULL, thread_function, NULL);
    if (err != 0) {
        LOGD("be traced by %s", strerror(err));
    }
}

//获取TracePid
int getnumberfor_str(char *str) {
    if (str == NULL) {
        return -1;
    }
    char result[20];
    int count = 0;
    while (*str != '\0') {
        if (*str >= 48 && *str <= 57) {
            result[count] = *str;
            count++;
        }
        str++;
    }
    int val = atoi(result);
    return val;
}

JNIEXPORT void JNI_OnUnload(JavaVM *vm, void *reserved) {
    LOGD("onunload");
}