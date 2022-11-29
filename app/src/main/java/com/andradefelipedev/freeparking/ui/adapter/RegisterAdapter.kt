package com.andradefelipedev.freeparking.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andradefelipedev.freeparking.databinding.ItemAdapterBinding
import com.andradefelipedev.freeparking.ui.model.Register

class RegisterAdapter(
    private val register: Context,
    private val registerList: MutableList<Register>,
    private val registerSelected: (Register, Int) -> Unit
) : RecyclerView.Adapter<RegisterAdapter.MyViewHolder>() {


    companion object {
        val SELECT_CAR:Int = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemAdapterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val register = registerList[position]

        holder.binding.textDescription.text = register.nome
        holder.binding.textHorarioChegada.text = register.horarioChegada.toString()
        holder.binding.textHorarioSaida.text = register.horarioSaida.toString()

        holder.binding.btnRegister.setOnClickListener {
            registerSelected(register, SELECT_CAR)
        }
    }

    override fun getItemCount() = registerList.size



    inner class MyViewHolder(val binding: ItemAdapterBinding) : RecyclerView.ViewHolder(binding.root)
}