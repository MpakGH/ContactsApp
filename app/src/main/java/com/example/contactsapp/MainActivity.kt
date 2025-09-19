package com.contactsapp
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import com.contactsapp.R
class MainActivity : AppCompatActivity() {

    private lateinit var contactAdapter: ContactAdapter
    private val contactViewModel: ContactViewModel by viewModels {
        ContactViewModelFactory((application as ContactApp).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        contactAdapter = ContactAdapter(mutableListOf()) { contact ->
            contactViewModel.delete(contact)
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = contactAdapter
        }

        lifecycleScope.launch {
            contactViewModel.allContacts.collect { contacts ->
                contactAdapter.updateContacts(contacts)
            }
        }
    }
}