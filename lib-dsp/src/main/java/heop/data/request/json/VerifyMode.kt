package com.hik.heop.data.request.json

import heop.data.constant.VerifyType


data class VerifyModeBean(
    var VerifyMode: VerifyMode? = null
)

data class VerifyMode(
    @VerifyType
    var mode: String? = null,
    var card: Boolean? = null,
    var fingerPrint: Boolean? = null,
    var face: Boolean? = null,
    var QRCode: Boolean? = null,
    var password: Boolean? = null,
)