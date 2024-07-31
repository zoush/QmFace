package heop.v2.upload

import com.hik.vis.heop.v2.upload.HeopUpload

/**
 * Heop事件上报Flow
 */
object HeopUploads
    : IHeopUploads by HeopUpload.create(IHeopUploads::class.java)

