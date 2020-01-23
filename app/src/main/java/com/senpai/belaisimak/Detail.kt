package com.senpai.belaisimak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_detail.*

class Detail : AppCompatActivity() {

    lateinit var ref : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        ref = FirebaseDatabase.getInstance().getReference("Mahasiswa")

        btnAddMahasiswa.setOnClickListener {
            savedata()
//            Toast.makeText(this, "Hello!", Toast.LENGTH_SHORT).show()
            val intent = Intent (this, Dashboard::class.java)
            startActivity(intent)
            finish()
        }

        val spinner: Spinner = findViewById(R.id.inputJurusan)
        val spinner1: Spinner = findViewById(R.id.inputPS)

        ArrayAdapter.createFromResource(
            this,
            R.array.jurusan_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.prodi_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner1.adapter = adapter
        }

        spinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            }
        }

        spinner1.onItemSelectedListener = object :AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            }
        }
        

    }

    private fun savedata() {

        val kajur: String
        kajur = "false"
        val kprodi: String
        kprodi = "false"
        val wali: String
        wali = "false"
        val bengkel: String
        bengkel = "false"

        val nama = inputNama.text.toString()
        val nim = inputNim.text.toString()
        val kelas = inputKelas.text.toString()
        val jurusan = inputJurusan.selectedItem.toString()
        val studi = inputPS.selectedItem.toString()
        val kompen = inputKompen.text.toString()
        val userId = ref.push().key.toString()

        val user = Mahasiswa(userId, nama,nim, kelas, jurusan, studi, kompen, kajur, kprodi, wali, bengkel)

        ref.child(userId).setValue(user).addOnCompleteListener {
            Toast.makeText(applicationContext, "Successs",Toast.LENGTH_SHORT).show()
            inputNama.setText("")
            inputNim.setText("")
            inputKelas.setText("")
            inputKompen.setText("")
        }
    }

}
