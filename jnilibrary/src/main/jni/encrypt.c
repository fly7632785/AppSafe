#include <jni.h>

JNIEXPORT jstring JNICALL
Java_com_jafir_jnilibrary_Hello_sayHello(JNIEnv *env, jobject instance) {
    return (*env)->NewStringUTF(env, "hello world!!!");
}
