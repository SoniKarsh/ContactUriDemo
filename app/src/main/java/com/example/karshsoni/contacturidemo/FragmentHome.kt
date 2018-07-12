package com.example.karshsoni.contacturidemo

import android.os.Bundle
import android.app.Fragment
import android.app.LoaderManager
import android.content.CursorLoader
import android.content.Loader
import android.database.Cursor
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_home.*

class FragmentHome : Fragment(), LoaderManager.LoaderCallbacks<Cursor> {

    val TAG = "FragmentHome"
    val ADD_FRAGMENT_TAG = "fragAdd"
    val DELETE_FRAGMENT_TAG = "fragDelete"
    val MODIFY_FRAGMENT_TAG = "fragModify"
    var hashMap = HashMap<String, String>()
    var firstTimeLoaded = false

    override fun onLoaderReset(loader: Loader<Cursor>?) {

    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor>? {
        if(id == 1){
            return CursorLoader(activity, ContactsContract.Contacts.CONTENT_URI, null,
                    null, null, null)
        }
        return null
    }

    override fun onLoadFinished(loader: Loader<Cursor>?, cursor: Cursor?) {
        if (cursor!!.count > 0) {
            while (cursor.moveToNext()) {
                val id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                val name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                if (Integer.parseInt(cursor.getString(
                                cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    val phoneCursor = activity.contentResolver.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            arrayOf(id), null)
                    while (phoneCursor!!.moveToNext()) {
                        val phoneNo = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
//                        hashMap[name] = phoneNo
                        Log.e(TAG, ": $name and $phoneNo")
                    }
                    phoneCursor.close()
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnView.setOnClickListener {
            if(firstTimeLoaded == false){
                loaderManager.initLoader(1, null, this)
                firstTimeLoaded = true
            }else{
                loaderManager.restartLoader(1, null, this)
            }
            //            displayContacts()
        }

        btnAdd.setOnClickListener{
            val manager = fragmentManager
            val transaction = manager.beginTransaction()
            transaction.replace(R.id.mainFrag, AddFragment(), ADD_FRAGMENT_TAG)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        btnDelete.setOnClickListener {
            val manager = fragmentManager
            val transaction = manager.beginTransaction()
            transaction.replace(R.id.mainFrag, DeleteFragment(), DELETE_FRAGMENT_TAG)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        btnModify.setOnClickListener {
            val manager = fragmentManager
            val transaction = manager.beginTransaction()
            transaction.replace(R.id.mainFrag, ModifyFragment(), MODIFY_FRAGMENT_TAG)
            transaction.addToBackStack(null)
            transaction.commit()
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

}