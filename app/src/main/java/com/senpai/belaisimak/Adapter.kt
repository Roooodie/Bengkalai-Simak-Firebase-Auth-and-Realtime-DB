package com.senpai.belaisimak

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.firebase.database.FirebaseDatabase

//import com.google.firebase.database.core.Context

class Adapter(val mCtx: Context, val layoutResId: Int, val list: List<Mahasiswa>) :
    ArrayAdapter<Mahasiswa>(mCtx, layoutResId, list){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(layoutResId,null)

        val tvNama = view.findViewById<TextView>(R.id.tvNama)
        val tvNim = view.findViewById<TextView>(R.id.tvNim)
        val tvKelas = view.findViewById<TextView>(R.id.tvKelas)
        val tvJurusan = view.findViewById<TextView>(R.id.tvJurusan)
        val tvPS = view.findViewById<TextView>(R.id.tvPS)
        val tvKompen = view.findViewById<TextView>(R.id.tvKompen)

        val btnUpdate = view.findViewById<Button>(R.id.btnUpdate)
        val btnDelete = view.findViewById<Button>(R.id.btnDelete)

        val user = list[position]

        tvNama.text = user.nama
        tvNim.text = user.nim
        tvKelas.text = user.kelas
        tvJurusan.text = user.jurusan
        tvPS.text = user.studi
        tvKompen.text = user.kompen

        btnUpdate.setOnClickListener {
            showUpdateDialog(user)
        }
        btnDelete.setOnClickListener {
            Deleteinfo(user)
        }

        return view

    }

    private fun Deleteinfo(user: Mahasiswa) {
        val progressDialog = ProgressDialog(context,
            R.style.Theme_MaterialComponents_Light_Dialog)
        progressDialog.isIndeterminate = true
        progressDialog.setMessage("Deleting...")
        progressDialog.show()
        val mydatabase = FirebaseDatabase.getInstance().getReference("Mahasiswa")
        mydatabase.child(user.id).removeValue()
        Toast.makeText(mCtx,"Deleted!!",Toast.LENGTH_SHORT).show()
        val intent = Intent(context, Dashboard::class.java)
        context.startActivity(intent)
    }

    private fun showUpdateDialog(user: Mahasiswa) {
        val builder = AlertDialog.Builder(mCtx)

        builder.setTitle("Status Check")

        val inflater = LayoutInflater.from(mCtx)

        val view = inflater.inflate(R.layout.update, null)

        val tvNama = view.findViewById<TextView>(R.id.textView4)
        val cbKajur = view.findViewById<CheckBox>(R.id.checkbox_Kajur)
        val cbKprodi = view.findViewById<CheckBox>(R.id.checkbox_Kprodi)
        val cbWali = view.findViewById<CheckBox>(R.id.checkbox_WaliKelas)
        val cbBengkel = view.findViewById<CheckBox>(R.id.checkbox_Bengkel)

        tvNama.setText(user.nama)

        if (user.kajur == "false"){
            cbKajur.text = "Kajur Belum Menyetujui/Tanda Tangan"
        } else {
            cbKajur.text = "Kajur Telah Menyetujui/Tanda Tangan"
            cbKajur.isChecked = true
        }

        if (user.kprodi == "false"){
            cbKprodi.text = "Kprodi Belum Menyetujui/Tanda Tangan"
        } else {
            cbKprodi.text = "Kprodi Telah Menyetujui/Tanda Tangan"
            cbKprodi.isChecked = true
        }

        if (user.wali == "false"){
            cbWali.text = "Wali Kelas Belum Menyetujui/Tanda Tangan"
        } else {
            cbWali.text = "Wali Kelas Telah Menyetujui/Tanda Tangan"
            cbWali.isChecked = true
        }

        if (user.bengkel == "false"){
            cbBengkel.text = "Kepala Bengkel Belum Menyetujui/Tanda Tangan"
        } else {
            cbBengkel.text = "Kepala Telah Menyetujui/Tanda Tangan"
            cbBengkel.isChecked = true
        }

        builder.setView(view)

        builder.setPositiveButton("Update Status") { dialog, which ->

            val dbUsers = FirebaseDatabase.getInstance().getReference("Mahasiswa")

            val nama = tvNama.text.toString().trim()
            val nim = user.nim
            val kelas = user.kelas
            val jurusan = user.jurusan
            val prodi = user.studi
            val kompen = user.kompen
            val kajur = cbKajur.isChecked.toString()
            val kprodi = cbKprodi.isChecked.toString().trim()
            val wali = cbWali.isChecked.toString().trim()
            val bengkel = cbBengkel.isChecked.toString().trim()

            if (nama.isEmpty()){
                tvNama.error = "please enter name"
                tvNama.requestFocus()
                return@setPositiveButton
            }

            val user = Mahasiswa(user.id,nama,nim, kelas, jurusan, prodi, kompen, kajur, kprodi, wali, bengkel)

            dbUsers.child(user.id).setValue(user).addOnCompleteListener {
                Toast.makeText(mCtx,"Updated", Toast.LENGTH_SHORT).show()
            }

        }

        builder.setNegativeButton("No") { dialog, which ->

        }

        val alert = builder.create()
        alert.show()

    }




}