package log.loginterceptor

object LogFormatterUtils {
    fun stackTrace(place: Int) = getNameFromTrace(Thread.currentThread().stackTrace, place)

    fun getNameFromTrace(traceElements: Array<StackTraceElement>?, place: Int): String? {
        return if (traceElements != null && traceElements.size > place) {
            val traceElement = traceElements[place]
            val taskName = StringBuilder()
            taskName.append(traceElement.methodName)
                .append("(")
                .append(traceElement.fileName)
                .append(":")
                .append(traceElement.lineNumber)
                .append(")")
            taskName.toString()
        } else {
            null
        }
    }
}