package com.example.karshsoni.contacturidemo

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.modify_fragment.*
import android.provider.ContactsContract
import android.content.ContentValues



class ModifyFragment: Fragment() {

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonModify.setOnClickListener {
            val updateName = editModifyName.getText().toString()
            val updatePhone = editModifyNum.text.toString()

            val where = ContactsContract.RawContacts._ID + " = ? "
            val params = arrayOf(updateName)

            val contentResolver = activity.getContentResolver()
            val contentValues = ContentValues()
            contentValues.put(ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY, updatePhone)
            contentResolver.update(ContactsContract.RawContacts.CONTENT_URI, contentValues, where, params)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater!!.inflate(R.layout.modify_fragment, container, false)
    }

}