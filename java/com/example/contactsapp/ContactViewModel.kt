import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.Flow

class ContactViewModel(private val repository: ContactRepository) : ViewModel() {

    val allContacts: Flow<List<Contact>> = repository.allContacts

    fun insert(contact: Contact) {
        viewModelScope.launch {
            repository.insert(contact)
        }
    }

    fun delete(contact: Contact) {
        viewModelScope.launch {
            repository.delete(contact)
        }
    }
}