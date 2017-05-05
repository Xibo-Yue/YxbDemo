#include "com_yuexibo_jni_demo_Java2JNI.h"
JNIEXPORT jstring JNICALL
Java_com_yuexibo_jni_1demo_Java2JNI_java2C
        (JNIEnv *ss, jobject aaa){
    return (*ss)->NewStringUTF(ss,"I am from C");
}