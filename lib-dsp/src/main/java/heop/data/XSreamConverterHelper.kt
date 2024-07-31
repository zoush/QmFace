package heop.data

import com.hik.vis.heop.util.sequence.SequenceFieldOrder
import com.thoughtworks.xstream.annotations.XStreamConverter
import com.thoughtworks.xstream.converters.extended.ToAttributedValueConverter

/**
 * <node max="[max]">[value]</node>
 *
 * eg: <node max="100">abc123</node>
 */
@SequenceFieldOrder(
    "max",
    "value"
)
@XStreamConverter(value = ToAttributedValueConverter::class, strings = ["value"])
data class AttributeMaxValueString(
    val max: Int? = null, /* 属性 */
    val value: String? = null /* 内容 */
)

/**
 * <node min="[min]" max="[max]">[value]</node>
 *
 * eg: <node min="1" max="100">abc123</node>
 */
@XStreamConverter(value = ToAttributedValueConverter::class, strings = ["value"])
@SequenceFieldOrder(
    "min",
    "max",
    "value"
)
data class AttributeMinMaxValueString(
    val min: Int? = null, /* 属性 */
    val max: Int? = null, /* 属性 */
    val value: String? = null /* 内容 */
)

/**
 * <node min="[min]" max="[max]">[value]</node>
 *
 * eg: <node min="1" max="100">abc123</node>
 */
@XStreamConverter(value = ToAttributedValueConverter::class, strings = ["value"])
@SequenceFieldOrder(
    "min",
    "max",
    "value"
)
data class AttributeMinMaxStringValueString(
    val min: String? = null, /* 属性 */
    val max: String? = null, /* 属性 */
    val value: String? = null /* 内容 */
)

/**
 * <node opt="[opt]">[value]</node>
 *
 * eg: <node opt="1,2,3"></node>
 */
@SequenceFieldOrder(
    "opt",
    "value"
)
@XStreamConverter(value = ToAttributedValueConverter::class, strings = ["value"])
data class AttributeOptValueString(
    val opt: String? = null, /* 属性 */
    val value: String? = null /* 内容 */
)