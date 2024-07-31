package heop.data.constant;

import androidx.annotation.StringDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 搜索结果状态字符串描述
 */
@Retention(RetentionPolicy.SOURCE)
@Target({
    ElementType.FIELD,
    ElementType.PARAMETER,
    ElementType.LOCAL_VARIABLE,
})
@StringDef({
    SearchResponseStatus.OK,
    SearchResponseStatus.FAILED,
    SearchResponseStatus.MORE,
    SearchResponseStatus.NO_MATCH,
    SearchResponseStatus.NO_MATCHES,
    SearchResponseStatus.PARAM_ERROR,
    SearchResponseStatus.TIMEOUT,
})
public @interface SearchResponseStatus {
    String OK = "OK"; //查询成功，没有更多的结果了
    String FAILED = "FAILED"; //查询失败
    String MORE = "MORE"; //查询成功，还有更多符合条件的结果没有在本次搜索中返回
    String NO_MATCH = "NO MATCH"; //没有查询到符合条件的结果
    String NO_MATCHES = "NO MATCHES"; //没有查询到符合条件的结果
    String PARAM_ERROR = "PARAM ERROR"; //参数错误
    String TIMEOUT = "TIMEOUT"; //超时
}