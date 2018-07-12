package com.example.karshsoni.contacturidemo

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_home.*
import android.provider.ContactsContract
import kotlinx.android.synthetic.main.delete_fragment.*


class DeleteFragment: Fragment() {

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonDelete.setOnClickListener {
            val whereClause = ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY + " = '" + editText.getText().toString() + "'"
            activity.getContentResolver().delete(ContactsContract.RawContacts.CONTENT_URI, whereClause, null)
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater!!.inflate(R.layout.delete_fragment, container, false)
    }

}