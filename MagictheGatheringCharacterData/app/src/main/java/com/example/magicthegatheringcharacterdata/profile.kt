package com.example.magicthegatheringcharacterdata

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import com.facebook.login.LoginManager

/**
 * A simple [Fragment] subclass.
 */
class profile : Fragment() {

    var PhotoURL : String = ""
    var Name : String = ""

    fun newInstance(url: String,name : String): profile {

        val profile = profile()
        val bundle = Bundle()
        bundle.putString("PhotoURL", url)
        bundle.putString("Name", name)
        profile.setArguments(bundle)

        return profile
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val ivProfilePicture = view.findViewById(R.id.iv_profile) as ImageView
        val tvName = view.findViewById(R.id.tv_name) as TextView
        val exit_button = view.findViewById(R.id.exit_button) as Button

        Glide.with(activity!!.baseContext)
            .load(PhotoURL)
            .into(ivProfilePicture)

        tvName.setText(Name)

        val btn_continue : Button = view.findViewById(R.id.btn_continue)
        btn_continue.setOnClickListener {

            Toast.makeText(context,"Next", Toast.LENGTH_LONG).show()

            val recycle = RecycleView()
            val fm =fragmentManager
            val transaction : FragmentTransaction = fm!!.beginTransaction()
            transaction.replace(R.id.layout,recycle,"recycle")
            transaction.addToBackStack("recycle")
            transaction.commit()


        }

        exit_button.setOnClickListener{

            val builder: AlertDialog.Builder = AlertDialog.Builder(this.context)
            builder.setMessage("ยืนยันที่จะออกจากระบบ?")
            builder.setPositiveButton("ยืนยัน",
                DialogInterface.OnClickListener { dialog, id ->
                    Toast.makeText(this.context, "ออกจากระบบ", Toast.LENGTH_SHORT).show()
                    LoginManager.getInstance().logOut()
                    activity!!.supportFragmentManager.popBackStack()
                })
            builder.setNegativeButton("ยกเลิก",
                DialogInterface.OnClickListener { dialog, which ->
                    //dialog.dismiss();
                })
            builder.show()


        }
        // Inflate the layout for this fragment
        return view

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = arguments
        if (bundle != null) {
            PhotoURL = bundle.getString("PhotoURL").toString()
            Name = bundle.getString("Name").toString()

        }

    }

}
