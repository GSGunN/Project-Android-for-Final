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

class confirm_sales : Fragment() {

    var title:String ?= null

    fun newInstance(title:String): confirm_sales {
        val fragment = confirm_sales()
        val bundle = Bundle()
        bundle.putString("title",title);
        fragment.setArguments(bundle)

        return fragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        if(bundle != null){
            this.title = bundle.getString("title").toString()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_confirm_sales, container, false)

        val title : TextView = view.findViewById(R.id.title)
        title.text = this.title
        //Glide.with(activity!!.baseContext).load(image).into(image_view)

        val btn_book : Button = view.findViewById(R.id.btn_confirm)
        btn_book.setOnClickListener{
            val re = recheck()
            val fm =fragmentManager
            val transaction : FragmentTransaction = fm!!.beginTransaction()

            val builder: AlertDialog.Builder = AlertDialog.Builder(this.context)
            builder.setMessage("ยืนยันข้อมูลท้งหมด?")
            builder.setPositiveButton("ยืนยัน",
                DialogInterface.OnClickListener { dialog, id ->
                    Toast.makeText(this.context, "ขอบคุณมากครับ", Toast.LENGTH_SHORT).show()
                    transaction.replace(R.id.layout,re,"success")
                    transaction.addToBackStack("success")
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