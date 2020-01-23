package com.senpai.belaisimak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.view.View
import android.widget.CheckBox
import android.widget.ListView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
//import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_dashboard.*
import com.google.firebase.database.core.Context
import java.text.FieldPosition


class Dashboard : AppCompatActivity() {

    lateinit var ref : DatabaseReference
    lateinit var list : MutableList<Mahasiswa>
    lateinit var rvMahasiswa: ListView

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        auth = FirebaseAuth.getInstance()

        btnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnMahasiswa.setOnClickListener {
            val intent = Intent(this, Detail::class.java)
            startActivity(intent)
        }

        ref = FirebaseDatabase.getInstance().getReference("Mahasiswa")
        list = mutableListOf()
        rvMahasiswa = findViewById(R.id.rvMahasiswa)

        ref.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0!!.exists()){
                    list.clear()
                    for (h in p0.children){
                        val user = h.getValue(Mahasiswa::class.java)
                        list.add(user!!)
                    }
                    val adapter = Adapter(this@Dashboard,R.layout.users,list)
                    rvMahasiswa.adapter = adapter
                }
            }
        })

    }


    override fun onBackPressed() {
        super.onBackPressed()
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null) {
            moveTaskToBack(true)
            finish()
        }
    }
    val firebaseAuth = FirebaseAuth.getInstance()

    fun onCheckboxClicked(view: View) {
        if (view is CheckBox) {
            val checked: Boolean = view.isChecked

            when (view.id) {
                R.id.checkbox_Kajur -> {
                    if (checked) {

                    } else {
                        // Remove the meat
                    }
                }
                R.id.checkbox_Kprodi -> {
                    if (checked) {
                        // Cheese me
                    } else {
                        // I'm lactose intolerant
                    }
                }
                R.id.checkbox_WaliKelas -> {
                    if (checked) {
                        // Cheese me
                    } else {
                        // I'm lactose intolerant
                    }
                }
                R.id.checkbox_Bengkel -> {
                    if (checked) {
                        // Cheese me
                    } else {
                        // I'm lactose intolerant
                    }
                }
            }
        }
    }


}
