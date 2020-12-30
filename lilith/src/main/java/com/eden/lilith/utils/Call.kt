package com.eden.lilith.utils

import android.database.Cursor
import android.provider.CallLog
import com.eden.lilith.LilithActivity
import java.util.*


fun LilithActivity.getCallLogs(
    callType: CallType = CallType.ALL_CALLS,
    callback: (ArrayList<Call>) -> Unit
) {

    val logs = ArrayList<Call>()

    val selection: String? = when (callType) {
        CallType.INCOMING_CALLS -> CallLog.Calls.TYPE + " = " + CallLog.Calls.INCOMING_TYPE
        CallType.OUTGOING_CALLS -> CallLog.Calls.TYPE + " = " + CallLog.Calls.OUTGOING_TYPE
        CallType.MISSED_CALLS -> CallLog.Calls.TYPE + " = " + CallLog.Calls.MISSED_TYPE
        CallType.ALL_CALLS -> null
    }

    val typeOfCall = listOf(
        "INCOMING_TYPE",
        "OUTGOING_TYPE",
        "MISSED_TYPE",
        "VOICEMAIL_TYPE",
        "REJECTED_TYPE",
        "BLOCKED_TYPE",
        "ANSWERED_EXTERNALLY_TYPE"
    )

    val cursor: Cursor =
        contentResolver.query(CallLog.Calls.CONTENT_URI, null, selection, null, null)!!
    val number = cursor.getColumnIndex(CallLog.Calls.NUMBER)
    val type = cursor.getColumnIndex(CallLog.Calls.TYPE)
    val date = cursor.getColumnIndex(CallLog.Calls.DATE)
    val duration = cursor.getColumnIndex(CallLog.Calls.DURATION)

    while (cursor.moveToNext()) {
        val log = Call(
            number = cursor.getString(number),
            type = typeOfCall[cursor.getInt(type) - 1],
            duration = cursor.getInt(duration),
            date = cursor.getLong(date)
        )
        logs.add(log)
    }
    cursor.close()
    callback.invoke(logs)
}

data class Call(
    val number: String,
    val date: Long,
    val duration: Int,
    val type: String
)

enum class CallType {
    INCOMING_CALLS,
    OUTGOING_CALLS,
    MISSED_CALLS,
    ALL_CALLS
}






