package com.hik.dsp.constants;

import androidx.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 视频预览类型
 */
@Retention(RetentionPolicy.SOURCE)
@Target({
    ElementType.FIELD,
    ElementType.PARAMETER,
    ElementType.LOCAL_VARIABLE,
})
@IntDef({
    HiAcsPreviewType.LOCAL,
    HiAcsPreviewType.REMOTE,
})
public @interface HiAcsPreviewType {
    int LOCAL = 0; // 本地视频
    int REMOTE = 1; // 远端视频或者录像视频
}