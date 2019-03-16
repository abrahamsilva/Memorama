package com.example.memorama

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.example.games.R
import kotlinx.android.synthetic.main.activity_memorama2.*
import java.util.Random
import java.util.HashMap

class MemoramaActivity : AppCompatActivity() {

    val random = Random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memorama2)
        val rv = recyclerView1
        rv.setHasFixedSize(true)
        var grid = GridLayoutManager(this, 4)
        rv.layoutManager = grid

        val keys:HashMap<Int, String> = HashMap()
        val used:HashMap<String, Int> = HashMap()

        used.put("arryn", 2)
        used.put("baratheon", 2)
        used.put("got", 2)
        used.put("lannister", 2)
        used.put("martell", 2)
        used.put("stark", 2)
        used.put("targaryen", 2)
        used.put("tyrell", 2)

        val chips = ArrayList<Chip>()

        var random = 0

        var arrayOfHouses = arrayOf(
            "arryn",
            "baratheon",
            "got",
            "lannister",
            "martell",
            "stark",
            "targaryen",
            "tyrell"
        )

        loop@ for( i  in 0..16){
            random = rand(0, 8)

            val aux = used[arrayOfHouses[random]]
            if (aux == 0) continue@loop
            if(aux != null) used.replace(arrayOfHouses[random], aux-1)
            chips.add(Chip(R.mipmap.ic_launcher, i, arrayOfHouses[random]))
            keys[i] = arrayOfHouses[random]
        }


        var  adapter = MemoramaAdapter(chips, keys)
        rv.adapter = adapter

    }

    fun rand(from: Int, to: Int) : Int { //from (inclusive) to (non-inclusive)
        return random.nextInt(to - from) + from
    }
}
