package com.example.memorama

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import android.os.Handler
import com.example.games.R
//import java.util.logging.Handler

class MemoramaAdapter(val chips: ArrayList<Chip>, val keys: HashMap<Int,String>):

    RecyclerView.Adapter<MemoramaAdapter.ChipViewHolder>(){

    var pushedButton: MutableList<Int> = mutableListOf()
    var score = 0

    var selected1: ImageView? = null
    var selected2: ImageView? = null

    var winScore = 8

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ChipViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.renglon, p0, false)
        return ChipViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  chips.size
    }

    override fun onBindViewHolder(p0: ChipViewHolder, p1: Int) {
        p0.imageView.setImageResource(chips[p1].idImage)
        p0.imageView.id = (chips[p1].pos)
    }

    fun playIntent(item: ImageView){
        if (selected1 == null) selected1 = item
        else if (selected2 == null) selected2 = item
    }

    fun checkPair (item: View){
        if (selected1 != null && selected2 != null){
            if (keys[selected1!!.id] == keys[selected2!!.id]){
                score++
                println(score)
                pushedButton.add(selected1!!.id)
                pushedButton.add(selected2!!.id)
                selected1 = null
                selected2 = null
                if(score==winScore)
                    Toast.makeText(item.context, "Ganaste", Toast.LENGTH_SHORT).show()
            }
            else{
                Handler().postDelayed({
                    selected1!!.setImageResource(R.mipmap.ic_launcher)
                    selected2!!.setImageResource(R.mipmap.ic_launcher)
                    selected1 = null
                    selected2 = null
                }, 500)
            }
        }
    }

    inner class ChipViewHolder(item : View) : RecyclerView.ViewHolder(item){
        val imageView = item.findViewById<ImageView>(R.id.chip)

        init {
            item.setOnClickListener {
                if(imageView.id !in pushedButton){
                    when (keys[imageView.id]){
                        "arryn" -> {
                            imageView.setImageResource(R.drawable.arryn)
                        }
                        "baratheon" -> {
                            imageView.setImageResource(R.drawable.baratheon)
                        }
                        "got" -> {
                            imageView.setImageResource(R.drawable.got)
                        }
                        "lannister" -> {
                            imageView.setImageResource(R.drawable.lannister)
                        }
                        "martell" -> {
                            imageView.setImageResource(R.drawable.martell)
                        }
                        "stark" -> {
                            imageView.setImageResource(R.drawable.stark)
                        }
                        "targaryen" -> {
                            imageView.setImageResource(R.drawable.targaryen)
                        }
                        "tyrell" -> {
                            imageView.setImageResource(R.drawable.tyrell)
                        }
                    }
                    playIntent(imageView)
                }
                checkPair(item)
            }
        }
    }
}
