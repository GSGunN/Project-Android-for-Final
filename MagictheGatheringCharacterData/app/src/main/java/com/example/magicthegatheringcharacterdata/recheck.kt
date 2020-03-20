package com.example.magicthegatheringcharacterdata

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction

/**
 * A simple [Fragment] subclass.
 */
class recheck : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_recheck, container, false)

        val btn_home : Button = view.findViewById(R.id.btn_home)
        btn_home.setOnClickListener{
            val r = RecycleView()
            val fm =fragmentManager
            val transaction : FragmentTransaction = fm!!.beginTransaction()
            transaction.replace(R.id.layout,r,"recycle")
            transaction.addToBackStack("recycle")
            transaction.commit()
        }

        val btn_logout : Button = view.findViewById(R.id.btn_logout)
        btn_logout.setOnClickListener{
            val l = authen()
            val fm =fragmentManager
            val transaction : FragmentTransaction = fm!!.beginTransaction()

            val builder: AlertDialog.Builder = AlertDialog.Builder(this.context)
            builder.setMessage("ออกจากระบบ?")
            builder.setPositiveButton("ยืนยัน",
                DialogInterface.OnClickListener { dialog, id ->
                    Toast.makeText(this.context, "ออกจากระบบ", Toast.LENGTH_SHORT).show()
                    transaction.replace(R.id.layout,l,"login")
                    transaction.addToBackStack("login")
                    transaction.commit()
                })
            builder.setNegativeButton("ยกเลิก",
                DialogInterface.OnClickListener { dialog, which ->
                    //dialog.dismiss();
                })
            builder.show()
        }

        return view
    }


}
