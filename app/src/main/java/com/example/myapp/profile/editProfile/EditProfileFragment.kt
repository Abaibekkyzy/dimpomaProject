package com.example.myapp.profile.editProfile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.EditText
import androidx.appcompat.app.AppCompatDelegate
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapp.R
import com.example.myapp.databinding.FragmentEditProfileBinding
import com.example.myapp.profile.User
import com.example.myapp.profile.UserList

class EditProfileFragment : Fragment() {

    var idUser: Int = 1
    private lateinit var binding : FragmentEditProfileBinding

    companion object{
        var currentUser: User = UserList.getUserList().get(1)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditProfileBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name: EditText = binding.editName
        val surname: EditText = binding.editSurname
        val email: EditText = binding.editEmail



        var user: User = UserList.getUserList().get(this.idUser)

        var user2 = User(name.toString(), surname.toString(), email.toString(), user.image, user.balance, user.income, user.outcome)

        currentUser = user2
        var saveChanges: ViewGroup = binding.saveChanges

//        saveChanges.setOnClickListener{
//            val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
//            val newFragment = ProfileFragment() // замените NewFragment на ваш фрагмент
//            fragmentTransaction.replace(R.id.frame_container, newFragment)
//            fragmentTransaction.addToBackStack(null)
//            fragmentTransaction.commit()
//        }

        binding.switchTheme.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                binding.switchTheme.text = "white"
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                binding.switchTheme.text = "dark"
            }
        }

        val navController = findNavController()
        var BackBtn: CardView = binding.backButtonFromEdit

        BackBtn.setOnClickListener{

            navController.navigate(R.id.action_editProfileFragment_to_profileFragment)

        }
    }
}