package com.eden.lilith.utils

import android.database.Cursor
import android.provider.ContactsContract
import com.eden.lilith.LilithActivity


fun LilithActivity.getContacts(
    callback: (List<Contact>) -> Unit
) {

    val cur: Cursor? = this.contentResolver.query(
        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
        null, null, null, null
    )


    val contacts: ArrayList<Contact> = ArrayList()

    if ((cur?.count ?: 0) > 0) {
        while (cur != null && cur.moveToNext()) {

            val id: String = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID))

            val name: String =
                cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))

            if (cur.getInt(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                val pCur: Cursor? = this.contentResolver.query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", arrayOf(id), null
                )

                val contact = Contact(
                    name = name,
                    phone = arrayListOf()
                )

                while (pCur?.moveToNext()!!) {
                    val phoneNo = pCur.getString(
                        pCur.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER
                        )
                    )
                    contact.phone.add(phoneNo)
                }
                pCur.close()
                contacts.add(contact)
            }
        }
    }
    cur?.close()
    callback.invoke(contacts)
}


data class Contact(
    val name: String,
    var phone: ArrayList<String>
)
